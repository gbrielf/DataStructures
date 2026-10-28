package Grafo;

public class ArestaInexistenteException extends RuntimeException{
    public ArestaInexistenteException(String err){
        super(err);
    }
}