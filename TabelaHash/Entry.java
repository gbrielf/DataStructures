package TabelaHash;

public class Entry<K, V>{
    K key;
    V value;

    public static final Entry<? , ?> AVAILABLE = new Entry<>(null,null);
    
    public Entry(K key, V value){
        this.key = key;
        this.value = value;
        
    }


    public void setKey(K newKey){
        this.key = newKey;
    }

    public K getKey(){
        return this.key;
    }

    public void setValue(V newValue){
        this.value = newValue;
    }

    public V getValue(){
        return this.value;
    }
}