public class PilhaArrayRubroNegra implements PilhaRubroNegra{
    private int N;
    private int tv;
    private int tp;
    private Object[] a;
    
    public PilhaArrayRubroNegra(int N){
    this.N = N;
    tv = -1;
    tp = N + 1;
    a = new Object[N*2];
    }
    
    public boolean estaVaziaVermelha(){
        return tv == -1;
    }
    
    public boolean estaVaziaPreta(){
        return tp == N;
    }

    public boolean estaCheia(){
        return tv == tp + 1;
    }

    public int sizeVermelha(){
    return tv + 1;
    }

    public int sizePreta(){
        return N - tp;
    }

    public void pushVermelha(Object o){
        if (estaCheia()){
            aumentarCapacidade();
        }
        a[++tv] = o;
    }

    public void pushPreta(Object o){
        if (estaCheia()){
            aumentarCapacidade();
        }
        a[--tp] = o;
    }

    public Object popVermelha(){
        if (estaVaziaVermelha()){
            throw new RuntimeException("A Pilha Vermelha está vazia.");
        }
        Object resultado = a[tv];
        a[tv--] = null;
        return resultado;
    }

    public Object popPreta(){
        if (estaVaziaPreta()){
            throw new RuntimeException("A Pilha Preta está vazia.");
        }
        Object resultado = a[tp];
        a[tp++] = null;
        return resultado;
    }

    public Object topVermelha(){
        if (tv == -1){
            throw new PilhaRubroNegraVaziaException("Pilha vermelha está vazia");
        }
        return a[tv];
    }

    public Object topPreta(){
        if (tp == N){
            throw new PilhaRubroNegraVaziaException("Pilha Preta está vazia");
        }
        return a[tp];
    }

    public void aumentarCapacidade(){
        int nAntiga = N;
        N *= 2;
        Object[] b = new Object[N];
        
        //for para pilha vermelha
        for (int i = 0; i <= tv; i++){
            b[i] = a[i];
        }

        int j = N -1;

        //for para pilha preta
        for (int i = nAntiga - 1; i >= tp; i--){
            b[j--] = a[i];
        }

        tp = j+1; 

        a = b;
    }
        
}