package Grafo;
import java.util.ArrayList;
import Grafo.Aresta;
import Grafo.Vertice;

interface IGrafo{
    public ArrayList<Vertice> finalVertex(Aresta e);
    public Vertice opposite(Vertice v, Aresta e);
    public Boolean isAdjacent(Vertice v, Vertice w);
    public Object replaceVertex(Vertice v, Vertice x);
    public Object replaceVertex(Aresta v, Aresta x);
    public void insertVertex(Object o);
    public void inserirAresta(Vertice v, Vertice w, Object o);
    public Object removeVertex(Vertice v);
    public Object removeEdge( Aresta e);
    public ArrayList<Aresta> incidentEdges(Vertice v);
    public ArrayList<Vertice> vertexs();
    public ArrayList<Aresta> edges();
}