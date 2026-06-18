package ArvoreBinariaDePesquisa;

public class ABInvalidIndexException extends RuntimeException{
    public ABInvalidIndexException(String err){
      super(err);
    }
}