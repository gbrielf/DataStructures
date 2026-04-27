package Lista;

public interface Lista {
    // métodos genéricos
    public int size();
    public boolean isEmpty();
    // métodos de fila
    public boolean isFirst(No n);
    public boolean isLast(No n);
    // métodos de acesso a info
    public Object first();
    public Object last();
    public Object before(Object p);
    public Object after(Object p);
    // métodos para atualizar
    public Object replaceElement(No n, Object o); // altera o conteúdo do nó
    public Object swapElements(No n, No q); // troca a posição de dois nós

    public void insertBefore(No n, Object o);
    public void insertAfter(No n, Object o);
    public void insertFirst(Object o);
    public void insertLast(Object o);

    public Object remove(No n);    
}
