package ArvoreAVL;

import ArvoreBinariaDePesquisa.ArvoreBP;
import ArvoreBinariaDePesquisa.Item;
import ArvoreBinariaDePesquisa.No;

public class ArvoreAVL<T> extends ArvoreBP<T> {
    private NoAVL<T> paiRemovido;

    public ArvoreAVL(Item<T> item) {
        super(item);
    }

    @Override
    protected No<T> createNode(Item<T> item, No<T> parent) {
        return new NoAVL<T>(item, (NoAVL<T>) parent);
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

        } else if (chave > atual.getItem().getKey()) {

            No<T> novoDireito = removeRec(atual.getRightChild(), chave);

            atual.setRightChild(novoDireito);

            if (novoDireito != null) {
                novoDireito.setParent(atual);
            }

        } else {

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
                No<T> novoDireito = removeRec(
                        atual.getRightChild(),
                        sucessor.getItem().getKey());

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

    // 09/09/2026 - atualmente ele só integra o insert, ainda não refatorei o método
    // para agregar o remove
    public void updateBalanceInsert(NoAVL<T> n) {
        NoAVL<T> noPai = (NoAVL<T>) n.getParent();

        // confere se o nó adicionado não é o raiz
        if (noPai == null) {
            return;
        }

        // incremento ou decremento do FB em uma unidade do nó pai de acordo com a
        // posição de inserção do nó filho
        if (noPai.getRightChild() == n) {
            noPai.setBF(noPai.getBF() - 1);
        } else {
            noPai.setBF(noPai.getBF() + 1);
        }

        // se o FB for igual a zero posso parar a operação
        if (noPai.getBF() == 0) {
            return;
        }
        // primeira condição de balanceamento, se o nó analisado tem FB maior que 1 ou
        // menor que -1
        if (noPai.getBF() < -1 || noPai.getBF() > +1) {
            balance(noPai);
            // se ele estiver em 1 ou -1 eu tenho que analisar o anteceçor dele (no caso o
            // avo)
        } else {
            updateBalanceInsert((NoAVL<T>) noPai);
        }
    }

    // responsável por conferir se a árvore está degenerada após uma remoção
    // e chamar o método de balanceamento, caso necessário
    public void updateBalanceRemove(NoAVL<T> n) {
        NoAVL<T> noPai = (NoAVL<T>) n.getParent();

        if (noPai == null) {
            return;
        }

        if (noPai.getRightChild() == n) {
            noPai.setBF(noPai.getBF() + 1);
        } else if (noPai.getLeftChild() == n) {
            noPai.setBF(noPai.getBF() - 1);
        }

        if (noPai.getBF() == 0) {
            updateBalanceRemove(noPai);
        } else if (noPai.getBF() < -1 || noPai.getBF() > +1) {
            balance(noPai);
        } else {
            return;
        }

    }

    // responsável por realizar as rotações simples e duplas,
    // de acordo com o fator de balanceamento do nó analisado
    public void balance(NoAVL<T> n) {
        NoAVL<T> filhoDireito = (NoAVL<T>) n.getRightChild();
        NoAVL<T> filhoEsquerdo = (NoAVL<T>) n.getLeftChild();
        
        // rotação dupla direita ou simples a direita
        if (n.getBF() > 1) {
            if(filhoEsquerdo.getBF() == -1) {
                leftRotation(filhoEsquerdo);
            }
            rightRotation(n);

        }

        // rotação dupla a esquerda ou simples a esquerda
        else if (n.getBF() < -1 ) {
            if(filhoDireito.getBF() == 1){
                rightRotation(filhoDireito);
            }
            leftRotation(n);
        }
    }

    public void rightRotation(NoAVL<T> n) {
        NoAVL<T> filhoEsquerdo = (NoAVL<T>) n.getLeftChild();
        NoAVL<T> avo = (NoAVL<T>) n.getParent();

        // atualiza o pai do filho esquerdo para o avô do nó que será rotacionado
        filhoEsquerdo.setParent(avo);
        // atualiza o filho direito do filho esquerdo para o nó que será rotacionado
        filhoEsquerdo.setRightChild(n);

        // atualiza o pai do nó que será rotacionado para o filho esquerdo
        n.setParent(filhoEsquerdo);
        // atualiza o filho esquerdo do nó que será rotacionado
        // para o filho direito do filho esquerdo dele
        n.setLeftChild(filhoEsquerdo.getRightChild());

        if (avo != null) {
            // atualiza o filho do avô do nó que será rotacionado para o filho esquerdo
            if (avo.getLeftChild() == n) {
                avo.setLeftChild(filhoEsquerdo);
            // se não, atualiza o filho do avô do nó que será rotacionado para o filho direito
            } else {
                avo.setRightChild(filhoEsquerdo);
            }
        // se o nó que será rotacionado for a raiz, atualiza a raiz para o filho esquerdo
        } else {
            raiz = filhoEsquerdo;
        }
    }

    public void leftRotation(NoAVL<T> n) {
        NoAVL<T> filhoDireito = (NoAVL<T>) n.getRightChild();
        NoAVL<T> avo = (NoAVL<T>) n.getParent();

        // atualiza o pai do filho direito para o avô do nó que será rotacionado
        filhoDireito.setParent(avo);
        // atualiza o filho esquerdo do filho direito para o nó que será rotacionado
        filhoDireito.setLeftChild(n);

        // atualiza o pai do nó que será rotacionado para o filho direito
        n.setParent(filhoDireito);
        // atualiza o filho direito do nó que será rotacionado
        n.setRightChild(filhoDireito.getLeftChild());

        if (avo != null) {
            // atualiza o filho do avô do nó que será rotacionado para o filho direito
            if (avo.getLeftChild() == n) {
                avo.setLeftChild(filhoDireito);
            // se não, atualiza o filho do avô do nó que será rotacionado para o filho esquerdo
            } else {
                avo.setRightChild(filhoDireito);
            }
        // se o nó que será rotacionado for a raiz, atualiza a raiz para o filho direito
        } else {
            raiz = filhoDireito;
        }
    }
}
