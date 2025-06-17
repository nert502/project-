package ApiLayer.Container;

public class ResponseContainer <T>{
	
	    public T data;
	    public String message;

	    public ResponseContainer() {
	        this.message = "OK";
	    
	}
}
