package ArvoreGenerica;

class ArvoreGenerica{
    No raiz;
    int tamanho;

    public ArvoreGenerica(Object o){
        raiz = new No(null, o);
        tamanho = 1;
    }

    public No root(){
        return raiz;
    }

    public No parent(No n){
        return (v.parent());
    }

    public Iterator children(No n){
        return n.children();
    }

    public boolean isInternal(No n){
        return (n.getFilhos().length() != 0);
    }
    
    public boolean isExternal(No n){
        return (n.getFilhos().length() == 0);
    }

    public boolean isRoot(No n){
        return n == raiz;
    }

    public void addChild(No n, Object o){}
    
    public Object remove(No n){}

    public void swapElements(No n, No m){}

    public int depth(No n){
        if(isRoot(n)){
            return 0;
        }else{
            return 1 + depth(n.parent())
        }
    }

    private int depthRecursive(No n){}

    public int height(){}


    public Iterator elements(){}

    public Iterator nos(){}

    public int size(){
        return tamanho;
    }

    public boolean isEmpty(){
        // sempre será falso porque não permite deletar o raiz
        return false;
    }

    public Object replace(No n, Object o){} 
}