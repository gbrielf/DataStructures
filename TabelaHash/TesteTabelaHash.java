package TabelaHash;

import java.util.Iterator;

public class TesteTabelaHash {
    public static void main(String[] args) {
        testarLinearProbing();
        System.out.println();
        testarHashingDuplo();
    }

    private static void testarLinearProbing() {
        System.out.println("===== TESTE COM LINEAR PROBING =====");

        TabelaHash<Integer, String> tabela = new TabelaHash<>(13, TabelaHash.LINEAR_PROBING);

        tabela.insertItem(18, "Joao");
        tabela.insertItem(41, "Maria");
        tabela.insertItem(22, "Carlos");
        tabela.insertItem(44, "Ana");
        tabela.insertItem(59, "Pedro");
        tabela.insertItem(32, "Julia");
        tabela.insertItem(31, "Marcos");
        tabela.insertItem(73, "Fernanda");

        tabela.imprimirTabela();

        System.out.println("\nBuscando chave 44: " + tabela.findElement(44));
        System.out.println("Buscando chave 100: " + tabela.findElement(100));

        System.out.println("\nRemovendo chave 44: " + tabela.removeElement(44));
        System.out.println("Buscando chave 44 depois da remoção: " + tabela.findElement(44));

        System.out.println("\nTabela depois da remoção:");
        tabela.imprimirTabela();

        System.out.println("\nChaves:");
        Iterator<Integer> chaves = tabela.keys();

        while (chaves.hasNext()) {
            System.out.println(chaves.next());
        }

        System.out.println("\nElementos:");
        Iterator<String> elementos = tabela.elements();

        while (elementos.hasNext()) {
            System.out.println(elementos.next());
        }
    }

    private static void testarHashingDuplo() {
        System.out.println("===== TESTE COM HASHING DUPLO =====");

        TabelaHash<Integer, String> tabela = new TabelaHash<>(13, TabelaHash.HASHING_DUPLO);

        tabela.insertItem(18, "Joao");
        tabela.insertItem(41, "Maria");
        tabela.insertItem(22, "Carlos");
        tabela.insertItem(44, "Ana");
        tabela.insertItem(59, "Pedro");
        tabela.insertItem(32, "Julia");
        tabela.insertItem(31, "Marcos");
        tabela.insertItem(73, "Fernanda");

        tabela.imprimirTabela();

        System.out.println("\nBuscando chave 31: " + tabela.findElement(31));
        System.out.println("Buscando chave 200: " + tabela.findElement(200));

        System.out.println("\nRemovendo chave 31: " + tabela.removeElement(31));
        System.out.println("Buscando chave 31 depois da remoção: " + tabela.findElement(31));

        System.out.println("\nTabela depois da remoção:");
        tabela.imprimirTabela();
    }
}