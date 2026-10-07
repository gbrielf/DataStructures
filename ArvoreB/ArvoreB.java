package ArvoreB;

import ArvoreBinariaDePesquisa.Item;

public class ArvoreB<T> {

    private NoB<T> raiz;
    // Ordem da árvore
    private final int t;

    public ArvoreB(int t) {

        if (t < 2) {
            throw new IllegalArgumentException(
                "A ordem da árvore B deve ser >= 2."
            );
        }

        this.t = t;
        this.raiz = new NoB<>(true);
    }

    public NoB<T> getRoot() {
        return raiz;
    }

    public int getOrder() {
        return t;
    }

    public boolean isEmpty() {
        return raiz.getNumberOfKeys() == 0;
    }

    // Procura o nó que contém a chave.
    // Retorna null caso a chave não exista.
    public NoB<T> searchNode(int chave) {
        return searchNode(raiz, chave);
    }

    private NoB<T> searchNode(NoB<T> no, int chave) {

        int i = 0;

        // Encontramos a primeira chave maior ou igual à chave procurada.
        while (
            i < no.getNumberOfKeys()
            && chave > no.getKey(i).getKey()
        ) {
            i++;
        }

        // A chave está neste nó.
        if (
            i < no.getNumberOfKeys()
            && chave == no.getKey(i).getKey()
        ) {
            return no;
        }

        // Se chegou em uma folha, a chave não existe.
        if (no.isLeaf()) {
            return null;
        }

        // A chave está necessariamente no filho i.
        return searchNode(no.getChild(i), chave);
    }

    // Retorna o Item encontrado.
    public Item<T> search(int chave) {

        NoB<T> no = searchNode(chave);

        if (no == null) {
            return null;
        }

        int indice = no.getIndexOfKey(chave);

        return no.getKey(indice);
    }

    // =========================================================
    // INSERÇÃO
    // =========================================================

    public void insert(Item<T> item) {

        // Evita inserir uma chave duplicada.
        if (search(item.getKey()) != null) {
            return;
        }

        // Se a raiz estiver cheia, precisamos criar uma nova raiz
        // e fazer a cisão.
        if (raiz.getNumberOfKeys() == 2 * t - 1) {

            NoB<T> novaRaiz = new NoB<>(false);

            novaRaiz.addChild(raiz);

            raiz.setParent(novaRaiz);

            raiz = novaRaiz;

            divideChild(raiz, 0);
        }

        insertIntoNotFull(raiz, item);
    }

    // Insere em um nó que sabemos que não está cheio.
    private void insertIntoNotFull(NoB<T> no, Item<T> item) {

        int chave = item.getKey();

        // Caso 1:
        // Estamos em uma folha.
        if (no.isLeaf()) {

            int i = no.getNumberOfKeys() - 1;

            // Move as chaves maiores para a direita.
            while (
                i >= 0
                && chave < no.getKey(i).getKey()
            ) {
                i--;
            }

            // Insere na posição correta.
            no.addKey(i + 1, item);

            return;
        }

        // Caso 2:
        // Estamos em um nó interno.

        int i = no.getNumberOfKeys() - 1;

        while (
            i >= 0
            && chave < no.getKey(i).getKey()
        ) {
            i--;
        }

        // O filho que deve receber a chave.
        i++;

        NoB<T> filho = no.getChild(i);

        // Se o filho estiver cheio, fazemos a cisão antes de descer.
        if (filho.getNumberOfKeys() == 2 * t - 1) {

            divideChild(no, i);

            // Depois da cisão, uma chave subiu para o pai.
            // Precisamos verificar de qual lado da chave
            // devemos continuar.
            if (chave > no.getKey(i).getKey()) {
                i++;
            }
        }

        insertIntoNotFull(no.getChild(i), item);
    }

    private void divideChild(NoB<T> pai, int indiceFilho) {

        NoB<T> cheio = pai.getChild(indiceFilho);

        NoB<T> novo = new NoB<>(cheio.isLeaf());

        // A chave do meio sobe para o pai.
        Item<T> chaveMeio = cheio.getKey(t - 1);

        // Copia as últimas t-1 chaves para o novo nó.
        for (int j = t; j < 2 * t - 1; j++) {
            novo.addKey(cheio.getKey(j));
        }

        // Se não for folha, também precisamos transferir os filhos.
        if (!cheio.isLeaf()) {

            for (int j = t; j < 2 * t; j++) {

                NoB<T> filho = cheio.getChild(j);

                novo.addChild(filho);
            }

            // Remove os filhos que foram transferidos.
            while (cheio.getNumberOfChildren() > t) {
                cheio.removeChild(t);
            }
        }

        // Remove do nó original as chaves que foram transferidas
        // e a chave do meio.
        while (cheio.getNumberOfKeys() > t - 1) {
            cheio.removeKey(t - 1);
        }

        // Coloca o novo filho ao lado do antigo.
        pai.addChild(indiceFilho + 1, novo);

        // Coloca a chave do meio no pai.
        pai.addKey(indiceFilho, chaveMeio);
    }

    // =========================================================
    // REMOÇÃO
    // =========================================================

    public Item<T> remove(int chave) {

        NoB<T> no = searchNode(chave);

        if (no == null) {
            return null;
        }

        int indice = no.getIndexOfKey(chave);

        Item<T> removido = no.getKey(indice);

        remove(raiz, chave);

        // Caso a raiz tenha ficado vazia após a remoção,
        // seu único filho passa a ser a nova raiz.
        if (
            !raiz.isLeaf()
            && raiz.getNumberOfKeys() == 0
        ) {

            raiz = raiz.getChild(0);

            raiz.setParent(null);
        }

        return removido;
    }

    // Método principal da remoção.
    private void remove(NoB<T> no, int chave) {

        int indice = 0;

        // Procura a primeira chave maior ou igual à chave.
        while (
            indice < no.getNumberOfKeys()
            && chave > no.getKey(indice).getKey()
        ) {
            indice++;
        }

        // caso 1:Chave encontrada neste nó.
        if (
            indice < no.getNumberOfKeys()
            && chave == no.getKey(indice).getKey()
        ) {

            // caso 1a: A chave está em uma folha.
            // Basta removê-la.
            if (no.isLeaf()) {

                no.removeKey(indice);

                return;
            }

            // caso 1b: A chave está em um nó interno.
            NoB<T> filhoEsquerdo = no.getChild(indice);
            NoB<T> filhoDireito = no.getChild(indice + 1);

            // Se o filho anterior tiver pelo menos t chaves,
            // usamos o predecessor.
            if (filhoEsquerdo.getNumberOfKeys() >= t) {

                Item<T> predecessor =
                    getPredecessor(filhoEsquerdo);

                // Substitui a chave pelo predecessor.
                no.getKeys().set(indice, predecessor);

                // Remove o predecessor da subárvore.
                remove(
                    filhoEsquerdo,
                    predecessor.getKey()
                );

                return;
            }

            // Se o filho posterior tiver pelo menos t chaves,
            // usamos o sucessor.
            if (filhoDireito.getNumberOfKeys() >= t) {

                Item<T> sucessor =
                    getSucessor(filhoDireito);

                no.getKeys().set(indice, sucessor);

                remove(
                    filhoDireito,
                    sucessor.getKey()
                );

                return;
            }

            // Caso contrário, ambos possuem t-1 chaves.
            // Fazer fusão: filhoEsquerdo + chave + filhoDireito
            mergeChildren(no, indice);

            // Depois da fusão, a chave está no filho esquerdo.
            remove(
                filhoEsquerdo,
                chave
            );

            return;
        }

        // caso 2: A chave não está neste nó.
        if (no.isLeaf()) {
            return;
        }

        // descer para o filho indice.
        NoB<T> filho = no.getChild(indice);

        // Antes de descer, precisamos garantir que o filho
        // tenha pelo menos t chaves.
        // Se ele tiver apenas t-1, tentamos: 1. Emprestar do irmão esquerdo; 2. Emprestar do irmão direito; 3. Fazer fusão;
        if (filho.getNumberOfKeys() == t - 1) {

            // Tenta emprestar do irmão esquerdo.
            if (
                indice > 0
                && no.getChild(indice - 1)
                    .getNumberOfKeys() >= t
            ) {

                borrowFromLeft(no, indice);

            // Tenta emprestar do irmão direito.
            } else if (
                indice < no.getNumberOfChildren() - 1
                && no.getChild(indice + 1)
                    .getNumberOfKeys() >= t
            ) {

                borrowFromRight(no, indice);

            // Não foi possível emprestar.
            // Fazemos uma fusão.
            } else {

                if (
                    indice < no.getNumberOfChildren() - 1
                ) {

                    // Funde o filho com o irmão direito.
                    mergeChildren(no, indice);

                } else {

                    // Funde com o irmão esquerdo.
                    mergeChildren(no, indice - 1);

                    indice--;
                }
            }
        }

        // Depois de empréstimo ou fusão,
        // continuamos procurando a chave.
        remove(
            no.getChild(indice),
            chave
        );
    }

    private Item<T> getPredecessor(NoB<T> no) {

        NoB<T> atual = no;

        // O predecessor está na folha mais à direita
        // da subárvore.
        while (!atual.isLeaf()) {
            atual =
                atual.getChild(
                    atual.getNumberOfChildren() - 1
                );
        }

        return atual.getLastKey();
    }

    // =========================================================
    // SUCESSOR
    // =========================================================

    private Item<T> getSucessor(NoB<T> no) {

        NoB<T> atual = no;

        // O sucessor está na folha mais à esquerda
        // da subárvore.
        while (!atual.isLeaf()) {
            atual = atual.getChild(0);
        }

        return atual.getFirstKey();
    }

    // empréstimo do irmão esquerdo
    private void borrowFromLeft(
        NoB<T> pai,
        int indice
    ) {

        NoB<T> filho = pai.getChild(indice);
        NoB<T> irmao = pai.getChild(indice - 1);

        // A chave do pai desce para o filho.
        filho.addKey(
            0,
            pai.getKey(indice - 1)
        );

        // A última chave do irmão sobe para o pai.
        Item<T> chaveIrmao =
            irmao.removeKey(
                irmao.getNumberOfKeys() - 1
            );

        pai.getKeys().set(
            indice - 1,
            chaveIrmao
        );

        // Se houver filhos, também precisamos
        // transferir o último filho do irmão.
        if (!irmao.isLeaf()) {

            NoB<T> ultimoFilho =
                irmao.removeChild(
                    irmao.getNumberOfChildren() - 1
                );

            filho.addChild(
                0,
                ultimoFilho
            );
        }
    }

    // empréstimo do direito
    // A lógica é espelhada em relação ao empréstimo do irmão esquerdo.
    private void borrowFromRight(
        NoB<T> pai,
        int indice
    ) {

        NoB<T> filho = pai.getChild(indice);
        NoB<T> irmao = pai.getChild(indice + 1);

        // A chave do pai desce para o final do filho.
        filho.addKey(
            pai.getKey(indice)
        );

        // A primeira chave do irmão sobe para o pai.
        Item<T> chaveIrmao =
            irmao.removeKey(0);

        pai.getKeys().set(
            indice,
            chaveIrmao
        );

        // Se houver filhos, transfere o primeiro
        // filho do irmão.
        if (!irmao.isLeaf()) {

            NoB<T> primeiroFilho =
                irmao.removeChild(0);

            filho.addChild(primeiroFilho);
        }
    }

    // fusão
    private void mergeChildren(
        NoB<T> pai,
        int indice
    ) {

        NoB<T> esquerdo =
            pai.getChild(indice);

        NoB<T> direito =
            pai.getChild(indice + 1);

        // Pega a chave que está entre os dois filhos.
        Item<T> chavePai =
            pai.removeKey(indice);

        // Coloca a chave do pai no filho esquerdo.
        esquerdo.addKey(chavePai);

        // Copia todas as chaves do irmão direito.
        for (Item<T> chave : direito.getKeys()) {
            esquerdo.addKey(chave);
        }

        // Copia os filhos, caso sejam nós internos.
        if (!direito.isLeaf()) {

            for (NoB<T> filho : direito.getChildren()) {
                esquerdo.addChild(filho);
            }
        }

        // Remove o irmão direito do pai.
        pai.removeChild(indice + 1);
    }

    // =========================================================
    // IMPRESSÃO
    // =========================================================

    public void printTree() {
        printTree(raiz, 0);
    }

    private void printTree(NoB<T> no, int nivel) {

        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }

        System.out.print("[ ");

        for (Item<T> chave : no.getKeys()) {
            System.out.print(chave.getKey() + " ");
        }

        System.out.println("]");

        if (!no.isLeaf()) {

            for (NoB<T> filho : no.getChildren()) {
                printTree(filho, nivel + 1);
            }
        }
    }
}
