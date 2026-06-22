package FilaDePrioridade;

public class OrderQueueIsEmptyException extends RuntimeException{
    public OrderQueueIsEmptyException(String err){
      super(err);
    }
}
