package FilaDePrioridade;

public class OrderQueueInvalidIndexException extends RuntimeException{
    public OrderQueueInvalidIndexException(String err){
      super(err);
    }
}
