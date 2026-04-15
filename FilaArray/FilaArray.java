class FilaArray implements Fila{
    int i, f;
    Object a[];
    int capacidade;
    int FC;

    FilaArray(int capacidade){
        this.capacidade = capacidade;
        i = 0;
        f = 0;
        a = new Object[capacidade];
    }

    public boolean isEmpty(){
        return f == i;
    }

    public int size(){
        return ((capacidade - i + f) % capacidade);
    }

    public Object first(){
        if(isEmpty()){
            throw new FilaVaziaException("A fila está vazia");
        }
        return a[i];
    }

    public Object dequeue(){
        if(isEmpty()){
            throw new FilaVaziaException("A fila está vazia.");
        }
        Object resultado=a[i];
        a[i]=null;
        i = (i + 1) % capacidade;
        return resultado;
    }

    public void enqueue(Object o){
        if(size() == capacidade - 1){
            int ii = i;
            int novaCapacidade = capacidade * 2;
            Object b[] = new Object[novaCapacidade];

            for(int j=0; j< size(); j++){
                b[j] = a[ii];
                ii = (ii + 1) % capacidade;
            }
            i=0;
            f=size();
            capacidade = novaCapacidade;
            a=b;
        }
        a[f]= o;
        f=(f+1)% capacidade;
    }
}