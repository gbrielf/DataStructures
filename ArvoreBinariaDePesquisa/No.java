package ArvoreBinariaDePesquisa;

public class No<T> {
    private Item<T> item;
    private No<T> pai;
    private No<T> filhoDireito;
    private No<T> filhoEsquerdo;

    public No(Item<T> item, No<T> pai) {
        this.item = item;
        this.pai = pai;
    }

    public boolean isExternal() {
        return this.filhoEsquerdo == null && this.filhoDireito == null;
    }

    public boolean isInternal() {
        return !isExternal();
    }

    public Item<T> getItem() {
        return this.item;
    }

    public void setItem(Item<T> item) {
        this.item = item;
    }

    public No<T> getParent() {
        return this.pai;
    }

    public void setParent(No<T> pai) {
        this.pai = pai;
    }

    public No<T> getRightChild() {
        return this.filhoDireito;
    }

    public No<T> getLeftChild() {
            return this.filhoEsquerdo;
    }
        
    public void setRightChild(No<T> filhoDireito) {
        this.filhoDireito = filhoDireito;
    }

    public void setLeftChild(No<T> filhoEsquerdo) {
        this.filhoEsquerdo = filhoEsquerdo;
    }

    public boolean isLeftChild() {
        return this.pai != null && this.pai.getLeftChild() == this;
    }

    public boolean isRightChild() {
        return this.pai != null && this.pai.getRightChild() == this;
    }
}