package FilaArrayComReversao;

public class TesteFilaComReversao {
    public static void main(String[] args) {
        System.out.println("=== Iniciando Teste Fila com Reversão ===");
        
        // Inicializa com capacidade 5 (comporta 4 elementos)
        FilaArrayComReversao fila = new FilaArrayComReversao(5);
        
        System.out.println("\n1. Enqueue [10, 20, 30]");
        fila.enqueue(10);
        fila.enqueue(20);
        fila.enqueue(30);
        
        System.out.println("Tamanho atual: " + fila.size()); // 3
        System.out.println("Primeiro (first): " + fila.first()); // 10
        
        System.out.println("\n2. Revertendo a fila...");
        fila.reverse();
        
        System.out.println("Novo primeiro (first): " + fila.first()); // 30
        
        System.out.println("\n3. Dequeue do primeiro reverso: " + fila.dequeue()); // 30
        
        System.out.println("\n4. Enqueue de 40 no modo reverso");
        fila.enqueue(40);
        
        System.out.println("Primeiro (first) atual: " + fila.first()); // 20
        
        System.out.println("\n5. Esvaziando a fila no modo reverso...");
        while(!fila.isEmpty()){
            System.out.println("Dequeue: " + fila.dequeue()); 
        }
        
        System.out.println("\n6. Teste de fila vazia");
        try {
            fila.dequeue();
        } catch (Exception e) {
            System.out.println("Sucesso ao capturar exceção de fila vazia: " + e.getMessage());
        }

        System.out.println("\n=== Fim do Teste ===");
    }
}
