package TabelaHash;

import java.util.Iterator;

public interface Hash<K, V> {
    public V findElement(K key);
    
    public V removeElement(K key);

    public void insertItem(K key, V element);

    public int size();

    public boolean isEmpty();

    public Iterator<K> keys();

    public Iterator<V> elements();
    
}