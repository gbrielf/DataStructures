public class PilhaArrayRubroNegra implements PilhaRubroNegra{
    private int N;
    private tvermelho;
    private tpreto;
    private Object a;
    
    public PilhaArrayRubroNegra(int N){
    this.N = N;
    tvermelho = -1;
    tpreto = N + 1;
    a = new Object[N*2];
    }
    
    public boolean estaVaziaVermelha(){
        return tv == -1;
    }
    
    public boolean estaVaziaPreta(){
        return tp == N;
    }

    public boolean estaCheia(){
        return tv = tp + 1;
    }

    public int sizeVermelho(){
    return tv+1;
    }

    public int sizePreto(){
        return N - tp;
    }

    public Object pushVermelho(Object o){
        if (estaCheia()){
            aumentarCapacidade();
        }
        S[++tv] = o;
    }

    public Object pushPreto(Object o){
        if (estaCheia()){
            aumentarCapacidade();
        }
        S[--tp] = o;
    }

    public Object popVermelho(){
        if (estaVaziaVermelha()){
            throw new RunTimeException("A Pilha Vermelha está vazia.");
        }
        Object resultado = a[tv];
        S[tv--] = Null;
        return resultado;
    }

    public Object popPreto(){
        if (estaVaziaPreta()){
            throw new RunTimeException("A Pilha Preta está vazia.");
        }
        Object resultado = a[tp];
        S[tp++] = Null;
        return resultado;
    }

    public Object topVermelho(){
        if (tv == -1){
            throw new PilhaVaziaException("Pilha vermelha está vazia");
        }
        return S[tv];
    }

    public Object topPreto(){
        if (tp == N){
            throw new PilhaVaziaException("Pilha Preta está vazia");
        }
        return S[tp];
    }

    public void aumentarCapacidade(){
        int capacidadeAntiga = capacidade;
        capacidade *= 2;
        Object[] b = new Object[capacidade];
        
        //for para pilha vermelha
        for (int i = 0; i <= tv; i++){
            b[i] = a[i]
        }

        int j = capacidade -1;

        //for para pilha preta
        for (int i = capacidadeAntiga - 1; i >= tp; i--){
            b[j--] = a[i]
        }

        tp = j+1; 

        a = b;
    }
        
}