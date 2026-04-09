class FilaComListaLigada{
    private No inicio;
    private No fim;
    private int tamanho;

    public FilaComListaLigada(){
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    public void Enqueue(Object elemento){
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

    public No Dequeue(){
        if (inicio == null){
            throw new FilaVaziaException("A fila está vazia.");
        }
        No elementoRemovido = inicio.getElemento();
        inicio = inicio.getProximo();
        
        if(inicio == null){
            fim == null;
        }

        return elementoRemovido;
    }
}