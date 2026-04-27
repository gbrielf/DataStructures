package Lista;

public class No {
    private No anterior;
    private No proximo;
    private Object elemento;

    public No(Object elemento){
        this.anterior = null;
        this.proximo = null;
        this.elemento = elemento;
    }

    public No getPrevious(){
        return anterior;
    }

    public No getNext(){
        return proximo;
    }

    public Object getElement(){
        return elemento;
    }

    public void setPrevious(No anterior){
        this.anterior = anterior;
    }

    public void setNext(No proximo){
        this.proximo = proximo;
    }

    public void setElement(Object elemento){
        this.elemento = elemento;
    }
}
