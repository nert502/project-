package BusinessLogicLayer.exception;

public class BusinessLayerException  extends Exception{
	
	 private static final long serialVersionUID = 1L;
	
	public BusinessLayerException(String message) {
        super(message);
    }
	
	public BusinessLayerException(String message, Throwable cause) {
        super(message, cause);
    }

}
