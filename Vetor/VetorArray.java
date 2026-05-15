package Vetor;

public class VetorArray implements Vetor{
    private int capacidade;
    private int tamanho;
    private Object[] a;
    private int inicio;

    public VetorArray(int capacidade){
        this.capacidade = capacidade;
        tamanho = 0;
        this.inicio = 0;
        a = new Object[capacidade];
    }

    private int indiceCircular(int ranking){
        return (inicio + ranking ) % capacidade;
    }

    public Object elemAtRank(int ranking){
        if(ranking > tamanho - 1 || ranking < 0){
            throw new VectorRankingException("O ranking informado não existe");
        }else if(isEmpty()){
            throw new VectorIsEmptyException("O vetor está vazio!");
        }

        return a[indiceCircular(ranking)];
    }

    public Object replaceAtRank(int ranking, Object elemento){
        if(ranking > tamanho - 1 || ranking < 0){
            throw new VectorRankingException("O ranking informado não existe");
        }else if(isEmpty()){
            throw new VectorIsEmptyException("Não é possível alterar elemento, o vetor está vazio!");
        }

        int idx = indiceCircular(ranking);
        Object resultado = a[idx];
        a[idx] = elemento;
        return resultado;
    }

    public void insertAtRank(int ranking, Object elemento){
        if(ranking > tamanho || ranking < 0){
            throw new VectorRankingException("O ranking informado não existe");
        }
        if(tamanho == capacidade){
            alterarCapacidade();
        }

        for(int i = tamanho; i > ranking; i--){
               a[indiceCircular(i)] = a[indiceCircular(i-1)]; 
        }

        a[indiceCircular(ranking)] = elemento;
        tamanho++;
    }

    public Object removeAtRank(int ranking){
        if(ranking > tamanho - 1 || ranking < 0){
            throw new VectorRankingException("O ranking informado não existe");
        }else if(isEmpty()){
            throw new VectorIsEmptyException("Não é possível remover, o vetor está vazio!");
        }

        Object resultado = a[indiceCircular(ranking)];

        for(int i = ranking; i < tamanho - 1; i++){
            a[indiceCircular(i)] = a[indiceCircular(i + 1)];
        }

        a[indiceCircular(tamanho-1)] = null;
        tamanho--;

        if (tamanho <= capacidade/3){
            alterarCapacidade();
        }
        
        return resultado;
    }

    public int size(){
        return tamanho;
    }

    public boolean isEmpty(){
        return tamanho == 0;
    }

    protected void alterarCapacidade(){
        int novaCapacidade = 0;

        if(tamanho == capacidade){
            novaCapacidade = capacidade * 2;
        }
        else if(tamanho <= capacidade/3){
            novaCapacidade = capacidade / 2;
        }

        Object[] b = new Object[novaCapacidade];

        for(int i = 0; i < tamanho; i++){
            b[i] = a[indiceCircular(i)];
        }

        a = b;
        capacidade = novaCapacidade;
        inicio = 0;
    }
}
