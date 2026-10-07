package ArvoreB;

import java.util.ArrayList;
import java.util.List;
import ArvoreBinariaDePesquisa.Item;

public class NoB<T> {

    private List<Item<T>> chaves;
    private List<NoB<T>> filhos;
    private NoB<T> pai;
    private boolean folha;

    public NoB(boolean folha) {
        this.chaves = new ArrayList<>();
        this.filhos = new ArrayList<>();
        this.folha = folha;
    }

    public Item<T> getFirstKey() {
        if (chaves.isEmpty()) return null;
        return chaves.get(0);
    }

    public Item<T> getLastKey() {
        if (chaves.isEmpty()) return null;
        return chaves.get(chaves.size() - 1);
    }

    public int getIndexOfKey(int chave) {
        for (int i = 0; i < chaves.size(); i++) {
            if (chaves.get(i).getKey() == chave) {
                return i;
            }
        }

        return -1;
    }

    public int getIndexOfChild(NoB<T> filho) {
        return filhos.indexOf(filho);
    }

    public NoB<T> getChild(int indice) {
        return filhos.get(indice);
    }

    public List<NoB<T>> getChildren() {
        return filhos;
    }

    public NoB<T> getParent() {
        return pai;
    }

    public void setParent(NoB<T> pai) {
        this.pai = pai;
    }

    public boolean isLeaf() {
        return folha;
    }

    public void setLeaf(boolean folha) {
        this.folha = folha;
    }

    public List<Item<T>> getKeys() {
        return chaves;
    }

    public Item<T> getKey(int indice) {
        return chaves.get(indice);
    }

    public int getNumberOfKeys() {
        return chaves.size();
    }

    public void addKey(Item<T> item) {
        chaves.add(item);
    }

    public void addKey(int indice, Item<T> item) {
        chaves.add(indice, item);
    }

    public Item<T> removeKey(int indice) {
        return chaves.remove(indice);
    }

    public void addChild(NoB<T> filho) {
        filhos.add(filho);

        if (filho != null) {
            filho.setParent(this);
        }
    }

    public void addChild(int indice, NoB<T> filho) {
        filhos.add(indice, filho);

        if (filho != null) {
            filho.setParent(this);
        }
    }

    public NoB<T> removeChild(int indice) {
        return filhos.remove(indice);
    }

    public int getNumberOfChildren() {
        return filhos.size();
    }

    public void clearChildren() {
        filhos.clear();
    }
}