package ArvoreBinariaDePesquisa;

public class No<T> {
    Item<T> item;
    No<T> pai;
    No<T> filhoDireito;
    No<T> filhoEsquerdo;

    No(Item<T> item, No<T> pai){
        this.item = item;
        this.pai =  pai;
        this.filhoDireito = null;
        this.filhoEsquerdo = null;
    }
    
    public boolean isRoot(){
        return this.pai == null;
    }

    public boolean isExternal(){
        return filhoDireito == null && filhoEsquerdo == null;
    }

    public boolean isInternal(){
        return !isExternal();
    }

    public Item<T> getItem(){
        return this.item;
    }

    public void setItem(Item<T> novoItem){
        this.item = novoItem;
    }

    public No<T> getParent(){
        return this.pai;
    }

    public void setParent(No<T> novoPai){
        this.pai = novoPai;
    }

    public No<T> getRightChild(){
        return this.filhoDireito;
    }

    public No<T> getLeftChild(){
        return this.filhoEsquerdo;
    }

    public void setRightChild(No<T> novoFilhoDireito){
        this.filhoDireito = novoFilhoDireito;
    }

    public void setLeftChild(No<T> novoFilhoEsquerdo){
        this.filhoEsquerdo = novoFilhoEsquerdo;
    }

    public boolean isLeftChild(){
        if(this.pai == null) 
            return false;
        return this.pai.filhoEsquerdo == this;
    }

    public boolean isRightChild(){
        if(this.pai == null) 
            return false;
        return this.pai.filhoDireito == this;
    }

}
