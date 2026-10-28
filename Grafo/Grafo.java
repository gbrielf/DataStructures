package Grafo;

import java.util.ArrayList;

import Grafo.Aresta;

public class Grafo implements IGrafo {
    private ArrayList<Vertice> vertices; // conjunto de vértices
    private ArrayList<Aresta> arestas; // conjunto de arestas   
    // Dado um grafo G(V,A), a matriz de adjacência M=[aij] é uma matriz n x m tal que aij:
    // - é +1 se existe (Vi,Vj) pertencente a A.
    // - 0 caso contrário.
    private ArrayList[][] matrizDeAdjacencia;
    private int ordem; // número de nós
    
    // definição de grafo G(V,A)
    public Grafo(){
        // por definição um grafo tem que ter ao menos um vértice mas não é obrigado ter arestas
        this.vertices = new ArrayList<>();
        this.arestas = new  ArrayList<>();
        this.ordem = 0;
        this.matrizDeAdjacencia = new ArrayList[0][0];
    }
}
