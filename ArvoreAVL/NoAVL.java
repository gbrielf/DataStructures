package ArvoreAVL;
import ArvoreBinariaDePesquisa.Item;

public class NoAVL<T> extends ArvoreBinariaDePesquisa.No<T> {
    private int BF; // Fator de Balanceamento

    public NoAVL(Item<T> item, ArvoreBinariaDePesquisa.No<T> pai) {
        super(item, pai);
        this.BF = 0;
    }

    public int getBF() {
        return this.BF;
    }

    public void setBF(int BF) {
        this.BF = BF;
    }
}