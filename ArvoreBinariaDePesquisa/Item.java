package ArvoreBinariaDePesquisa;


public class Item<T> {
    private int chave;
    private T elemento;

    public Item(int chave, T elemento) {
        this.chave = chave;
        this.elemento = elemento;
    }

    public int getKey() {
        return this.chave;
    }

    public void setKey(int chave) {
        this.chave = chave;
    }

    public T getElement() {
        return this.elemento;
    }

    public void setElement(T elemento) {
        this.elemento = elemento;
    }
}