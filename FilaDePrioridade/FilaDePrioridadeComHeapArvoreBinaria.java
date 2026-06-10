package FilaDePrioridade;

public class FilaDePrioridadeComHeapArvoreBinaria<T> implements FilaDePrioridade{
    private No<T> raiz;
    private No<T> ultimo;
    private int tamanho;
    
    public FilaDePrioridadeComHeapArvoreBinaria(){
        this.raiz = null;
        this.ultimo=null;
        this.tamanho = 0;
    }

    public int size(){
        return this.tamanho;
    }
    
    public boolean isEmpty(){
        return tamanho == 0;
    }

    public int min(){
        return raiz.item.getKey();
    }

    public void insert(int k, Object o){

        Item<T> novoItem = new Item(k,o);

        if(isEmpty()){
            No<T> novoNo = new No<T>(novoItem, null);
        
            this.ultimo = novoNo;
        
            this.raiz = novoNo;
        
            tamanho++;
        
            return;
        }
        
        No<T> pai = newLastNode(this.ultimo);
        
        No<T> novoUltimo = new No(novoItem, pai);
        
        this.ultimo = novoUltimo;

        if(pai.getLeftChild() != null){
            pai.setLeftChild(this.ultimo);
        }
        else if(pai.getLeftChild() == null){
            pai.setRightChild(this.ultimo);
        }

        this.tamanho++;

        upHeap(this.ultimo);
    }

    private No<T> newLastNode(No<T> n){

        while(n.isRightChild() && !n.isRoot()){
            n = n.getParent();
        }

        if(!n.isRoot()){
            n = n.getParent().getRightChild();
        }

        while(n.getLeftChild() != null){
            n = n.getLeftChild();
        }

        return n;        
    } 


    public Object removeMin(){

        Item<?> itemAntigo = this.raiz.getItem();
        
        this.raiz.setItem(this.ultimo.getItem());
        
        No<?> pai = this.ultimo.getParent();

        if(ultimo == pai.getRightChild()){
            pai.setRightChild(null);
        }else{
            pai.setLeftChild(null);
        }
        
        downHeap(this.raiz);

        while(!this.ultimo.isRightChild() && !this.ultimo.isRoot()){
            this.ultimo = this.ultimo.getParent();
        }

        if(!this.ultimo.isRoot()){
            this.ultimo = this.ultimo.getParent().getLeftChild();
        }

        while(this.ultimo.getLeftChild() != null){
            this.ultimo = this.ultimo.getLeftChild();
        }

        this.tamanho--;

        return itemAntigo.getElemento();
    }

    public void upHeap(No<T> n) throws OrderQueueInvalidIndexException{
        if(n.isRoot()){
            return;
        }
        
        No<T> pai = n.getParent();

        if(n.getItem().getKey() < pai.getItem().getKey()){
            swapItens(n, pai);
            upHeap(pai);
        }
    }

    public void downHeap(No<T> n){
        No<T> direito = n.getRightChild();
        No<T> esquerdo = n.getLeftChild();
        No<T> menor = null;

        if (esquerdo == null && direito == null){
            return;
        }
        if(direito == null){
            if(compare(esquerdo, n)){
                swapItens(esquerdo, n);
                downHeap(esquerdo);
            }
            return;
        }
        if(compare(esquerdo, direito)){
            menor = esquerdo;
        } else {
            menor = direito;
        }

        if(compare(menor, n)){
            swapItens(menor, n);
            downHeap(menor);
        }
    }

    private void swapItens(No<T> n, No<T> m){
        Item<T> aux = n.getItem();
        n.setItem(m.getItem());
        m.setItem(aux);
    }

    private boolean compare(No<T> filho, No<T> pai) {
        Item<?> itemFilho =  filho.getItem();
        Item<?> itemPai =  pai.getItem();

        int chaveFilha =  itemFilho.getKey();
        int chavePai =  itemPai.getKey();

        return (chavePai > chaveFilha);
    }
}