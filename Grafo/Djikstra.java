package Grafo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.function.ToDoubleFunction;

public class Djikstra {
    private static final double INFINITO = Double.POSITIVE_INFINITY;

    private final IGrafo grafo;
    private final Vertice origem; //v0
    private final ToDoubleFunction<Aresta> peso;
    private final Set<Vertice> vertices = new HashSet<>(); // S = vértices com distâncias já definidas
    private final Map<Vertice, Double> distancia = new HashMap<>(); // distancia[v]: menor distância conhecida de v0 até v
    private final Map<Vertice, Vertice> antecessor = new HashMap<>(); // vértice anterior a v no melhor caminho

    public Djikstra(IGrafo grafo, Vertice origem, ToDoubleFunction<Aresta> peso){
        this.grafo = grafo; 
        this.origem = origem;
        this.peso = peso;
        execute();
    }

    private void execute(){
        ArrayList<Vertice> V = grafo.vertexs();

        // S = {v0}; D[v0] = 0;
        vertices.add(origem);
        distancia.put(origem, 0.0);

        // para cada v em V - {v0}: D[v] = L(v0, v)
        for (Vertice v : V){
            if ( v == origem ) continue;
            double l = L(origem, v);
            distancia.put(v, l);
            if (l != INFINITO ){
                antecessor.put(v, origem); // ligação direta
            }
        }

        // enquanto S <> V
        while (vertices.size() != V.size()){

            // escolha o vértice w em V - S tal que D[w]
            Vertice w = null;
            for (Vertice v : V){
                if(!vertices.contains(v) && (w == null || distancia.get(v) < distancia.get(w))){
                    w = v;
                }
            }

            // se o menor D[w] é infinito, os que sobraram são inalcançáveis
            if (distancia.get(w) == INFINITO) break;

            // coloque w em S ( a distancia de w passa a ser definida)
            vertices.add(w);

            // para cada v em V - S: tenta melhorar D[v]
            for (Vertice v : V) {
                if (vertices.contains(v)) continue;

                double candidata = distancia.get(w) + L(w, v);

                if (candidata < distancia.get(v)){
                    distancia.put(v, candidata);
                    antecessor.put(v, w);
                }
            }
        }

    }

    // função L pertencente aos reais, e um vértice fixo V0 de V
    private double L(Vertice a, Vertice b){
        double menor = INFINITO;

        for( Aresta e : grafo.incidentEdges(a)){
            if(!canLeave(a, e)) continue;
            if(grafo.opposite(a, e) != b) continue;

            double p = peso.applyAsDouble(e);
            if(p < 0){
                throw new IllegalArgumentException("Dijkstras não aceita peso negativo.");
            }
            if(p < menor) menor = p;
        }

        return menor;
    }

    private boolean canLeave(Vertice u, Aresta e){
        switch (e.getWay()){
            case I_PARA_J: return e.getVertexI() == u;
            case J_PARA_I: return e.getVertexJ() == u;
            default: return true; // NAO_DIRECIONADA
        }
    }

    // menor distancia de v0 até v (infinito de v é inalcançável)
    public double distanceTo(Vertice v){
        return distancia.get(v);
    }

    // caminho mínimo v0 -> destino, ou null se não há caminho
    public ArrayList<Vertice> pathTo(Vertice destino){
        if (distancia.get(destino) == INFINITO) return null;

        LinkedList<Vertice> caminho = new LinkedList<>();
        for (Vertice v = destino; v != null; v = antecessor.get(v)){
            caminho.addFirst(v);
        }

        return new ArrayList<>(caminho);
    }
    
}
