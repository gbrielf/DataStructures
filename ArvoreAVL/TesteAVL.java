package ArvoreAVL;
import java.util.Scanner;
import ArvoreBinariaDePesquisa.Item;

public class TesteAVL {
    public static void main(String[] args) {
        ArvoreAVL<Integer> arvore = new ArvoreAVL<>(new Item<Integer>(10, null));
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("****MENU****\n");
            System.out.println("1. Inserir valor");
            System.out.println("2. Remover valor");
            System.out.println("3. Atualizar valor");
            System.out.println("4. Apresentar arvore");
            System.out.println("5. Mostrar altura da árvore");
            System.out.println("6. Buscar valor de um nó");
            System.out.println("7. Sair");
            System.out.print("Escola uma opção e\naperte na tecla '0'\npara finalizar o programa.");

            String opcao = scanner.nextLine().trim();

            switch(opcao) {
                case "1":
                    try{
                        System.out.print("Qual o valor você deseja inserir na sua árvore?");
                        int novoValor = Integer.parseInt(scanner.nextLine().trim());
                        arvore.insertAVL(novoValor);
                    }
            }
        }


    }
    
}
