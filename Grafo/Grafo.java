package Grafo;

import java.util.ArrayList;

public class Grafo implements IGrafo {
    private ArrayList<Vertice> vertices; // conjunto de vértices
    private ArrayList<Aresta> arestas; // conjunto de arestas
    // Dado um grafo G(V,A), a matriz de adjacência M=[aij] é uma matriz n x m tal
    // que aij:
    // - é +1 se existe (Vi,Vj) pertencente a A.
    // - 0 caso contrário.
    private ArrayList[][] matrizDeAdjacencia;
    private int ordem; // número de nós

    // definição de grafo G(V,A)
    public Grafo() {
        // por definição um grafo tem que ter ao menos um vértice mas não é obrigado ter
        // arestas
        this.vertices = new ArrayList<>();
        this.arestas = new ArrayList<>();
        this.ordem = 0;
        this.matrizDeAdjacencia = new ArrayList[0][0];
    }

    public int getOrder() {
        return ordem;
    }

    // Retorna um array armazenando os vértices finais da aresta e.
    public ArrayList<Vertice> finalVertex(Aresta e) {
        ArrayList<Vertice> verticesResposta = new ArrayList<Vertice>();

        verticesResposta.add(e.getVertexI());

        verticesResposta.add(e.getVertexJ());

        return verticesResposta;
    }

    // Retorna o vértice oposto de v em e, ou seja, o vértice final da aresta e
    // separado do vértice v.
    // Um erro ocorre se e não é incidente a v
    public Vertice opposite(Vertice v, Aresta e) {
        if (e.getVertexI() == v) {
            return e.getVertexJ();
        }
        if (e.getVertexJ() == v) {
            return e.getVertexI();
        }

        throw new ArestaNaoIncidenteNoVerticeException("A aresta sinalizada não incide no vértice especificado.");
    }

    // Retorna true se v e w são adjacentes
    public Boolean isAdjacent(Vertice v, Vertice w) {
        for (Aresta e : arestas) {
            Vertice i = e.getVertexI();
            Vertice j = e.getVertexJ();

            if ((i == v && j == w) || (i == w && j == v)) {
                return true;
            }

        }

        return false;
    }

    // Substitui o elemento armazenado do vértice V com X
    public Object replaceVertex(Vertice v, Object x) {
        Object elementoSubstituido = v.getElement();

        v.setElement(x);

        return elementoSubstituido;
    }

    // Substitui o elemento armazenado na aresta e com x
    public Object replaceEdge(Aresta v, Object x) {
        Object elementoSubstituido = v.getElement();

        v.setElement(x);

        return elementoSubstituido;
    }

    // Insere e retorna um novo vértice armazenando o elemento o
    public Vertice insertVertex(Object o) {
        Vertice v = new Vertice();
        v.setElement(o);
        vertices.add(v);
        this.ordem++;

        return v;
    }

    // Insere e retorna uma nova aresta não-dirigida (v,w) armazenando o elemento o
    public Aresta insertEdge(Vertice v, Vertice w, Object o) {
        // caso o vertice seja isolado, não pertence ao grafo
        if (!vertices.contains(v) || !vertices.contains(w)) {
            throw new IllegalArgumentException("Vértice não pertence ao grafo");
        }

        Aresta a = new Aresta(v, w, Sentido.NAO_DIRECIONADA);
        a.setElement(o);
        arestas.add(a);

        if (v != w) {
            v.setDegree(v.getDegree() + 1);
            w.setDegree(w.getDegree() + 1);
        } else {
            v.setDegree(v.getDegree() + 2); // laço conta 2
        }

        return a;
    }

    // Remove o vértice v ( e todas as arestas incidentes) e retorna o elemento
    // armazenado em v
    public Object removeVertex(Vertice v) {
        if (!vertices.contains(v)) {
            throw new VerticeInexistenteException("O vértice não existe.");
        }

        for (Aresta e : new ArrayList<>(arestas)) {
            if (e.getVertexJ() == v || e.getVertexI() == v) {
                removeEdge(e);
            }
        }

        vertices.remove(v);

        return v.getElement();
    }

    public void updateDegrees(Aresta e, int valor) {
        Vertice i = e.getVertexI();
        Vertice j = e.getVertexJ();

        i.setDegree(i.getDegree() + valor);
        j.setDegree(j.getDegree() + valor);
        switch (e.getWay()) {
            case I_PARA_J:
                i.setOutdegree(i.getOutdegree() + valor);
                j.setIndegree(j.getIndegree() + valor);
                break;
            case J_PARA_I:
                j.setOutdegree(j.getOutdegree() + valor);
                i.setIndegree(i.getIndegree() + valor);
                break;
            default:
                break;
        }
    }

    // Remove a aresta e, retornando o elemento armazenado
    public Object removeEdge(Aresta e) {
        if (!arestas.contains(e)) {
            throw new ArestaInexistenteException("A aresta não existe.");
        }

        arestas.remove(e);
        updateDegrees(e, -1);

        return e.getElement();
    }

    // retorna as arestas incidentes em um vertice
    public ArrayList<Aresta> incidentEdges(Vertice v) {
        ArrayList<Aresta> incidentes = new ArrayList<>();

        for (Aresta e : arestas) {
            if (e.getVertexI() == v || e.getVertexJ() == v) {
                incidentes.add(e);
            }
        }

        return incidentes;
    }

    // retorna todos os vertices
    public ArrayList<Vertice> vertexs() {
        return new ArrayList<>(vertices);
    }

    // retorna todas as arestas
    public ArrayList<Aresta> edges() {
        return new ArrayList<>(arestas);
    }
}
