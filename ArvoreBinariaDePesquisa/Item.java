package ArvoreBinariaDePesquisa;

public class Item<T>{
    protected int chave;
    protected T elemento;  
    
    public Item(int chave, T elemento){
        this.elemento = elemento;   
        this.chave = chave;
    }

    public int getKey(){
        return this.chave;
    }

    public T getElemento(){
        return this.elemento;
    }

    public void setElemento(T elemento){
        this.elemento = elemento;
    }
}