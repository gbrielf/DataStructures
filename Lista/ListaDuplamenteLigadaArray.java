package Lista;

public class ListaDuplamenteLigadaArray {
    private No[] a;
    private int capacidade;
    private int tamanho;
    private int head;
    private int tail;
    
    public ListaDuplamenteLigadaArray(int capacidade){
        this.capacidade = capacidade;
        this.tamanho = 0;
        this.head = -1;
        this.tail = -1;
        a = new No[capacidade];
    }

    public int size(){
        return tamanho;
    }

    public boolean isEmpty(){
        return tamanho == 0;
    }

    public boolean isFirst(No n){
        if(isEmpty()) return false;
        return a[head] == n;
    }

    public boolean isLast(No n){
        if(isEmpty()) return false;
        return a[tail] == n;
    }


    public No getFirst(){
        return a[head];
    }

    public No getLast(){
        return a[tail];
    }

    public No before(No p){
        int indiceDeP = -1;
        
        for(int i = 0; i < tamanho; i++){
            if (a[i] == p){
                indiceDeP = i;
                break;
            }
        }
        
        if(indiceDeP > 0){
            return a[indiceDeP - 1];
        }

        return null;
    }

    
    public No after(No p){
        int indiceDeP = -1;
        
        for(int i = 0; i < tamanho; i++){
            if (a[i] == p){
                indiceDeP = i;
                break;
            }
        }
        
        if(indiceDeP > 0){
            return a[indiceDeP + 1];
        }

        return null;
    }

    public Object replaceElement(No n, Object o){
        for(int i = 0; i < tamanho; i++){
            if(a[i] == n){
                Object elementoAntigo = a[i].getElement();
                a[i].setElement(o);
                return elementoAntigo;
            }
        }

        return null;
    }

    public void swapElements(No n, No q){
        Object temp = n.getElement();

        n.setElement(q.getElement());

        q.setElement(temp);
    }

    public No insertBefore(No n, Object o){
      if(tamanho == capacidade){
        throw new RuntimeException("A lista está cheia!");
      }  

      int indiceDeN = -1;

      for(int i = 0; i < tamanho; i++){
        if(a[i] == n){
            indiceDeN = i;
            break;
        }
      }

      for (int i = tamanho; i > indiceDeN; i--){
        a[i] = a[i - 1];
      }

      No novoNo = new No(o);
      a[indiceDeN] = novoNo;

      if(indiceDeN == 0){
        head = 0;
      }

      tail++;

      tamanho++;

      return novoNo;
    }

    public No insertAfter(No n, Object o){
        if(tamanho == capacidade){
            throw new RuntimeException("A lista está cheia!");
        }
        
        int indiceDeN = -1;
        
        
        for(int i = 0; i < tamanho; i++){
            if(a[i] == n){
                indiceDeN = i;
                break;
            }
        }

        if(indiceDeN >= 0 && indiceDeN < tamanho - 1){ throw new RuntimeException("O indice informado não está disponível!");  }


        for(int i = tamanho; i > indiceDeN + 1; i--){
            a[i] = a[i - 1];
        }

        No novoNo = new No(o);
        a[indiceDeN] = novoNo;

        tail++;
        tamanho++;

        return novoNo;

    }

    public void insertFirst(Object o){
        if(tamanho == capacidade){
            throw new RuntimeException("O lista está cheia!");
        }

        No novoNo = new No(o);
        
        for(int i = tamanho; i > 0; i--){
            a[i] = a[i - 1];
        }

        a[0] = novoNo;

        head = 0;
        tamanho++;
        tail++;
    }

    public void insertLast(Object o){
        if(tamanho == capacidade){
            throw new RuntimeException("O lista está cheia!");
        }

        No novoNo = new No(o);
        a[++tail] = novoNo;
            
        if(tamanho == 0){
            head = 0;
        }

        tamanho++;
    }

    public Object remove(No n){
        if(isEmpty()){
            throw new ListIsEmptyException("A lista já está vazia");
        }

        int indiceDeN = -1;

        for(int i = 0; i < tamanho; i ++){
            if(a[i] == n){
                indiceDeN = i;
                break;
            }
        }

        Object elementoRemovido = a[indiceDeN].getElement();

        for(int i = indiceDeN; i < tamanho; i++){
            a[i] = a[i + 1];
        }

        
        a[tail] = null;
        tail--;
        tamanho--;

        return  elementoRemovido;



    }
}
