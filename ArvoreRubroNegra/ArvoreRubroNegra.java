package ArvoreRubroNegra;
import ArvoreAVL.NoAVL;
import ArvoreBinariaDePesquisa.ArvoreBP;
import ArvoreBinariaDePesquisa.No;
import ArvoreBinariaDePesquisa.Item;

public class ArvoreRubroNegra<T> extends ArvoreBP<T> {
    public String cor;
    private NoRubroNegro<T> paiDoRemovido;
    private boolean removidoEraFilhoEsquerdo;
    private String corDoRemovido;

    public ArvoreRubroNegra(Item<T> item) {
        super(item);
        this.cor = "vermelho";
    }

    @Override
    public NoRubroNegro<T> createNode(Item<T> item, No<T> pai) {
        return new NoRubroNegro<>(item, pai);
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
                corDoRemovido = ((NoRubroNegro<T>) atual).getNodeColor();

                if(paiDoRemovido != null){
                    removidoEraFilhoEsquerdo = (paiDoRemovido.getLeftChild() == atual);
                }

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
                corDoRemovido = ((NoRubroNegro<T>) atual).getNodeColor();
                
                if(paiDoRemovido != null){
                    removidoEraFilhoEsquerdo = (paiDoRemovido.getLeftChild() == atual);
                }

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
                corDoRemovido = ((NoRubroNegro<T>) atual).getNodeColor();
                
                if(paiDoRemovido != null){
                    removidoEraFilhoEsquerdo = (paiDoRemovido.getLeftChild() == sucessor);
                }

                No<T> novoDireito = removeRec(atual.getRightChild(), sucessor.getItem().getKey());

                atual.setRightChild(novoDireito);

                if(novoDireito != null){
                    novoDireito.setParent(atual);
                }

            }
        }

        return atual;
    }

    // Retorna o nó removido de forma recursiva da árvore rubro negra 
    public void removeRB(int chave){
        paiDoRemovido = null;
        corDoRemovido = null;

        raiz = (NoRubroNegro<T>) removeRec(getRoot(), chave);
        
        // só chama o updateBalanceRemove se o nó removido for preto, pois a remoção de um nó vermelho não viola as propriedades da árvore rubro-negra
        if( paiDoRemovido != null && corDoRemovido.equals("preto") ){
            updateBalanceRemove(paiDoRemovido);
        }
    }    

    public void updateBalanceRemove(NoRubroNegro<T> n){
        if(n == null){
            return;
        }

        boolean nEraFilhoEsquerdo = removidoEraFilhoEsquerdo;

        NoRubroNegro<T> irmao = nEraFilhoEsquerdo
            ? n.getRightChild(): n.getLeftChild();

        // Caso 1: O irmão é vermelho
        if(irmao != null && irmao.getNodeColor().equals("vermelho")){
            n.setNodeColor("vermelho");
            irmao.setNodeColor("preto");
            
            if(nEraFilhoEsquerdo){
                leftRotation(n);
            }else{
                rightRotation(n);
            }
            // depois da rotação o irmão mudou, então atualizamos a referência
            irmao = nEraFilhoEsquerdo
                ? n.getRightChild(): n.getLeftChild();
        }

        // Caso 2: O irmão é preto 
        NoRubroNegro<T> sobrinhoProximo = nEraFilhoEsquerdo
            ? (irmao != null ? irmao.getLeftChild() : null)
            : (irmao != null ? irmao.getRightChild() : null);

        NoRubroNegro<T> sobrinhoDistante = nEraFilhoEsquerdo
            ? (irmao != null ? irmao.getRightChild() : null)
            : (irmao != null ? irmao.getLeftChild() : null);

        boolean sobrinhoDistanteVermelho = sobrinhoDistante != null && sobrinhoDistante.getNodeColor().equals("vermelho");
        boolean sobrinhoProximoVermelho = sobrinhoProximo != null && sobrinhoProximo.getNodeColor().equals("vermelho");

        // caso 4: sobrinho distante vermelho -> rotação do pai + repintamento
        if(sobrinhoDistanteVermelho){
            if(irmao != null){
                irmao.setNodeColor(n.getNodeColor());
            }
            n.setNodeColor("preto");
            sobrinhoDistante.setNodeColor("preto");

            if(nEraFilhoEsquerdo){
                leftRotation(n);
            }else{
                rightRotation(n);
            }
            return;
        }

        // caso 3: sobrinho próximo vermelho -> rotação do irmão + repintamento
        if(sobrinhoProximoVermelho){
            if(irmao != null){
                irmao.setNodeColor("vermelho");
                sobrinhoProximo.setNodeColor("preto");

                if(nEraFilhoEsquerdo){
                    rightRotation(irmao);
                }else{
                    leftRotation(irmao);
                }
            }

            // recalcula tudo e cai no caso 4
            irmao = nEraFilhoEsquerdo
                ? n.getRightChild(): n.getLeftChild();
            
            sobrinhoDistante = nEraFilhoEsquerdo
                ? (irmao != null ? irmao.getRightChild() : null)
                : (irmao != null ? irmao.getLeftChild() : null);
            
            if(irmao != null){
                irmao.setNodeColor(n.getNodeColor());
            }
            n.setNodeColor("preto");
            if(sobrinhoDistante != null){
                sobrinhoDistante.setNodeColor("preto");
            }

            if(nEraFilhoEsquerdo){
                leftRotation(n);
            }else{
                rightRotation(n);
            }
            return;
        }

        // caso 2: irmão preto com dois sobrinhos pretos (ou nulos)
        // recolore o irmão de vermelho e sobe o dupl negro para o pai
        if(irmao != null) {
            irmao.setNodeColor("vermelho");
        }

        if(n.getNodeColor().equals("vermelho")) {
            // pai absorve o duplo negro e fica preto
            n.setNodeColor("preto");
            return;
        }

        // pai já era preto, o duplo negro sobe o próprio pai se torna o vazio
        NoRubroNegro<T> avo = n.getParent();

        if(avo == null){
            //chegou na raiz, termina a propagação
            return;
        }

        removidoEraFilhoEsquerdo = (avo.getLeftChild() == n);
        updateBalanceRemove(avo);
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

        // se o pai for preto não há violação
        if (n.getParent().getNodeColor().equals("preto")) {
            return;
        }

        // pai é vermelho, resolver pois ocorreu violação
        NoRubroNegro<T> pai = n.getParent();
        NoRubroNegro<T> avo = n.getGrandParent();

        if(avo == null){
            // segurança, caso o pai seja o raiz
            pai.setNodeColor("preto");
            return;
        }

        NoRubroNegro<T> tio = pai.getSibling(); // tio = irmão do pai, não de n


        // Caso 1: Se o nó inserido for vermelho e o pai for vermelho, precisa balancear
        if(tio != null && n.getNodeColor().equals("vermelho")){
            pai.setNodeColor("preto");
            tio.setNodeColor("preto");
            avo.setNodeColor("vermelho");
            updateBalanceInsert(avo);
        }else{
            // Caso 2: Tio preto
            balanceInsert(n);
        }   
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
            NoRubroNegro<T> avo = n.getGrandParent();
            rightRotation(n.getParent());
            leftRotation(avo);

            n.setNodeColor("preto");
            n.getLeftChild().setNodeColor("vermelho");
            n.getRightChild().setNodeColor("vermelho");
        }else if(n.isRightChild() && n.getParent().isLeftChild()){
            NoRubroNegro<T> avo = n.getGrandParent();
            leftRotation(n.getParent());
            rightRotation(avo);
            n.setNodeColor("preto");
            n.getLeftChild().setNodeColor("vermelho");
            n.getRightChild().setNodeColor("vermelho");
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

     // Reaproveitando a estrutura do printTree da árvore AVL, trocando o
    // fator de balanceamento (BF) pela cor do nó.
    public void printTree() {
        if (this.isEmpty()) {
            throw new RuntimeException("Árvore vazia");
        }
        NoRubroNegro<T> raizRB = (NoRubroNegro<T>) getRoot();
        int linhas = height(raiz) + 1;
        int colunas = (int) Math.pow(2, linhas) - 1;
        String[][] matrix = new String[linhas][colunas];
 
        completeMatrix(raizRB, matrix, 0, 0, linhas);
 
        int largura = largestSize(matrix) + 1; // +1 de espaçamento
 
        // i começa em 0 (raiz) e vai até a última linha (folhas)
        for (int i = 0; i < linhas; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < colunas; j++) {
                String valor = matrix[i][j] == null ? "" : matrix[i][j];
                sb.append(centralize(valor, largura));
            }
            System.out.println(sb.toString());
        }
    }
 
    private void completeMatrix(NoRubroNegro<T> no, String[][] matrix, int nivel, int posicao, int linhas) {
        if (no == null) {
            return;
        }
 
        int coluna = (int) (Math.pow(2, linhas - nivel - 1) * (2 * posicao + 1)) - 1;
 
        String chave = String.valueOf(no.getItem().getKey());
        // "V" para vermelho, "P" para preto
        String cor = no.getNodeColor().equals("vermelho") ? "V" : "P";
        matrix[nivel][coluna] = chave + "(" + cor + ")";
 
        completeMatrix(no.getLeftChild(), matrix, nivel + 1, posicao * 2, linhas);
        completeMatrix(no.getRightChild(), matrix, nivel + 1, (posicao * 2) + 1, linhas);
    }
 
    private int largestSize(String[][] matrix) {
        int max = 0;
        for (String[] linha : matrix) {
            for (String valor : linha) {
                if (valor != null) {
                    max = Math.max(max, valor.length());
                }
            }
        }
        return max;
    }
 
    private String centralize(String texto, int largura) {
        if (texto.length() >= largura) {
            return texto;
        }
        int espacosTotal = largura - texto.length();
        int esquerda = espacosTotal / 2;
        int direita = espacosTotal - esquerda;
        return " ".repeat(esquerda) + texto + " ".repeat(direita);
    }
}