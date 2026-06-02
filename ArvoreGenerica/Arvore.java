package ArvoreGenerica;

public interface Arvore {
    // métodos genéricos
    public int size();  // OK
    public int height();
    public boolean isEmpty();  // OK
    public Itarator elements();
    public Iterator nos();
    // métodos de acesso
    public No root();  // OK
    public No parent(No n);
    public Iterator children(No n);
    // métodos de consulta
    public boolean isInternal(No n);
    public boolean isExternal(No n);
    public boolean isRoot(No n);
    public int depth(No n);  // OK
    // método de atualização
    public Object replace(No n, Object o);
    
}
