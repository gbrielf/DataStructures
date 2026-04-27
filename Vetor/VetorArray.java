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

    private int indiceFisico(int ranking){
        return (inicio + ranking ) % capacidade;
    }

    public Object elemAtRank(int ranking){
        if(ranking > tamanho - 1 || ranking < 0){
            throw new VetorRankingException("O ranking informado não existe");
        }else if(isEmpty()){
            throw new VetorVazioException("O vetor está vazio!");
        }

        return a[indiceFisico(ranking)];
    }

    public Object replaceAtRank(int ranking, Object elemento){
        if(ranking > tamanho - 1 || ranking < 0){
            throw new VetorRankingException("O ranking informado não existe");
        }else if(isEmpty()){
            throw new VetorVazioException("Não é possível alterar elemento, o vetor está vazio!");
        }

        int idx = indiceFisico(ranking);
        Object resultado = a[idx];
        a[idx] = elemento;
        return resultado;
    }

    public void insertAtRank(int ranking, Object elemento){
        if(ranking > tamanho - 1 || ranking < 0){
            throw new VetorRankingException("O ranking informado não existe");
        }
        if(ranking == capacidade - 1){
            aumentarCapacidade();
        }

        for(int i = tamanho; i > ranking; i--){
               a[indiceFisico(i)] = a[indiceFisico(i-1)]; 
        }
        a[indiceFisico(ranking)] = elemento;
        tamanho++;
    }

    public Object removeAtRank(int ranking){
        if(ranking > capacidade - 1 || ranking < 0){
            throw new VetorRankingException("O ranking informado não existe");
        }else if(isEmpty()){
            throw new VetorVazioException("Não é possível remover, o vetor está vazio!");
        }

        Object resultado = a[ranking];

        for(int i = ranking; i < tamanho - 1; i++){
            a[i] = a[i + 1];
        }

        a[tamanho-1] = null;
        tamanho--;
        
        return resultado;
    }

    public int size(){
        return tamanho;
    }

    public boolean isEmpty(){
        return tamanho == 0;
    }

    public void aumentarCapacidade(){
        int novaCapacidade = capacidade * 2;
        Object[] b = new Object[novaCapacidade];

        for(int i = 0; i < tamanho; i++){
            b[i] = a[indiceFisico(i)];
        }

        a = b;
        capacidade = novaCapacidade;
        inicio = 0;
    }
}
