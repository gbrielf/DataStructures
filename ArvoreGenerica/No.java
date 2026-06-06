package ArvoreGenerica;
import java.util.Iterator;
import java.util.ArrayList;

public class No{
    private Object o;
    private No pai;
    private ArrayList<No> filhos = new ArrayList<No>();
    
    public No(No pai, Object o){
        this.pai = pai;
        this.o = o;
    }

    public Object getElement(){
        return o;
    }

    public void setElement(Object o){
        this.o = o;
    }

    public No getParent(){
        return pai;
    }

    public void setParent(No n){
        this.pai = n;
    }

    public void setChild(No o){
        // método de lista duplamente ligada
        filhos.add(o);
    }

    public void removeChild(No o){
        // método remove de Array List nativo do Java
        filhos.remove(o);
    }

    public int childrenNumber(){
        return filhos.size();
    }

    public Iterator<No> getChildren(){
        return filhos.iterator();
    }

}