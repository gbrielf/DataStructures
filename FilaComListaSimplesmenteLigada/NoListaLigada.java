public class NoListaLigada{
    private Object elemento;
    private NoListaLigada proximo;

    public NoListaLigada(Object elemento){
        this.elemento = elemento;
        proximo = null;
    }

    public Object getElemento(){
        return elemento;
    }

    public void setElemento(Object elemento){
        this.elemento = elemento;
    }

    public NoListaLigada getProximo(){
        return proximo;
    }

    public void setProximo(NoListaLigada novoNo){
        this.proximo = novoNo; 
    }
}