package ArvoreAVL;
import java.util.Scanner;
import ArvoreBinariaDePesquisa.Item;

public class TesteAVL {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo ao Teste da Árvore AVL!");
        System.out.println("Digite um valor inteiro para iniciar a árvore AVL:");
        int valorInicial = scanner.nextInt();
        // consome o \n deixado pelo nextInt()
        scanner.nextLine(); 

        System.out.println("Digite o elemento associado ao valor (ou deixe em branco para null):");
        String elementoInputInicial = scanner.nextLine().trim();
        // is empty? não funciona com object, por isso, transforma em string e verifica se está vazio, se sim, recebe null, se não, recebe elementoInputInicial
        Object elementoInicial = elementoInputInicial.isEmpty() ? null : elementoInputInicial; 

        ArvoreAVL<Object> arvore = new ArvoreAVL<>(new Item<Object>(valorInicial, elementoInicial));

        while(true){
            System.out.println("******MENU******\n");
            System.out.println("1. Inserir valor");
            System.out.println("2. Remover valor");
            System.out.println("3. Atualizar valor");
            System.out.println("4. Apresentar arvore");
            System.out.println("5. Mostrar altura da árvore");
            System.out.println("6. Buscar valor de um nó");
            System.out.println("0. Sair");
            System.out.print("Escola uma opção e\naperte na tecla '0'\npara finalizar o programa.\n\nDigite a opção desejada:\n");

            String opcao = scanner.nextLine().trim();

            switch(opcao) {
                case "1":
                    try{
                        System.out.print("Digite um valor inteiro para inserir na árvore:\n");
                        int novoValor = Integer.parseInt(scanner.nextLine().trim());
                        System.out.println("Digite o elemento associado ao valor (ou deixe em branco para null):");
                        String elementoInput = scanner.nextLine().trim();
                        Object elemento = elementoInput.isEmpty() ? null : elementoInput;
                        arvore.insertAVL(new Item<Object>(novoValor, elemento));
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Valor inválido! Digite um número inteiro.");
                    } catch ( Exception e) {
                        System.out.println("Erro ao inserir:" + e.getMessage());
                    }
                    break;

                case "2":
                    try{
                        System.out.println("Digite o valor inteiro a ser removido da árvore:");
                        int numARemover = Integer.parseInt(scanner.nextLine().trim());
                        arvore.removeAVL(numARemover);
                        System.out.println("O número " + numARemover +" foi removido!");
                    }catch( NumberFormatException e){
                        System.out.println("Erro: Valor inválido! Digite um número inteiro.");
                    } catch ( Exception e) {
                        System.out.println("Erro ao remover o número "+ e.getMessage());
                    }
                    break;
                
                case "3":
                    try{
                        System.out.println("Digite a chave do nó a ser atualizado:");
                        int chaveAlvo = Integer.parseInt(scanner.nextLine().trim());

                        System.out.println("Digite a NOVA CHAVE (número inteiro) do nó:");
                        int novoValor = Integer.parseInt(scanner.nextLine().trim());

                        System.out.println("Digite o novo elemento associado ao nó (ou deixe em branco para null):");
                        String elementoInput = scanner.nextLine().trim();
                        // novo elemento está vazio? então recebe null, se não recebe elemento input
                        Object novoElemento = elementoInput.isEmpty() ? null : elementoInput; 

                        // Cria um novo item com o novo valor
                        NoAVL<Object> noAlvo = (NoAVL<Object>) arvore.search(chaveAlvo);

                        if(noAlvo == null){
                            System.out.println("Nó com chave "+ chaveAlvo + " não existe!");
                        }else{
                            Item<Object> novoItem = new Item<>(novoValor, novoElemento);
                            Object itemAntigo = arvore.replace(noAlvo, novoItem);
                            System.out.println("O item antigo "+ itemAntigo+" foi substituído!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Valor inválido! Digite um número inteiro.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage()+ "");
                    }
                    break;
                case "4":
                    System.out.println("\nÁrvore AVL atual:");
                    arvore.printTree();
                    System.out.println("\n");
                    break;

                case "5":
                    System.out.println("Altura atual da árvore: " + arvore.height());
                    break;
                
                case "6":
                    try{
                        System.out.println("Digite o valor do nó a ser buscado:");
                        int valorABuscar = Integer.parseInt(scanner.nextLine().trim());
                        NoAVL<Object> itemEncontrado = (NoAVL<Object>) arvore.search(valorABuscar);
                        if(itemEncontrado != null){
                            System.out.println("O nó com valor " + valorABuscar + " possui o elemento " + itemEncontrado.getItem().getElemento());
                        } else {
                            System.out.println("O nó com valor " + valorABuscar + " não foi encontrado na árvore.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Erro: Valor inválido! Digite um número inteiro.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                
                case "0":
                    System.out.println("Programa finalizado.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
            }
        }            
    }
}