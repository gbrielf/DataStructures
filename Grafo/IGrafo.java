    package Grafo;
    import java.util.ArrayList;
    import Grafo.Aresta;
    import Grafo.Vertice;

    interface IGrafo{
        public ArrayList<Vertice> finalVertex(Aresta e); //Retorna um array armazenando os vértices finais da aresta e.
        public Vertice opposite(Vertice v, Aresta e); // Retorna o vértice oposto de v em e, ou seja, o vértice final da aresta e separado do vértice v. Um erro ocorre se e não é incidente a v
        public Boolean isAdjacent(Vertice v, Vertice w); // Retorna true se v e w são adjacentes
        public Object replaceVertex(Vertice v, Vertice x); // Substitui o elemento armazenado do vértice V com X
        public Object replaceEdge(Aresta v, Aresta x); // Substitui o elemento armazenado na aresta e com x
        public void insertVertex(Object o); // Insere e retorna um novo vértice armazenando o elemento o
        public void inserirAresta(Vertice v, Vertice w, Object o); // Insere e retorna uma nova aresta não-dirigida (v,w) armazenando o elemento o
        public Object removeVertex(Vertice v); // Remove o vértice v ( e todas as arestas incidentes) e retorna o elemento armazenado em v
        public Object removeEdge( Aresta e); // Remove a aresta e, retornando o elemento armazenado
        public ArrayList<Aresta> incidentEdges(Vertice v); // retorna as arestas incidentes em um vertice
        public ArrayList<Vertice> vertexs(); // retorna todos os vertices 
        public ArrayList<Aresta> edges(); // retorna todas as arestas
    }