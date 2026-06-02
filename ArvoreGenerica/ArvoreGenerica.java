package ArvoreGenerica;

class ArvoreGenerica{
    No raiz;
    No[] filhos;
    int tamanho;

    public ArvoreGenerica(Object o){
        raiz = new No(null, o);
        tamanho = 1;
    }

    public int size(){
        return tamanho;
    }

    public int depth(No n){
        if( n == raiz){
            return 0;
        }else{
            return 1 + depth(n.parent())
        }
    }

    public int height(){
        // exercício
    }

    public boolean isEmpty(){
        // sempre será falso porque não permite deletar o raiz
        return false;
    }

    public No root(){
        return this.raiz;
    }

    

}