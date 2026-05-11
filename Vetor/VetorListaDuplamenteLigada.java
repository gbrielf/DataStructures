package Vetor;
import Lista.No;

public class VetorListaDuplamenteLigada implements Vetor{
    private int tamanho;
    private No inicio;
    private No fim;

    public VetorListaDuplamenteLigada(){
        this.tamanho = 0;
        this.inicio = new No(null);
        this.fim = new No(null);
        this.inicio.setNext(fim);
        this.fim.setPrev(inicio);
    }

    public int size(){
        return tamanho;
    }

    public boolean isEmpty(){
        return tamanho == 0;
    }

    public Object elemAtRank(int r){
        if (r >= size() || r < 0){
            throw new VectorRankingException("O ranking acessado não está disponível!");
        }else if(isEmpty()){
            throw new VectorIsEmptyException("O vetor está vazio");
        }

        No cursor = inicio.getNext();

        for (int i = 0; i < r; i++){
            cursor = cursor.getNext();
        }

        return cursor.getElement();
    }

    public Object replaceAtRank(int r, Object o){
        if (r >= size() || r < 0){
            throw new VectorRankingException("O ranking acessado não está disponível!");
        }else if(isEmpty()){
            throw new VectorIsEmptyException("O vetor está vazio");
        }
    
        No cursor = inicio.getNext();
        Object resultado;
    
        for(int i =0; i < r; i++){
            cursor = cursor.getNext();
        }
        
        resultado = cursor.getElement();
        cursor.setElement(o);
        return resultado;
                
    }

    public void insertAtRank(int r, Object novoElemento){
        if (r > size() || r < 0){
            throw new VectorRankingException("O ranking informado não está disponível!");
        }

        No novoNo = new No(novoElemento);

        No cursor = inicio;

        
        for(int i = 0; i < r; i++){
            cursor = cursor.getNext();
        }
        
        novoNo.setPrev(cursor);
        novoNo.setNext(cursor.getNext());
        cursor.getNext().setPrev(novoNo);
        cursor.setNext(novoNo);
        tamanho++;
    }

    public Object removeAtRank(int r){
        if(isEmpty()){
            throw new VectorIsEmptyException("O vetor está vazio!");
        }
        if(r >= size()|| r < 0){
            throw new VectorRankingException("O ranking informado não está disponível!");
        }
        
        No cursor = inicio.getNext();
        
        for(int i = 0; i < r; i++){
            cursor = cursor.getNext();
        }

        Object resultado = cursor.getElement();

        cursor.getPrev().setNext(cursor.getNext());
        cursor.getNext().setPrev(cursor.getPrev());

        tamanho--;
        return resultado;
        
    }
}