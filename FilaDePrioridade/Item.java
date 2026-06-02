public class Item<T>{
    private int chave;
    private Object elemento;  
    
    public Item(int chave, T elemento){
        this.elemento = elemento;   
        this.chave = chave;
    }

    public int getChave(){
        return this.chave;
    }

    public T getElemento(){
        return this.elemento;
    }
}