public class No{
    private Object elemento;
    private No proximo;

    public No(Object elemento){
        this.elemento = elemento;
        proximo = null;
    }

    public Object getElemento(){
        return elemento;
    }

    public void setElemento(Object elemento){
        this.elemento = elemento;
    }

    public No getProximo(){
        return proximo;
    }

    public void setProximo(No novoNo){
        this.proximo = novoNo; 
    }
}