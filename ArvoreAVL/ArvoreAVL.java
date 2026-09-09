package ArvoreAVL;
import java.util.Iterator;
import java.util.ArrayList;
import ArvoreBinariaDePesquisa.ArvoreBP;
import ArvoreBinariaDePesquisa.Item;

public class ArvoreAVL<T> extends ArvoreBP<T>{
    int FB;
    public ArvoreAVL(Item<T> item){
        super(item);
        FB = 0;
    }

    @Override
    protected ArvoreBinariaDePesquisa.No<T> createNode(Item<T> item, ArvoreBinariaDePesquisa.No<T> parent){
        return new No<T>(item, (No<T>)parent);
    }

    public void insertAVL(Item<T> item) {
        No<T> noInserido = (No<T>) insert(item);
        updateBalance(noInserido);
    }

    public void removeAVL(int chave) {}

    public void updateBalance(No<T> n) {
        No<T> noPai = (No<T>) n.getParent();

        // confere se o nó adicionado não é o raiz
        if(noPai == null){
            return;
        }

        // incremento ou decremento do FB em uma unidade do nó pai de acordo com a posição de inserção do nó filho
        if(noPai.getRightChild() == n){
            noPai.setBF(noPai.getBF() - 1);
        }else{
            noPai.setBF(noPai.getBF() + 1);
        }

        // se o FB for igual a zero posso parar a operação
        if(noPai.getBF() == 0){
            return;
        }
        // primeira condição de balanceamento, se o nó analisado tem FB maior que 1 ou menor que -1
        if(noPai.getBF() < -1 || noPai.getBF() > +1){
            balance(noPai);
        // se ele estiver em 1 ou -1 eu tenho que analisar o anteceçor dele (no caso o avô)
        }else{
            updateBalance((No<T>) noPai);
        }
    }

    public void balance(No<T> n){}
    
}
