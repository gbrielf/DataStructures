package ArvoreGenerica;
import java.util.Iterator;

public interface Arvore<N, E>{
    // métodos genéricos
    public int size();  // OK
    public int height();
    public boolean isEmpty();  // OK
    public Iterator<E> elements();
    public Iterator<N> nos();
    // métodos de acesso
    public N getRoot();  // OK
    public N parent(N n);
    public Iterator<N> children(N n);
    // métodos de consulta
    public boolean isInternal(N n);
    public boolean isExternal(N n);
    public boolean isRoot(N n);
    public int depth(N n);  // OK
    // método de atualização
    public Object replace(N n, E o);
    
}
