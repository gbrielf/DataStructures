package Sequencia;
import Lista.ListaDuplamenteLigada;
import Lista.No;
import Vetor.VectorRankingException; 
import Vetor.VectorIsEmptyException; 


class SequenciaComLista extends ListaDuplamenteLigada implements Sequencia{
    No head;
    No tail;
    int tamanho;

    public SequenciaComLista(){
        tamanho = 0;
        head.getNext(tail);
        tail.getPrev(head);
    }

    public Object elemAtRank(int r){
        if (r >= size() || r < 0){
            throw new VectorRankingException("O ranking acessado não está disponível!");
        }else if(isEmpty()){
            throw new VectorIsEmptyException("O vetor está vazio");
        }

        No cursor = head.getNext();

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
    
        No cursor = head.getNext();
        Object resultado;
    
        for(int i =0; i < r; i++){
            cursor = cursor.getNext();
        }
        
        resultado = cursor.getElement();
        cursor.setElement(o);
        return resultado;
    }

    public void insertAtRank(int r, Object o){
        if (r > size() || r < 0){
            throw new VectorRankingException("O ranking informado não está disponível!");
        }

        No novoNo = new No(novoElemento);

        No cursor = head;

        
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
        
        No cursor = head.getNext();
        
        for(int i = 0; i < r; i++){
            cursor = cursor.getNext();
        }

        Object resultado = cursor.getElement();

        cursor.getPrev().setNext(cursor.getNext());
        cursor.getNext().setPrev(cursor.getPrev());

        tamanho--;
        return resultado;    
    }

    public No atRank(int r){
        No novoNo;

        if(r <= size()/2){
            novoNo = head.getNext();
        }else{
            novoNo = tail.getPrev();
            for(int i = 0; i < size() - r - 1; i++){
                novoNo = novoNo.getPrev();
            }
        }
        return novoNo;
    }

    public int rankOf(No no){
        Nó novoNo = head.getNext();
        int indice = 0;
        while(novoNo != no && novoNo != tail){
            novoNo = novoNo.getNext();
            indice++;
        }
        return indice;
    }
}