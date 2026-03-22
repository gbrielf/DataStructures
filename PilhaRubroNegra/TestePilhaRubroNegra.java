public class TestePilhaRubroNegra {
    public static void main(String[] args) {
        PilhaRubroNegra pilha = new PilhaArrayRubroNegra(4);

        System.out.println("== Teste Pilha Vermelha ==");
        pilha.pushVermelha("A");
        pilha.pushVermelha("B");
        System.out.println("top vermelha: " + pilha.topVermelha()); // B
        System.out.println("Pop vermelha: " + pilha.popVermelha());  // B
        System.out.println("top vermelha: " + pilha.topVermelha()); // A
        System.out.println("Pop vermelha: " + pilha.popVermelha());  // A
        System.out.println("Está vazia vermelha? " + pilha.estaVaziaVermelha()); // true

        System.out.println("\n== Teste Pilha Preta ==");
        pilha.pushPreta(1);
        pilha.pushPreta(2);
        System.out.println("top preta: " + pilha.topPreta()); // 2
        System.out.println("Pop preta: " + pilha.popPreta());   // 2
        System.out.println("Pop preta: " + pilha.popPreta());   // 1
        System.out.println("Está vazia preta? " + pilha.estaVaziaPreta()); // true

        System.out.println("\n== Teste aumento automático de capacidade ==");
        pilha.pushVermelha("X");
        pilha.pushVermelha("Y");
        pilha.pushVermelha("Z");
        pilha.pushVermelha("W");
        pilha.pushPreta("P"); // Deve disparar aumento

        System.out.println("top vermelha: " + pilha.topVermelha()); // W
        System.out.println("top preta: " + pilha.topPreta());       // P

        System.out.println("\n== Teste erro ao desempilhar de pilha vazia ==");
        try {
            pilha.popPreta(); // Remove P
            pilha.popPreta(); // Erro esperado
        } catch (RuntimeException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        }

        System.out.println("\n== Teste finalizado com sucesso ==");
    }
}