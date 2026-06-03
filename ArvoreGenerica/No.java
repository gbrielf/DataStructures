package ArvoreGenerica;
import java.util.Iterator;
import java.util.ArrayList;

public class No{
    private Object o;
    private No pai;
    private ArrayList filhos = new ArrayList();
    
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

    public void addChild(No o){
        // método de lista duplamente ligada
        filhos.add(o);
    }

    public void removeChild(No o){
        // método de lista duplamente ligada
        filhos.remove(o);
    }

    public int childrenNumber(){
        return filhos.size();
    }

    public Iterator children(){
        return filhos.iterator();
    }

}