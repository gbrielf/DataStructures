package ArvoreBinariaDePesquisa;
import java.util.Iterator;
import java.util.ArrayList;
import ArvoreGenerica.Arvore;


public class ArvoreBP<T> implements Arvore<No<T>, Item<T>>{
    private No<T> raiz;    
    
    public ArvoreBP(Item<T> item){
        raiz = new No<> (item, null);
    }

    @Override public No<T> getRoot(){ return raiz; }

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

    protected No<T> createNode(Item<T> item, No<T> parent){
        return new No<T>(item, parent);
    }

    protected int height(No<T> n){
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
    
    public No<T> insert( Item<T> item) { 
        No<T> novoNo = createNode(item, null);

        if(raiz == null){
            raiz = novoNo;
            return raiz;
        }

        No<T> noPai = searchParent(item.getKey());

        if(noPai == null){
            throw new RuntimeException("A chave não existe"); // chave não existe
        }

        novoNo.setParent(noPai);
        
        if(item.getKey() > noPai.getItem().getKey()){
            noPai.setRightChild(novoNo);
        }else{
            noPai.setLeftChild(novoNo);
        }

        return novoNo;
    }

    // incompleto
    public Item<T> remove(int chave) { 
        No<T> noAlvo = search(chave);
        
        // caso a árvore seja vazia ou noAlvo seja null
        if(isEmpty() || noAlvo == null){
            return null; // chave não existe
        }

        Item<T> itemAlvo = noAlvo.getItem();
        
        // se no alvo tiver dois filhos (raiz ou não)
        if(noAlvo.getLeftChild() != null && noAlvo.getRightChild() != null){
            No<T> menorNoSubstituto = smallestNode(noAlvo);

            // menorNoSubstituto já é o menor nó da subarvore direita, logo ele só pode ter filho direito(tem valor maior q o dele)
            // se menor nó tiver um filho direito, ele não pode ter um esquerdo pois já é o menor
            // adaptando local do menorNoSubstituto para poder realocalo para o lugar do no removido
            if(menorNoSubstituto.getRightChild() != null){
                menorNoSubstituto.getRightChild().setParent(menorNoSubstituto.getParent());
            }
            if(menorNoSubstituto.isLeftChild()){
                menorNoSubstituto.getParent().setLeftChild(menorNoSubstituto.getRightChild());
            }else{
                menorNoSubstituto.getParent().setRightChild(menorNoSubstituto.getRightChild());
            }

            noAlvo.setItem(menorNoSubstituto.getItem());

            return itemAlvo;
        }

        // se no alvo tiver 0 ou 1 filho
        else{
            No<T> filhoUnico;
            
            if(noAlvo.getLeftChild() != null){
                filhoUnico = noAlvo.getLeftChild();
            } else if(noAlvo.getRightChild() != null){
                filhoUnico = noAlvo.getRightChild();
            } else {
                filhoUnico = null; // noAlvo é folha
            }

            if(noAlvo == raiz){
                if(filhoUnico != null){
                    filhoUnico.setParent(null);
                }
                raiz = filhoUnico;
            } else {
                if(filhoUnico != null){
                    filhoUnico.setParent(noAlvo.getParent());
                }
                if(noAlvo.isLeftChild()){
                    noAlvo.getParent().setLeftChild(filhoUnico);
                } else {
                    noAlvo.getParent().setRightChild(filhoUnico);
                }
            }

            return itemAlvo;
        }
    }

    @Override
    public Iterator<No<T>> nos(){
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

    public void preOrder(No<T> n, ArrayList<No<T>> list){
        if(n == null) return;

        list.add(n);

        preOrder(n.getLeftChild(), list);
        preOrder(n.getRightChild(), list);
    }

    public void posOrder(No<T> n, ArrayList<No<T>> list){
        if(n == null) return;

        posOrder(n.getLeftChild(), list);
        posOrder(n.getRightChild(), list);

        list.add(n);
    }

    @Override
    public Iterator<Item<T>> elements(){
        Iterator<No<T>> nos = nos();

        ArrayList<Item<T>> itens = new ArrayList<>();
        
        while(nos.hasNext()){
            No<T> no = nos.next();
            itens.add(no.getItem());
        }
        
        return itens.iterator();
    }


    @Override
    public Iterator<No<T>> children(No<T> n){
        ArrayList<No<T>> filhos = new ArrayList<>();

        if(n.getLeftChild() != null) filhos.add(n.getLeftChild());

        if(n.getRightChild() != null) filhos.add(n.getRightChild());

        return filhos.iterator();
    }

    @Override
    public Object replace(No<T> n, Item<T> o){
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
