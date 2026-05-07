package Lista;

public class ListaDuplamenteLigada implements Lista{
    private No head;
    private No tail;
    private int tamanho;

    public ListaDuplamenteLigada(){
        this.head = new No(null);
        this.tail = new No(null);
        this.head.setNext(tail);
        this.tail.setPrevious(head);
        tamanho = 0;
    }

    public int size(){
        return tamanho;
    }

    public boolean isEmpty(){
        return tamanho == 0;
    }

    public boolean isFirst(No n){
        return n.getPrevious() == head;
    }

    public boolean isLast(No n){
        return n.getNext() == tail;
    }

    public Object getFirst(){        
        // head.getNext() é equivalente a "proximo". proximo + getElement() = proximo.getElement()
        return head.getNext().getElement();
    }

    public Object getLast(){
        return tail.getPrevious().getElement();
    }

    public No before(No p){
        return p.getPrevious();
    }
    
    public No after(No p){
        return p.getNext();
    }

    public No insertBefore(No n, Object o){
        No novoNo = new No(o);
        novoNo.setNext(n);
        novoNo.setPrevious(n.getPrevious());
        n.getPrevious().setNext(novoNo);
        n.setPrevious(novoNo);
        tamanho++;

        return novoNo;
    }

    public No insertAfter(No n, Object o){
        No novoNo = new No(o);
        novoNo.setPrevious(n); //informa quem é o anterior ao novo nó
        novoNo.setNext(n.getNext()); // informa quem é o sucessor ao novo nó
        (n.getNext()).setPrevious(novoNo);
        n.setNext(novoNo);
        tamanho++;

        return novoNo;
    }

    public Object replaceElement(No n, Object o){
        Object resultado = n.getElement();
        n.setElement(o);

        return resultado;
    }

    public void swapElements(No n, No q){
        Object elementoATrocar = n.getElement();
        n.setElement(q.getElement());
        q.setElement(elementoATrocar);
    }

    public void insertFirst(Object elemento){
        No novoNo = new No(elemento);
        novoNo.setPrevious(head);
        novoNo.setNext(head.getNext());
        head.getNext().setPrevious(novoNo);
        head.setNext(novoNo);
        tamanho++;
    }

    public void insertLast(Object elemento){
        No novoNo = new No(elemento);
        novoNo.setNext(tail);
        novoNo.setPrevious(tail.getPrevious());
        tail.getPrevious().setNext(novoNo);
        tail.setPrevious(novoNo);
        tamanho++;
    }

    public Object remove(No n){
        n.getNext().setPrevious(n.getPrevious());
        n.getPrevious().setNext(n.getNext());
        tamanho--;

        return n.getElement();

    }


}