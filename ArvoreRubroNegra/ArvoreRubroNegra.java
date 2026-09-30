package ArvoreRubroNegra;
import ArvoreAVL.NoAVL;
import ArvoreBinariaDePesquisa.ArvoreBP;
import ArvoreBinariaDePesquisa.No;
import ArvoreBinariaDePesquisa.Item;

public class ArvoreRubroNegra<T> extends ArvoreBP<T> {
    public String cor;
    private NoRubroNegro<T> paiDoRemovido;

    public ArvoreRubroNegra(Item<T> item) {
        super(item);
        this.cor = "vermelho";
    }

    @Override
    public NoRubroNegro<T> createNode(Item<T> item, No<T> pai) {
        return new NoRubroNegro<>(item, pai);
    }

    // Retorna o nó removido de forma recursiva da árvore rubro negra 
    public void removeRB(int chave){
        paiDoRemovido = null;

        NoRubroNegro<T> atual = (NoRubroNegro<T>) getRoot();

        paiDoRemovido = (NoRubroNegro<T>) removeRec(atual,chave);
        if( paiDoRemovido != null){
            updateBalanceRemove(paiDoRemovido);
        }
    }

    // o mesmo método utilizado em avl
    @Override 
    protected No<T> removeRec(
        No<T> atual, 
        int chave) {

        if(atual == null){
            return null;
        }
        // Se a chave for menor que a chave do nó atual, vá para a subárvore esquerda
        if(chave < atual.getItem().getKey()){
            No<T> novoEsquerdo = removeRec(atual.getLeftChild(), chave);
            
            atual.setLeftChild(novoEsquerdo);

            if(novoEsquerdo != null){
                novoEsquerdo.setParent(atual);
            }
        // Se a chave for maior que a chave do nó atual, vá para a subárvore direita
        }else if(chave > atual.getItem().getKey()){
            No<T> novoDireito = removeRec(atual.getRightChild(), chave);

            atual.setRightChild(novoDireito);

            if(novoDireito != null){
                novoDireito.setParent(atual);
            }
        // Se a chave for igual à chave do nó atual, encontramos o nó a ser removido
        }else{
            // quando esse nó não tiver filhos, apenas o remove
            if(atual.getLeftChild() == null){
                // Quando atual é uma folha, novoEsquerdo pode ser null.
                // Além disso, o pai importante é o pai do nó fisicamente removido.
                // Capturei a referência antes de retornar o filho
                paiDoRemovido = (NoRubroNegro<T>) atual.getParent();

                No<T> filho = atual.getRightChild();

                if(filho != null){
                    filho.setParent(atual.getParent());
                }

                return filho;
            }
            else if (atual.getRightChild() == null){
                // Capturamos o pai do nó que será fisicamente removido
                // antes de retornar o filho.
                paiDoRemovido = (NoRubroNegro<T>) atual.getParent();

                No<T> filho = atual.getLeftChild();

                if (filho != null) {
                    filho.setParent(atual.getParent());
                }

                return filho;
            }
            else {
                // Nó com dois filhos
                NoRubroNegro<T> sucessor = (NoRubroNegro<T>) smallestNode(atual);
                
                atual.setItem(sucessor.getItem());

                paiDoRemovido = (NoRubroNegro<T>) sucessor.getParent();

                No<T> novoDireito = removeRec(atual.getRightChild(), sucessor.getItem().getKey());

                if(novoDireito != null){
                    novoDireito.setParent(atual);
                }
            }
        }

        return atual;
    }
    
    
    public String getColor(NoRubroNegro<T> n) {
        return n.getNodeColor();
    }

    public void setColor(NoRubroNegro<T> n, String cor) {
        n.setNodeColor(cor);
    }

    public NoRubroNegro<T> insertRB(Item<T> item) {
        // insere utilizando como base a inserção da árvore binária de pesquisa
        NoRubroNegro<T> noInserido = (NoRubroNegro<T>) insert((Item<T>) item);
        
        // confere o balanceamento da árvore rubro-negra após a inserção
        updateBalanceInsert(noInserido);

        return noInserido;
    }


    // Implementação da aferição do balanceamento da árvore rubro-negra
    public void updateBalanceInsert(NoRubroNegro<T> n) {
        // Caso 0: Se o nó inserido for a raiz, apenas pinta de preto
        if(n.isRoot()){
            n.setNodeColor("preto");
            return;
        }
        // Caso 1: Se o nó inserido for vermelho e o pai for vermelho, precisa balancear
        else if(n.getNodeColor() == "vermelho" && n.getParent().getNodeColor() == "vermelho") {
            // Caso 1: Tio vermelho
            if(n.getSibling().getNodeColor() == "vermelho"){
                    n.getParent().setNodeColor("preto");
                    n.getSibling().setNodeColor("preto");
                    n.getGrandParent().setNodeColor("preto");
            }else{
                // Caso 2: Tio preto
                balanceInsert(n);
            }
            
        }
    }

    public void updateBalanceRemove(NoRubroNegro<T> n){

    }

    // Implementação do balanceamento e das rotações aplicando as regras da árvore rubro-negra para inserção
    public void balanceInsert(NoRubroNegro<T> n) {
        if(n.isLeftChild() && n.getParent().isLeftChild()){
            if(n.getGrandParent() != null){
                rightRotation(n.getGrandParent());
                n.getParent().setNodeColor("preto");
                n.getSibling().setNodeColor("vermelho");
            }
        }else if(n.isRightChild() && n.getParent().isRightChild()){
            if(n.getGrandParent() != null){
                leftRotation(n.getGrandParent());
                n.getParent().setNodeColor("preto");
                n.getSibling().setNodeColor("vermelho");
            }
        }else if(n.isLeftChild() && n.getParent().isRightChild()){
            rightRotation(n.getParent());
            leftRotation(n.getGrandParent());
            n.setNodeColor("preto");
            n.getSibling().setNodeColor("vermelho");
        }else if(n.isRightChild() && n.getParent().isLeftChild()){
            leftRotation(n.getParent());
            rightRotation(n.getGrandParent());
            n.setNodeColor("preto");
            n.getSibling().setNodeColor("vermelho");
        }
    }

    public void leftRotation(NoRubroNegro<T> n) {
        // Realiza a rotação à esquerda em torno do nó fornecido
        NoRubroNegro<T> filhoDireito = n.getRightChild();
        // Atualiza os ponteiros dos filhos e pais
        n.setRightChild(filhoDireito.getLeftChild());
        // Atualiza o pai do filho esquerdo do filho direito, se existir
        if (filhoDireito.getLeftChild() != null) {
            filhoDireito.getLeftChild().setParent(n);
        }
        // Atualiza o pai do filho direito para o pai do nó
        filhoDireito.setParent(n.getParent());
        // Atualiza o pai do nó para o filho direito
        if (n.isRoot()) {
            this.raiz = filhoDireito;
        } else if (n.isLeftChild()) {
            n.getParent().setLeftChild(filhoDireito);
        } else {
            n.getParent().setRightChild(filhoDireito);
        }
        filhoDireito.setLeftChild(n);
        n.setParent(filhoDireito);
    }

    public void rightRotation(NoRubroNegro<T> n) {
        // Realiza a rotação à direita em torno do nó fornecido
        NoRubroNegro<T> filhoEsquerdo = n.getLeftChild();
        // Atualiza os ponteiros dos filhos e pais
        n.setLeftChild(filhoEsquerdo.getRightChild());
        // Atualiza o pai do filho direito do filho esquerdo, se existir
        if (filhoEsquerdo.getRightChild() != null) {
            filhoEsquerdo.getRightChild().setParent(n);
        }
        // Atualiza o pai do filho esquerdo para o pai do nó
        filhoEsquerdo.setParent(n.getParent());
        // Atualiza o pai do nó para o filho esquerdo
        if (n.isRoot()) {
            this.raiz = filhoEsquerdo;
        } else if (n.isLeftChild()) {
            n.getParent().setLeftChild(filhoEsquerdo);
        } else {
            n.getParent().setRightChild(filhoEsquerdo);
        }
        filhoEsquerdo.setRightChild(n);
        n.setParent(filhoEsquerdo);
    }

}