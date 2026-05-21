package PilhasArray;
import PilhasArray.Pilha;
import PilhasArray.PilhaArray;

public class TestePilhaArray{
    public static void main(String[] args){
        PilhaArray pilha = new PilhaArray(10, 0);

        System.out.println("== Teste Pilha Array ==");

        System.out.println("Pilha vazia?" + pilha.isEmpty());

        System.out.println("Pilha pop:" + pilha.pop()); // lança exceção, a pilha já está vazia
        
        pilha.push("2"); // adiciona char 2 a pilha
        System.out.println("Pilha top:" + pilha.top()); // retorna "2"
        
        pilha.push(2); // adiciona int 2
        System.out.println("Pilha top:" + pilha.top()); // retorna 2

        pilha.push("a"); // adiciona char a
        System.out.println("Pilha top:" + pilha.top()); // retorna a
        
        System.out.println("Pilha tamanho:" + pilha.size());

        System.out.println("Pilha vazia?" + pilha.isEmpty());
    }
}