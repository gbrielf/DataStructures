package Lista;

public interface Lista{
    // métodos genéricos
    public int size(); //ok
    public boolean isEmpty(); //ok

    // métodos de fila
    public boolean isFirst(No n); //ok
    public boolean isLast(No n); //ok

    // métodos de acesso a info
    public Object getFirst(); //ok
    public Object getLast(); //ok
    public No before(No p); //ok  retorna nó antes
    public No after(No p); //ok retorna nó seguinte

    // métodos para atualizar
    public Object replaceElement(No n, Object o); // ok. altera o conteúdo do nó
    public void swapElements(No n, No q); // ok. troca a posição de dois nós

    public No insertBefore(No n, Object o); // ok insere antes do no n
    public No insertAfter(No n, Object o); // ok inser depois do no n
    public void insertFirst(Object o); // ok insere depois do head
    public void insertLast(Object o); // ok insere depois do tail

    public Object remove(No n); // ok
}
