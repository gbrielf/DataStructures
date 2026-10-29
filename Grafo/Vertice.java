package Grafo;

public class Vertice {
    private int grau; // número de arestas incidentes
    private int outdegree; // em um dígrafo, é o número de arestas que SAEM do vértice     
    private int indegree; // em um dígrafo, é o número de arestas que CHEGAM no vértice
    private String rotulo; // título de um vértice, não é obrigatório
    private Object elemento; // elemento de um vértice, não é obrigatório

    public Vertice(){
        this.rotulo = null;
        this.elemento = null;
        this.grau = 0;
        this.outdegree = 0;
        this.indegree = 0;
    }

    public int getDegree(){
        return grau;
    }

    public void setDegree(int grau){
        this.grau = grau;
    }

    public int getOutdegree(){
        return outdegree;
    }

    public void setOutdegree(int outdegree){
        this.outdegree = outdegree;
    }

    public int getIndegree(){
        return indegree;
    }

    public void setIndegree(int indegree){
        this.indegree = indegree;
    }

    public String getLabel(){
        return rotulo;
    }

    public void setLabel(String rotulo){
        this.rotulo = rotulo;
    }

    public Object getElement(){
        return elemento;
    }
    public void setElement(Object elemento){
        this.elemento = elemento;
    }
}
