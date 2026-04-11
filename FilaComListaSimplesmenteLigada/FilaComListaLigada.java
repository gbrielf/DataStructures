class FilaComListaLigada{
    private No inicio;
    private No fim;
    private int tamanho;

    public FilaComListaLigada(){
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public void enqueue(Object elemento){
        No novoNo = new No(elemento);

        if(inicio == null){
            inicio = novoNo;
            fim = novoNo;
        }else{
            fim.setProximo(novoNo);
            fim = novoNo;
        }
        tamanho++;
    }

    public Object dequeue(){
        if (isEmpty()){
            throw new FilaVaziaException("A fila está vazia.");
        }
        Object elementoRemovido = inicio.getElemento();
        inicio = inicio.getProximo();
        
        if(inicio == null){
            fim = null;
        }
        tamanho--;
        return elementoRemovido;
    }

    public Object first(){
        return inicio.getElemento();
    }

    public int size(){
        return tamanho;
    }

    public boolean isEmpty(){
        return (inicio == null);
    }


}