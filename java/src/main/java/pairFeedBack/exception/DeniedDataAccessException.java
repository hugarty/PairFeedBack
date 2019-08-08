package pairFeedBack.exception;

public class DeniedDataAccessException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DeniedDataAccessException (String arg){
        super(arg);
    }
}