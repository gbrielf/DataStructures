package Sequencia;

import Lista.No;

public class TesteSequencia {
    public static void main(String[] args) {
        Sequencia seq = new SequenciaComLista();

        System.out.println("=== Teste da Sequência ===");
        System.out.println("Tamanho inicial: " + seq.size());
        System.out.println("Está vazia? " + seq.isEmpty());

        System.out.println("\n--- Métodos de Vector ---");
        seq.insertAtRank(0, "A");
        seq.insertAtRank(1, "B");
        seq.insertAtRank(2, "C");
        System.out.println("Após insertAtRank (0:A, 1:B, 2:C):");
        imprimir(seq);

        System.out.println("elemAtRank(1): " + seq.elemAtRank(1));

        System.out.println("replaceAtRank(1, 'X'): " + seq.replaceAtRank(1, "X"));
        imprimir(seq);

        System.out.println("removeAtRank(1): " + seq.removeAtRank(1));
        imprimir(seq);

        System.out.println("\n--- Métodos de Lista ---");
        seq.insertFirst("Primeiro");
        seq.insertLast("Ultimo");
        System.out.println("Após insertFirst e insertLast:");
        imprimir(seq);

        No n1 = seq.getFirst();
        No n2 = seq.getLast();
        System.out.println("getFirst(): " + n1.getElement());
        System.out.println("getLast(): " + n2.getElement());

        System.out.println("isFirst(n1)? " + seq.isFirst(n1));
        System.out.println("isLast(n2)? " + seq.isLast(n2));

        seq.insertAfter(n1, "Depois do Primeiro");
        seq.insertBefore(n2, "Antes do Ultimo");
        System.out.println("Após insertAfter e insertBefore:");
        imprimir(seq);

        No n3 = seq.after(n1);
        No n4 = seq.before(n2);
        System.out.println("after(getFirst()): " + n3.getElement());
        System.out.println("before(getLast()): " + n4.getElement());

        System.out.println("replaceElement(n3, 'Novo Valor'): " + seq.replaceElement(n3, "Novo Valor"));
        imprimir(seq);

        System.out.println("swapElements(n1, n2)");
        seq.swapElements(n1, n2);
        imprimir(seq);
        System.out.println("Voltando swapElements(n1, n2)");
        seq.swapElements(n1, n2);
        imprimir(seq);

        System.out.println("remove(n3): " + seq.remove(n3));
        imprimir(seq);

        System.out.println("\n--- Métodos Ponte (Bridge) ---");
        No nRank2 = seq.atRank(2);
        if (nRank2 != null) {
            System.out.println("atRank(2): " + nRank2.getElement());
            System.out.println("rankOf(nRank2): " + seq.rankOf(nRank2));
        }

        System.out.println("\nTamanho final: " + seq.size());
        System.out.println("Está vazia? " + seq.isEmpty());
    }

    private static void imprimir(Sequencia seq) {
        System.out.print("Estado atual (por rank): [");
        for (int i = 0; i < seq.size(); i++) {
            System.out.print(seq.elemAtRank(i) + (i < seq.size() - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
}
