class PilhaArray implements Pilha{
    int FC;
    int capacidade;
    int topo;
    Object[] a;

    public PilhaArray(int capacidade, int crescimento){
        this.capacidade = capacidade;
        topo = -1;
        FC = crescimento;
        a = new Object[capacidade];

        public boolean isEmpty(){
            return topo==-1;
        }

        public Object top(){
            if(isEmpty()){
                throw new PilhaVaziaException("A pilha está vazia, top exception.");
            }
        }

        public int size(){
            return topo+1;
        }

        public Object pop(){
            if(isEmpty()){
                throw new PilhaVaziaException("A pilha está vazia, pop exception.")
            }
            Object removido = a[t--];
            return removido;
        }

        public void aumentarPilha(){}

    }
}