package Grafo;

public class Aresta {
    private Vertice verticeI;
    private Vertice verticeJ;
    // se o grafo for direcionado -1 informará que vi é o vértice de origem
    // +1 se vi for o vértice final, caso contrário será 0
    private Sentido sentido;
    private Object elemento;

    // definição de aresta: a(vi,vj)
    public Aresta( Vertice verticeI, Vertice verticeJ, Sentido sentido ){
        this.verticeI = verticeI;
        this.verticeJ = verticeJ;
        this.sentido = sentido;
        this.elemento = null;
    }

    public Object getElement(){
        return elemento;
    }

    public void setElement(Object elemento){
        this.elemento = elemento;
    }

    public Sentido getWay(){
        return sentido;
    }

    public void setWay(Sentido sentido){
        this.sentido = sentido;
    }

    public Vertice getVertexI(){
        return verticeI;
    }

    public Vertice getVertexJ(){
        return verticeJ;
    }
}
