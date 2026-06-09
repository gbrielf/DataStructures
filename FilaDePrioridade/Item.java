public class Item<T>{
    private int chave;
    private T elemento;  
    
    public Item(int chave, T elemento){
        this.elemento = elemento;   
        this.chave = chave;
    }

    public int getChave(){
        return this.chave;
    }

    public Object getElemento(){
        return this.elemento;
    }
}