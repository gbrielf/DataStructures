package Sequencia;

import Lista.No;

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
    public No isFirst();
    public No isLast();
    public No before(No p);
    public No after(No p);

    public Object replaceElement(int n, Object o);
    public void swapElements(int n, int q);

    public void insertBefore(int n, Object o);
    public void insertAfter(int n, Object o);
    public void insertFirst(Object o);
    public void insertLast(Object o);

    public Object remove(int n);
    // métodos ponte
    public Object atRank(int r);
    public Object rankOf(No n);
}