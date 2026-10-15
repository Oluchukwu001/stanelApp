package stanel.app.store.exception;


public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String message){
        super(message);
    }
}
