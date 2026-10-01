package ArvoreRubroNegra;
import ArvoreBinariaDePesquisa.No;
import ArvoreBinariaDePesquisa.Item;

public class NoRubroNegro<T> extends No<T> {
    public String cor;
    public boolean duploNegro;

    public NoRubroNegro(Item<T> item, No<T> pai) {
        super(item, pai);
        this.cor = "vermelho"; // Novo nó é sempre vermelho
        this.duploNegro = false;
    }

    @Override
    public NoRubroNegro<T> getLeftChild() {
        return (NoRubroNegro<T>) super.getLeftChild();
    }

    @Override 
    public NoRubroNegro<T> getRightChild() {
        return (NoRubroNegro<T>) super.getRightChild();
    }

    @Override 
    public NoRubroNegro<T> getParent() {
        return (NoRubroNegro<T>) super.getParent();
    }

    public NoRubroNegro<T> getGrandParent() {
        NoRubroNegro<T> pai = getParent();
        
        if(pai == null){
            return null;
        }

        return pai.getParent();
    }

    @Override 
    public NoRubroNegro<T> getSibling() {
        return (NoRubroNegro<T>) super.getSibling();
    }

    public boolean isDoubleBlack(){
        return this.duploNegro;
    }

    public void setDoubleBlack(boolean duploNegro){
        this.duploNegro = duploNegro;
    }

    public String getNodeColor() {
        return cor;
    }

    public void setNodeColor(String cor) {
        this.cor = cor;
    }
}
