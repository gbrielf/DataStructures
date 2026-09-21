package ArvoreRubroNegra;
import ArvoreBinariaDePesquisa.No;
import ArvoreBinariaDePesquisa.Item;

public class NoRubroNegro<T> extends No<T> {
    public String cor;

    public NoRubroNegro(Item<T> item, No<T> pai) {
        super(item, pai);
        this.cor = "vermelho"; // Novo nó é sempre vermelho
    }

    public String getNodeColor() {
        return cor;
    }

    public void setNodeColor(String cor) {
        this.cor = cor;
    }
}
