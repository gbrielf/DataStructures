package Grafo;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;


public class Aresta {
    private Vertice verticeI;
    private Vertice verticeJ;
    // se o grafo for direcionado -1 informará que vi é o vértice de origem
    // +1 se vi for o vértice final, caso contrário será 0
    private Sentido sentido;

    // definição de aresta: a(vi,vj)
    public Aresta( Vertice verticeI, Vertice verticeJ, Sentido sentido ){
        this.verticeI = verticeI;
        this.verticeJ = verticeJ;
        this.sentido = sentido;
    }

    public void setWay(Sentido sentido){
        this.sentido = sentido;
    }

    public Sentido getWay(){
        return sentido;
    }

    public Vertice getVertexI(){
        return verticeI;
    }

    public Vertice getVertexJ(){
        return verticeJ;
    }
}
