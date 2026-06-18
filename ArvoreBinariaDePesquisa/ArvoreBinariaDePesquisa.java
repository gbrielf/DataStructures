package ArvoreBinariaDePesquisa;
import ArvoreGenerica.Arvore;


public class ArvoreBinariaDePesquisa<T> implements Arvore<No<T>>{
    private No<T> raiz;
    private int tamanho;
    
    public ArvoreBinariaDePesquisa(Item<T> item){
        raiz = new No<> (item, null);
    }

    @Override public No<T> root(){ return raiz; }

    @Override public No<T> parent(No<T> n){ return n.getParent(); }

    @Override public boolean isRoot(No<T> n){ return n.getParent() == null; }

    @Override public boolean isInternal(No<T> n){ return n.getLeftChild() != null || n.getRightChild() != null; }

    @Override public boolean isExternal(No<T> n){ return !isInternal(n); }

    @Override public int size() { return size(raiz);}

    private int size(No<T> n){
        if(n == null) return 0;
        return 1 + size(n.getLeftChild()) + size(n.getRightChild());
    }

    @Override public boolean isEmpty() { return raiz == null; }

    @Override public int depth(No<T> n){
        if(isRoot(n)) return 0;
        return 1 + depth(n.getParent());
    }

    @Override public int height(){
        return height(raiz);
    }

    private int height(No<T> n){
        if(isExternal(n)) return 0;
        int h = 0;
        if(n.getLeftChild() != null){
            h = Math.max(h, height(n.getLeftChild()));
        }
        if(n.getRightChild() != null){
            h = Math.max(h, height(n.getRightChild()));
        }

        return 1 + h;
    }

    public No<T> search(int chave) { 
        No<T> no = raiz;

        while(no != null && chave != no.getItem().getKey()){
            if(chave < no.getItem().getKey()){
                no = no.getLeftChild();
            }
            else{
                no = no.getRightChild();
            }
        }

        return no;
    }

    private No<T> searchParent(int chave){
        No<T> noAtual = raiz;

        if(noAtual == null){
           return raiz;
        }
        else{
            while(noAtual.getItem() != null){
                if(chave > noAtual.getItem().getKey() && noAtual.getRightChild() == null){
                    return noAtual;
                }
                else if(chave < noAtual.getItem().getKey() && noAtual.getLeftChild() == null){
                    return noAtual;
                }
                else{
                    if(chave > noAtual.getItem().getKey()){
                        noAtual = noAtual.getRightChild();
                    }else{
                        noAtual = noAtual.getLeftChild();
                    }
                }
            }
            return null;
        }
    }
    
    public void insert( Item<T> item) { 
        No<T> novoNo = new No<>(item, null);
        if(raiz == null){
            raiz = novoNo;
            return;
        }
        No<T> noPai = searchParent(item.getKey());

        if(noPai == null){
            return; // chave não existe
        }

        novoNo.setParent(noPai);
        
        if(item.getKey() > noPai.getItem().getKey()){
            noPai.setRightChild(novoNo);
        }else{
            noPai.setLeftChild(novoNo);
        }
    }

    public Item<T> remove(int chave) { 
        No<T> noAlvo = search(chave);
        

        if(isExternal(noAlvo)){
            if(noAlvo.isLeftChild()){
                Item<T> itemRemovido = noAlvo.getItem();
        
                noAlvo.getParent().setLeftChild(null);
        
                return itemRemovido;
            }
            else{
                Item<T> itemRemovido = noAlvo.getItem();
        
                noAlvo.getParent().setRightChild(null);
        
                return itemRemovido;
            }
        }
        return null;
    }
}
