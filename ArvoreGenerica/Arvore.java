package ArvoreGenerica;
import java.util.Iterator;

public interface Arvore<N>{
    // métodos genéricos
    public int size();  // OK
    public int height();
    public boolean isEmpty();  // OK
    public Iterator elements();
    public Iterator nos();
    // métodos de acesso
    public No getRoot();  // OK
    public No parent(No n);
    public Iterator<No> children(No n);
    // métodos de consulta
    public boolean isInternal(N n);
    public boolean isExternal(N n);
    public boolean isRoot(N n);
    public int depth(N n);  // OK
    // método de atualização
    public Object replace(N n, Object o);
    
}
