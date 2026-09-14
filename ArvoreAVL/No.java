package ArvoreAVL;
import ArvoreBinariaDePesquisa.Item;

public class No<T> extends ArvoreBinariaDePesquisa.No<T>{
    private int FB; // Fator de Balanceamento

    public No(Item<T> item, No<T> pai) {
        super(item, pai);
        this.FB = 0;
    }

    public int getBF() {
        return this.FB;
    }

    public void setBF(int FB) {
        this.FB = FB;
    }

}
