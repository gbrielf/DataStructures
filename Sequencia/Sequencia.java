package Sequencia;

public interface Sequencia{
    // métodos genéricos
    public int size();
    public boolean isEmpty();
    // métodos de vetor
    public Object elemAtRank(int r);
    public Object replaceAtRank(int r, Object o);
    public void insertAtRank(int r, Object o);
    public Object removeAtRank(int r);
    // métodos de lista duplamente ligada
    public No first();
    public No last();
    public No before();
    public No after();
    public Object replaceElement(int n, Object o);
    public void swapElements(int n, int q);
    public void insertBefore(int n, Object o);
    public void insertAfter(int n, Object o);
    public void insertFirst(Object o);
    public void insertLast(Object o);
    public Object remove(int n);
    // métodos ponte
    public Object atRank(int r);
    public Object rankOf(int n);
}