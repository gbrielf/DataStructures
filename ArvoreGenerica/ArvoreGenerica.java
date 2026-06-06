package ArvoreGenerica;
import java.util.ArrayList;
import java.util.Iterator;

class ArvoreGenerica{
    public No raiz;
    public int tamanho;

    public ArvoreGenerica(Object o){
        this.raiz = new No(null, o);
        this.tamanho = 1;
    }

    public int size(){
        return this.tamanho;
    }

    public boolean isEmpty(){
        // sempre será falso porque não permite deletar o raiz
        return tamanho == 0;
    }

    public No getRoot() throws EmptyTreeException {
        if(isEmpty()){
            throw new EmptyTreeException("A árvore está vazia.");
        }
        return this.raiz;
    }

    public boolean isRoot(No n) throws InvalidTreePositionException{
        if(n == null){
            throw new InvalidTreePositionException("Nó inválido: nulo.");
        }
        return n == this.raiz;
    }

    public No parent(No n) throws InvalidTreePositionException{
        if(n == null){
            throw new InvalidTreePositionException("Nó inválido: nulo.");
        }
        return n.getParent();
    }

    public No addChild(No n, Object o) throws InvalidTreePositionException{
        if(n == null){
            throw new InvalidTreePositionException("Nó inválido: nulo.");
        }

        No novoNo = new No(n, o);
        
        n.setChild(novoNo);
        
        this.tamanho++;

        return novoNo;
    }

    public Iterator children(No n){
        return n.getChildren();
    }

    public boolean isInternal(No n){
        return (n.childrenNumber() != 0);
    }
    
    public boolean isExternal(No n){
        return (n.childrenNumber() == 0);
    }

    
    public Object remove(No n) throws InvalidTreePositionException{
        if(n == null){
            throw new InvalidTreePositionException("Nó inválido: nulo");
        }
        else if(isRoot(n)){
            throw new InvalidTreePositionException("A árvore está vazia e só possui a raiz, a raiz não pode ser removida.")
        }

        No pai = n.getParent();

        if(isExternal(n)){
            Object resultado = n.getElement();
            pai.removeChild(n);
            this.tamanho--;
            return resultado;
        }
        else{
            throw new InvalidTreePositionException("A árvore genérica só permite a remoção de nós externos.");
        }
    }

    public void swapElements(No n, No m){
        Object tempObject = n.getElement();
        n.setElement(m.getElement());
        m.setElement(tempObject);
    }

    public int depth(No n){
        return depthRecursive(n);
    }

    private int depthRecursive(No n){
        if(isRoot(n)){
            return 0;
        } else {
            return 1 + depthRecursive(n.getParent());
        }
    }

    // Algoritmo altura(v) – O(n)
    public int height(No n){
        // se (isExternal(v))
        if(isEmpty()){
            throw new EmptyTreeException("A árvore está vazia, altura zero.")
        }
        if(isExternal(n)){
            // retorne 0
            return 0;
        // senão
        } else {
            // h=0
            int altura = 0;

            Iterator<No> filhos = n.getChildren();

            // para cada w em children(v)
            while(filhos.hasNext()){
                No filho = filhos.next();
                // h=max(h,altura(w))
                altura = Math.max(altura, height(filho));
            }

            // retorne 1+h
            return 1 + altura;
        }
    }


    public Iterator<No> elements(){
        ArrayList<No> elementos = new ArrayList<>();

        No noSelecionado = raiz;

        Iterator<No> resultado = preOrder(noSelecionado, elementos);

        return resultado;

    }

    public Iterator<No> preOrder(No n, ArrayList<No> list){
        if(n == null){
            return list.iterator();
        }

        list.add(n);
        
        Iterator<No> filhos = n.getChildren();

        while(filhos.hasNext()){
            No filho = filhos.next();
            preOrder(filho, list);
        }

        return list.iterator();
    }
    
    public Iterator<No> posOrder(No n, ArrayList list){

    }

    public Iterator nos(){

    }

    public Object replace(No n, Object o){

    } 
}