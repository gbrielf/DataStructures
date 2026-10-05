package ArvoreB;
import java.util.ArrayList;
import java.util.List;
import ArvoreBinariaDePesquisa.Item;

public class NoB<T>{
    private List<Item<T>> chaves;
    private List<NoB<T>> filhos;
    private NoB<T> pai;
    private boolean folha;
    private Item<T> primeiraChave;
    private Item<T> ultimaChave;

    public NoB(boolean folha){
        this.chaves = new ArrayList<>();
        this.filhos = new ArrayList<>();
        this.folha = folha;
    }

    public Item<T> getFirstKey(){
        if(chaves.isEmpty()) return null;
        return chaves.get(0);
    }

    public Item<T> getLastKey(){
        if(chaves.isEmpty()) return null;
        return chaves.get(chaves.size() - 1);
    }

    public int getIndexOfKey(int chave){
        for(int i = 0; i < chaves.size(); i++ ){
            if( chaves.get(i).getKey() == chave){
                return i;
            }
        }

        return -1; // não encontrado
    }

    public int getIndexOfChild(NoB<T> filho) {
        return filhos.indexOf(filho); // aqui sim, tipo bate: filho é NoB<T>
    }

    public NoB<T> getChild(int indice){
        return filhos.get(indice);
    }

    public List<NoB<T>> getChildren(){
        return this.filhos;
    }

    public NoB<T> getParent(){
        return this.pai;
    }

    public void setParent(NoB<T> pai){
        this.pai = pai;
    }

    public boolean isLeaf(){
        return this.folha;
    }

    public void setLeaf(boolean folha){
        this.folha = folha;
    }

}
