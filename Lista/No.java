package Lista;

public class No {
    private No anterior;
    private No posterior;
    private Object elemento;

    public No(Object elemento){
        this.anterior = null;
        this.posterior = null;
        this.elemento = elemento;
    }
}
