package FilaArrayComReversao;

interface FilaComReversao {
    public void enqueue(Object O); 
    //lança isFull() quando cheia, dobrando capacidade da fila
    public Object dequeue(); 
    //lança exceção quando a fila está vazia
    public Object first();
    public int size();
    public boolean isEmpty();
    public boolean isFull();
    public void reverse();
    public void resize();
}