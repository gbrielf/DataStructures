package Grafo;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Aresta {
    private Vertice verticeI;
    private Vertice verticeJ;
    // se o grafo for direcionado -1 informará que vi é o vértice de origem
    // +1 se vi for o vértice final, caso contrário será 0
    private int sentido;

    // definição de aresta: a(vi,vj)
    public Aresta( Vertice verticeI, Vertice verticeJ, int sentido ){
        this.verticeI = verticeI;
        this.verticeJ = verticeJ;
        this.sentido = sentido;
    }

    public void setWay(int sentido){
        this.sentido = sentido;
    }

    public int getWay(){
        return sentido;
    }
}
