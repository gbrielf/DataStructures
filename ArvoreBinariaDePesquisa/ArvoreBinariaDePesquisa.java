package ArvoreBinariaDePesquisa;
import java.util.Iterator;
import java.util.ArrayList;
import ArvoreGenerica.Arvore;


public class ArvoreBinariaDePesquisa<T> implements Arvore<No<T>>{
    private No<T> raiz;
    private int tamanho;
    
    public ArvoreBinariaDePesquisa(Item<T> item){
        raiz = new No<> (item, null);
    }

    @Override public No getRoot(){ return raiz; }

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

    // incompleto
    public Item<T> remove(int chave) { 
        No<T> noAlvo = search(chave);
        
        // caso a árvore seja vazia ou noAlvo seja null
        if(isEmpty() || noAlvo == null){
            return null; // chave não existe
        }
        
        // se no alvo for o raiz
        if(noAlvo == raiz){
            No<T> menorNo = smallestNode(noAlvo);
            
            Item<T> itemAlvo = menorNo.getItem();
            
            // se menor nó tiver um filho direito, ele não pode ter um esquerdo pois já é o menor
            if(menorNo.getRightChild() != null){
                menorNo.getRightChild().setParent(menorNo.getParent());
                menorNo.getParent().setLeftChild(menorNo.getRightChild());
            
                // se menor nó não tiver filho
            } else {
                menorNo.getParent().setLeftChild(null);
            }

            noAlvo.setItem(menorNo.getItem());

            return itemAlvo;
        }

        // se for interno com dois filhos
        if(isInternal(noAlvo) && noAlvo.getRightChild() != null && noAlvo.getLeftChild() != null){
            Item<T> itemAlvo = noAlvo.getItem();
        
            noAlvo.getLeftChild().setParent(noAlvo.getParent());
            noAlvo.getParent().setLeftChild(noAlvo.getLeftChild());

            noAlvo.getRightChild().setParent(noAlvo.getParent());
            noAlvo.getParent().setRightChild(noAlvo.getRightChild());
            
            return itemAlvo;
        }

        // se for nó interno e só tiver filho esquerdo
        else if(isInternal(noAlvo) && noAlvo.getRightChild() == null){
            noAlvo.getLeftChild().setParent(noAlvo.getParent());
            noAlvo.getParent().setLeftChild(noAlvo.getLeftChild());
        }

        // se for nó interno e só tiver filho direito
        else if(isInternal(noAlvo) && noAlvo.getLeftChild() == null){
            noAlvo.getRightChild().setParent(noAlvo.getParent());
            noAlvo.getParent().setRightChild(noAlvo.getRightChild());
        }

        // se for nó externo
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

    @Override
    public Iterator nos(){
        ArrayList<No<T>> list = new ArrayList<>();
        inOrder(raiz, list);

        return list.iterator();
    }

    public void inOrder(No<T> n, ArrayList<No<T>> list){
        if(n == null) return;
        
        inOrder(n.getLeftChild(), list);  // esquerda
        
        list.add(n);                       // raiz
        
        inOrder(n.getRightChild(), list); // direita        
    }

    @Override
    public Iterator elements(){
        ArrayList<No<T>> nos = new ArrayList<>();
        
        inOrder(raiz, nos);

        ArrayList<Item<T>> itens = new ArrayList<>();
        
        for(No<T> no : nos){
            itens.add(no.getItem());
        }
        
        return itens.iterator();
    }


    @Override
    public Iterator children(No<T> n){
        ArrayList<No<T>> filhos = new ArrayList<>();
        if(n.getLeftChild() != null) filhos.add(n.getLeftChild());
        if(n.getRightChild() != null) filhos.add(n.getRightChild());
        return filhos.iterator();
    }

      @Override
    public Object replace(No<T> n, Object o){
        Item<T> itemAntigo = n.getItem();
        n.setItem((Item<T>)o);
        return itemAntigo;
    }

    public No<T> smallestNode(No<T> n){
        if(n == null){
            return null;
        }
        if(n.getRightChild() != null){
            n = n.getRightChild();
            while(n.getLeftChild() != null){
                n = n.getLeftChild();
            }
            return n;
        }
        else{
            return n;
        }

    }
}
