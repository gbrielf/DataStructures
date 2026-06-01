public class FilaDePrioridadeComHeapArray{
    private int tamanho;
    private Object[] a;
    private int capacidade;
    
    public FilaDePrioridadeComHeapArray(int capacidade){
        this.capacidade = capacidade;
        this.a = new Object[capacidade];
        this.tamanho = 0;
    }

    public void insert(int k, Object o){
        Item item = new Item(k, o);
    }
    public Object removeMin(){
        Object resultado = a[0].getElemento();

    }
    public int min(){
        return a[0];
    }
    public int size(){
        return this.tamanho;
    }
    public boolean isEmpty(){
        if (size() == 0);
    }
}