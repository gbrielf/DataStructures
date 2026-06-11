package ArvoreGenerica;
import java.util.ArrayList;
import java.util.Iterator;

public class ArvoreGenerica{
    protected No raiz;
    protected int tamanho;

    public ArvoreGenerica(Object o){
        raiz = new No(null, o);
        tamanho = 1;
    }

    public No root(){
        return raiz;
    }

    public No parent(No n){
        return n.parent();
    }

    public Iterator children(No n){
        return n.children();
    }

    public boolean isInternal(No n){
        return (n.childrenNumber() != 0);
    }
    
    public boolean isExternal(No n){
        return (n.childrenNumber() == 0);
    }

    public boolean isRoot(No n){
        return n == raiz;
    }

    public void addChild(No n, Object o){}
    
    public Object remove(No n){}

    public void swapElements(No n, No m){}

    public int depth(No n){
        return depthRecursive(n);
    }

    private int depthRecursive(No n){
        if(isRoot(n)){
            return 0;
        } else {
            return 1 + depthRecursive(n.parent());
        }
    }

    // Algoritmo altura(v) – O(n)
    public int height(No n){
        // se (isExternal(v))
        if(isExternal(n)){
            // retorne 0
            return 0;
        // senão
        } else {
            // h=0
            int altura = 0;

            Iterator<No> filhos = n.children();

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

        Iterator resultado = preOrder(noSelecionado, elementos);

        return resultado;

    }

    public Iterator preOrder(No n, ArrayList<No> list){
        if(n == null){
            return list.iterator();
        }

        list.add(n);
        
        Iterator<No> filhos = n.children();

        while(filhos.hasNext()){
            No filho = filhos.next();
            preOrder(filho, list);
        }

        return list.iterator();
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