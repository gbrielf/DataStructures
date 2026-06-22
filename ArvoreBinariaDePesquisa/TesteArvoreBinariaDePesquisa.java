package ArvoreBinariaDePesquisa;
import ArvoreBinariaDePesquisa.No;
import java.util.Iterator;

public class TesteArvoreBinariaDePesquisa {
    public static void main(String[] args) {

        // ── criação ───────────────────────────────────
        ArvoreBinariaDePesquisa<String> abp = new ArvoreBinariaDePesquisa<>(new Item<>(10, "raiz"));

        abp.insert(new Item<>(5,  "cinco"));
        abp.insert(new Item<>(15, "quinze"));
        abp.insert(new Item<>(3,  "tres"));
        abp.insert(new Item<>(7,  "sete"));
        abp.insert(new Item<>(12, "doze"));
        abp.insert(new Item<>(20, "vinte"));

        // ── tamanho e altura ──────────────────────────
        System.out.println("Tamanho: " + abp.size());       // 7
        System.out.println("Altura:  " + abp.height());     // 2

        // ── busca ─────────────────────────────────────
        No<String> encontrado = abp.search(7);
        System.out.println("Busca 7:  " + (encontrado != null ? encontrado.getItem().getElemento() : "não encontrado"));

        No<String> naoEncontrado = abp.search(99);
        System.out.println("Busca 99: " + (naoEncontrado != null ? naoEncontrado.getItem().getElemento() : "não encontrado"));

        // ── elementos em ordem ────────────────────────
        System.out.print("In-order: ");
        Iterator it = abp.elements();
        while(it.hasNext()){
            Item<String> item = (Item<String>) it.next();
            System.out.print(item.getKey() + " ");
        }
        System.out.println(); // 3 5 7 10 12 15 20

        // ── profundidade ──────────────────────────────
        System.out.println("Depth raiz: " + abp.depth(abp.getRoot())); // 0
        System.out.println("Depth 7:    " + abp.depth(abp.search(7))); // 2

        // ── filhos da raiz ────────────────────────────
        System.out.print("Filhos da raiz: ");
        Iterator filhos = abp.children(abp.getRoot());
        while(filhos.hasNext()){
            No<String> filho = (No<String>) filhos.next();
            System.out.print(filho.getItem().getKey() + " ");
        }
        System.out.println(); // 5 15

        // ── remoção nó folha ──────────────────────────
        Item<String> removido = abp.remove(3);
        System.out.println("Removido folha: " + removido.getKey()); // 3
        System.out.println("Tamanho após remoção: " + abp.size());  // 6

        // ── remoção nó com um filho ───────────────────
        abp.remove(7); // 5 ficará só com filho direito vazio
        System.out.println("Tamanho após remoção: " + abp.size());  // 5

        // ── remoção nó com dois filhos ────────────────
        abp.remove(15);
        System.out.println("Tamanho após remoção: " + abp.size());  // 4

        // ── in-order após remoções ────────────────────
        System.out.print("In-order final: ");
        Iterator it2 = abp.elements();
        while(it2.hasNext()){
            Item<String> item = (Item<String>) it2.next();
            System.out.print(item.getKey() + " ");
        }
        System.out.println(); // 5 10 12 20
    }
}