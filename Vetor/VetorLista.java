package Vetor;

public class VetorLista implements Vetor{
    private int capacidade;
    private int tamanho;
    private No inicio;
    private No fim;

    public VetorLista(){
        this.capacidade = 0;
        this.tamanho = 0;
        this.inicio = new No(null);
        this.fim = new No(null);
        this.inicio.setNext(fim);
        this.fim.setPrevious(inicio);
    }

    public Object elemAtRank(int r){
        
    }
}