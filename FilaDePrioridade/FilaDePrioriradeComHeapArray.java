public class FilaDePrioridadeComHeapArray{
    private int tamanho;
    private Item[] a;
    private int capacidade;
    
    public FilaDePrioridadeComHeapArray(int capacidade){
        this.capacidade = capacidade;
        this.a = new Item[capacidade + 1];
        this.tamanho = 1;
    }

    public void insert(int k, Object o){
        if(tamanho > capacidade){
            throw new RuntimeException("Fila cheia!")
        }
        
        Item novoItem = new Item(k, o);
        
        a[tamanho] = novoItem;
        int i = tamanho;
        tamanho++;

        while(i>1){
            int pai = i/2;

            if(a[pai].getChave() <= a[i].getChave()){
                break
            }

            Item aux = a[i];
            a[i] = a[pai];
            a[pai] = aux;

            i = pai;
        }

    }

    // remove e retorna o item com a menor chave
    public Object removeMin(){
        if(isEmpty()){
            throw new OrderQueueIsEmptyException("A fila está vazia.");
        }

        Object resultado = a[1];
        
        a[1] = a[tamanho-1];
        a[tamanho-1] = null;
        tamanho--;
        

        int i = 1;

        while(true){

            int esquerda = 2 * i;
            int direita = (2 * i) + 1;

            if(esquerda >= tamanho){
                break;
            }

            int menorFilho = esquerda;

            if (direita < tamanho && a[direita].getChave() < a[esquerda].getChave()){
                menorFilho = direita;
            }

            // raiz nova é menor que os dois filhos
            if(a[i].getChave() <= a[menorFilho].getChave()){
                break;
            }

            Object aux = a[i];
            a[i] = a[menorFilho];
            a[menorFilho] = aux;

            i = menorFilho;
        }

        return resultado.getElemento();
    }

    public int min(){
        if(isEmpty()){
            throw new OrderQueueIsEmptyException("A filha está vazia.");
        }

        return a[1].getElemento();
    }
    public int size(){
        return this.tamanho - 1;
    }
    public boolean isEmpty(){
        if tamanho == 1;
    }
}