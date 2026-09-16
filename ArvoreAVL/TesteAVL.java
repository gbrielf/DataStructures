package ArvoreAVL;

import ArvoreBinariaDePesquisa.Item;

public class TesteAVL {
    public static void main(String[] args) {
        ArvoreAVL<Integer> arvore = new ArvoreAVL<>(new Item<Integer>(99, 99));

        arvore.insertAVL(new Item<Integer>(30, 30));
        arvore.insertAVL(new Item<Integer>(20, 20));
        arvore.insertAVL(new Item<Integer>(10, 10)); // Deve causar rotação à direita

        arvore.insertAVL(new Item<Integer>(40, 40));
        arvore.insertAVL(new Item<Integer>(50, 50)); // Deve causar rotação à esquerda

        arvore.insertAVL(new Item<Integer>(25, 25)); // Deve causar rotação dupla (esquerda-direita)

        System.out.println("Árvore AVL:");
        arvore.printTree();
    }
    
}
