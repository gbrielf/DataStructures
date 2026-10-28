package Grafo;

public class Vertice {
    private int grau; // número de arestas incidentes
    private int outdegree; // em um dígrafo, é o número de arestas que SAEM do vértice     
    private int indegree; // em um dígrafo, é o número de arestas que CHEGAM no vértice
    private String rotulo; // título de um vértice, não é obrigatório
    private Double valor; // valor de um vértice, não é obrigatório

    public Vertice(){
        this.rotulo = null;
        this.valor = null;
        this.grau = 0;
        this.outdegree = 0;
        this.indegree = 0;
    }

    public double getValue(){
        return valor;
    }

    public void setValue(double valor){
        this.valor = valor;
    }

    public String getLabel(){
        return rotulo;
    }

    public void setLabel(String rotulo){
        this.rotulo = rotulo;
    }
    
}
