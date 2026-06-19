package ArvoreGenerica;
import java.util.Iterator;

public interface Arvore {
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
    public boolean isInternal(No n);
    public boolean isExternal(No n);
    public boolean isRoot(No n);
    public int depth(No n);  // OK
    // método de atualização
    public Object replace(No n, Object o);
    
}
