package Service;

public class PageFailedToLoadException extends RuntimeException{
    public PageFailedToLoadException(String errorMessage, Throwable err){
        super(errorMessage, err);
    }
}
