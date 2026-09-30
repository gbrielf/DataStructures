package ArvoreRubroNegra;
import ArvoreBinariaDePesquisa.No;
import ArvoreBinariaDePesquisa.Item;

public class NoRubroNegro<T> extends No<T> {
    public String cor;

    public NoRubroNegro(Item<T> item, No<T> pai) {
        super(item, pai);
        this.cor = "vermelho"; // Novo nó é sempre vermelho
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
        return (NoRubroNegro<T>) getParent().getParent();
    }

    @Override 
    public NoRubroNegro<T> getSibling() {
        return (NoRubroNegro<T>) super.getSibling();
    }

    public String getNodeColor() {
        return cor;
    }

    public void setNodeColor(String cor) {
        this.cor = cor;
    }
}
