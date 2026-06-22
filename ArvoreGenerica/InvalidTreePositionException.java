package ArvoreGenerica;

public class InvalidTreePositionException extends RuntimeException{
    public InvalidTreePositionException(String err){
        super(err);
    }
}
