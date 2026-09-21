package ArvoreRubroNegra;
import ArvoreBinariaDePesquisa.ArvoreBP;
import ArvoreBinariaDePesquisa.No;
import ArvoreBinariaDePesquisa.Item;

public class ArvoreRubroNegra<T> extends ArvoreBP<T> {
    public String cor;

    public ArvoreRubroNegra(Item<T> item) {
        super(item);
        this.cor = "vermelho";
    }

    @Override
    public NoRubroNegro<T> createNode(Item<T> item, NoRubroNegro<T> pai) {
        return new NoRubroNegro<>(item, pai);
    }
    
    public String getColor(NoRubroNegro<T> no) {
        return no.getNodeColor();
    }

    public void setColor(NoRubroNegro<T> no, String cor) {
        no.setNodeColor(cor);
    }

    public NoRubroNegro<T> insertRubroNegro(Item<T> item) {
        // insere utilizando como base a inserção da árvore binária de pesquisa
        NoRubroNegro<T> noInserido = (NoRubroNegro<T>) insert((Item<T>) item);
        
        // confere o balanceamento da árvore rubro-negra após a inserção
        updateBalance(noInserido);

        return noInserido;
    }

    // Implementação da aferição do balanceamento da árvore rubro-negra
    public void updateBalance(NoRubroNegro<T> no) {
    }

    // Implementação do balanceamento e das rotações aplicando as regras da árvore rubro-negra
    public void balance(NoRubroNegro<T> no) {
    }
}
