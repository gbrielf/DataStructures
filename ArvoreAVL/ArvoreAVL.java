package ArvoreAVL;
import ArvoreBinariaDePesquisa.ArvoreBP;
import ArvoreBinariaDePesquisa.Item;
import ArvoreBinariaDePesquisa.No;

public class ArvoreAVL<T> extends ArvoreBP<T>{
    private int FB;
    private NoAVL<T> paiRemovido;

    public ArvoreAVL(Item<T> item){
        super(item);
        FB = 0;
    }

    @Override
    protected No<T> createNode(Item<T> item, No<T> parent){
        return new NoAVL<T>(item, (NoAVL<T>)parent);
    }

    @Override
    protected No<T> removeRec(
            No<T> atual,
            int chave) {

        if (atual == null) {
            return null;
        }

        if (chave < atual.getItem().getKey()) {

            No<T> novoEsquerdo = removeRec(atual.getLeftChild(), chave);

            atual.setLeftChild(novoEsquerdo);

            if (novoEsquerdo != null) {
                novoEsquerdo.setParent(atual);
            }

        }
        else if (chave > atual.getItem().getKey()) {

            No<T> novoDireito = removeRec(atual.getRightChild(), chave);

            atual.setRightChild(novoDireito);

            if (novoDireito != null) {
                novoDireito.setParent(atual);
            }

        }
        else {

            // ENCONTROU O NÓ QUE SERÁ REMOVIDO
            if (atual.getLeftChild() == null) {
                // Quando atual é uma folha, novoEsquerdo pode ser null.
                // Além disso, o pai importante é o pai do nó fisicamente removido.
                // Capturei a referência antes de retornar o filho
                paiRemovido = (NoAVL<T>) atual.getParent();

                No<T> filho = atual.getRightChild();

                if (filho != null) {
                    filho.setParent(atual.getParent());
                }

                return filho;
            }

            else if (atual.getRightChild() == null) {

                
                // Capturamos o pai do nó que será fisicamente removido
                // antes de retornar o filho.                
                paiRemovido = (NoAVL<T>) atual.getParent();

                No<T> filho = atual.getLeftChild();

                if (filho != null) {
                    filho.setParent(atual.getParent());
                }

                return filho;
            }

            else {

                // NÓ COM DOIS FILHOS
                NoAVL<T> sucessor = (NoAVL<T>) smallestNode(atual);

                
                // O sucessor não é a raiz da árvore.
                // Apenas utilizamos o Item do sucessor no nó atual.
                atual.setItem(sucessor.getItem());

                
                // O pai que precisamos guardar é o pai do sucessor,
                // pois é nessa subárvore que ocorrerá a remoção física.
                paiRemovido = (NoAVL<T>) sucessor.getParent();

                
                // Agora removemos fisicamente o sucessor da subárvore direita.
                No<T> novoDireito =
                        removeRec(
                            atual.getRightChild(),
                            sucessor.getItem().getKey()
                        );

                atual.setRightChild(novoDireito);

                if (novoDireito != null) {
                    novoDireito.setParent(atual);
                }
            }
        }

        return atual;
    }

    public void insertAVL(Item<T> item) {
        NoAVL<T> noInserido = (NoAVL<T>) insert(item);
        updateBalanceInsert(noInserido);
    }

    public void removeAVL(int chave) {
        // Limpa a referência antes de iniciar uma nova remoção,
        // porque ela pode estar registrando um valor de operações anteriores
        paiRemovido = null;

        // removeRec retorna a nova raiz da subárvore.
        // Por isso precisamos guardar o retorno em raiz.
        raiz = removeRec(getRoot(), chave);

        // Se o elemento removido era a raiz, paiRemovido será null.
        if (paiRemovido != null) {
            updateBalanceRemove(paiRemovido);
        }
    }

    // 09/09/2026 - atualmente ele só integra o insert, ainda não refatorei o método para agregar o remove
    public void updateBalanceInsert(NoAVL<T> n) {
        NoAVL<T> noPai = (NoAVL<T>) n.getParent();

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
            updateBalanceInsert((NoAVL<T>) noPai);
        }
    }

    public void updateBalanceRemove(NoAVL<T> n){
        NoAVL<T> noPai = (NoAVL<T>) n.getParent();

        if (noPai == null){
            return;
        }

        if(noPai.getRightChild() == n){
            noPai.setBF(noPai.getBF() + 1);
        }
        else if(noPai.getLeftChild() == n){
            noPai.setBF(noPai.getBF() - 1); 
        }

        if(noPai.getBF() == 0){
            updateBalanceRemove(noPai);
        }
        else if(noPai.getBF() < -1 || noPai.getBF() > +1){
            balance(noPai);
        }else{
            return;
        }

    }

    public void balance(NoAVL<T> n){
        // rotação simples a direita
        // rotação simples a esquerda
        // rotação dupla a direita
        // rotação dupla a esquerda
    }
    
}
