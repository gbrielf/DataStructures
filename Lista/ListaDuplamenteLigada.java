package Lista;

public class ListaDuplamenteLigada implements Lista{
    private No head;
    private No tail;
    private int tamanho;

    public ListaDuplamenteLigada(){
        this.head = new No(null);
        this.tail = new No(null);
        this.head.setNext(tail);
        this.tail.setPrev(head);
        tamanho = 0;
    }

    public int size(){
        return tamanho;
    }

    public boolean isEmpty(){
        return tamanho == 0;
    }

    public boolean isFirst(No n){
        return n.getPrev() == head;
    }

    public boolean isLast(No n){
        return n.getNext() == tail;
    }

    public No getFirst(){        
        if(isEmpty()){
            throw new ListIsEmptyException("A lista está vazia!");
        }
        // head.getNext() é equivalente a "proximo". proximo + getElement() = proximo.getElement()
        return head.getNext();
    }

    public No getLast(){
        if(isEmpty()){
            throw new ListIsEmptyException("A lista está vazia!");
        }
        return tail.getPrev();
    }

    public No before(No p){
        return p.getPrev();
    }
    
    public No after(No p){
        return p.getNext();
    }

    public No insertBefore(No n, Object o){
        No novoNo = new No(o);
        novoNo.setNext(n);
        novoNo.setPrev(n.getPrev());
        n.getPrev().setNext(novoNo);
        n.setPrev(novoNo);
        tamanho++;

        return novoNo;
    }

    public No insertAfter(No n, Object o){
        No novoNo = new No(o);
        novoNo.setPrev(n); //informa quem é o anterior ao novo nó
        novoNo.setNext(n.getNext()); // informa quem é o sucessor ao novo nó
        (n.getNext()).setPrev(novoNo);
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
        novoNo.setPrev(head);
        novoNo.setNext(head.getNext());
        head.getNext().setPrev(novoNo);
        head.setNext(novoNo);
        tamanho++;
    }

    public void insertLast(Object elemento){
        No novoNo = new No(elemento);
        novoNo.setNext(tail);
        novoNo.setPrev(tail.getPrev());
        tail.getPrev().setNext(novoNo);
        tail.setPrev(novoNo);
        tamanho++;
    }

    public Object remove(No n){
        n.getNext().setPrev(n.getPrev());
        n.getPrev().setNext(n.getNext());
        tamanho--;

        return n.getElement();

    }


}