package TabelaHash;

import java.util.Iterator;

public interface Hash<K, V> {
    public V findElement(K key);
    
    public V removeElement(K key);

    public void insertItem(K key, V element); // OK

    public int size(); // OK

    public boolean isEmpty(); // OK

    public Iterator<K> keys();

    public Iterator<V> elements();
    
}