package ArvoreGenerica;
import java.utils.ArrayList;
import java.utils.Iterator;

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
        profundidade = depthRecursive(n);
        
        return profundidade;
    }

    private int depthRecursive(No n){
        if(isRoot(n)){
            return 0;
        }else{
            return 1 + depthRecursive(n.parent())
        }
    }

    // Algoritmo altura(v) – O(n)
    public int height(){
        No noSelecionado = raiz;
        // se (isExternal(v))
        if(isExternal(noSelecionado)){
            // retorne 0
            return 0;
        // senão
        }else{
            // h=0
            int altura = 0;

            Iterator<No> filhos = noSelecionado.children();

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


    public Iterator elements(){
        ArrayList<No> elementos = new ArrayList<>();

        No noSelecionado = raiz;

        elementos.add(noSelecionado);

        preOrder(noSelecionado, elementos);

        return elementos.iterator();

    }

    public Iterator preOrder(No n, ArrayList list){
        if(n == null){
         return list.iterator();
        }

        list.add(n);
        
        Iterator filhos = n.children();

        while(filhos.hasNext()){
            No filho = filhos.next();
            preOrder(filho, list);
        }
    }
    
    public Iterator posOrder(No n, ArrayList list){

    }

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