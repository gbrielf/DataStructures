package ArvoreGenerica;
import java.util.Iterator;

public interface Arvore<N>{
    // métodos genéricos
    public int size();  // OK
    public int height();
    public boolean isEmpty();  // OK
    public Iterator elements();
    public Iterator Ns();
    // métodos de acesso
    public N root();  // OK
    public N parent(N n);
    public Iterator children(N n);
    // métodos de consulta
    public boolean isInternal(N n);
    public boolean isExternal(N n);
    public boolean isRoot(N n);
    public int depth(N n);  // OK
    // método de atualização
    public Object replace(N n, Object o);
    
}
