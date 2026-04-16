package FilaArrayComReversao;

class FilaArrayComReversao implements FilaComReversao{
    private int i;
    private int f;
    private int capacidade;
    private Object[] a;
    private boolean reverter;

    public FilaArrayComReversao(int capacidade){
        this.capacidade = capacidade;
        i = 0;
        f = 0;
        a = new Object[capacidade];
        reverter = false; 
    }

    public void reverse(){
        if(i != f){
            reverter = !reverter;
        }
    }

    public void enqueue(Object elemento){
        // evita que a lógica do isEmpty quebre (i == f)
        if(isFull()){
            resize();
        }
        if(!reverter){
            a[f] = elemento;
            f = (f + 1) % capacidade;

        }else{
            i = (capacidade + i - 1) % capacidade;          
            a[i] = elemento;                                    
        }
    }

    public Object dequeue(){
        if(isEmpty()){
            throw new FilaVaziaException("A fila está vazia.");
        }
        if(size() <= (capacidade - 1) / 3){
            resize();
        }

        if(!reverter){
            Object elementoRemovido = a[i];
            a[i] = null;
            i = (i + 1) % capacidade;
            return elementoRemovido;
        }else{
            f = (capacidade + f - 1) % capacidade;
            Object elementoRemovido = a[f];
            a[f] = null;
            return elementoRemovido;
        }
    }

    public int size(){
        return ((capacidade - i + f) % capacidade);
    }

    public Object first(){
        if(isEmpty()){
            throw new FilaVaziaException("A fila está vazia;");
        }
        if(!reverter){
            return a[i];
        }else{
            int ff = (capacidade + f - 1 ) % capacidade;
            return a[ff];
        }
    }

    public boolean isEmpty(){
        return i == f;
    }

    public boolean isFull(){
        return size() == capacidade - 1;
    }

     public void resize(){
            int novaCapacidade = capacidade;
            int tamanhoAtual = size();

 
            if(tamanhoAtual == capacidade - 1){
                novaCapacidade = capacidade * 2;
            }else if(tamanhoAtual > 0 && tamanhoAtual <= capacidade/3 ){
                novaCapacidade = capacidade / 2;
            }

            Object[] b = new Object[novaCapacidade];
            int contador;

            if(!reverter){
                contador = i;
            }
            else{
                contador = (f-1+capacidade)%capacidade;
            }            

            for(int j = 0; j < tamanhoAtual ; j++){
                b[j] = a[contador];

                if(!reverter){
                    contador = (contador + 1) % capacidade;
                }else{
                    contador = (capacidade + contador - 1) % capacidade;
                }
            }
            a = b;
            capacidade = novaCapacidade;
            i = 0;
            f = tamanhoAtual;
            reverter = false;
    }
}