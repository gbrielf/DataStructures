package Grafo;

public class VerticeInexistenteException extends RuntimeException{
    public VerticeInexistenteException(String err){
        super(err);
    }
}