public class Item{
    private int chave;
    private Object elemento;  
    
    public Item(int chave, Object elemento){
        this.elemento = elemento;   
        this.chave = chave;
    }

    public int getChave(){
        return this.chave;
    }

    public Object getElemento(){
        return this.elemento;
    }

    public void setChave(int novaChave){
        this.chave = novaChave;
    }

    public void setElemento(Object novoElemento){
        this.elemento = novoElemento;
    }
}