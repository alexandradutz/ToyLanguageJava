package domain;

/**
 * Created by Dutzi on 11/9/2015.
 */
public class DomainException extends Exception {
    public DomainException(){
        super("Domain Exception!");
    }

    public DomainException(String msg)
    {
        super(msg);
    }
}
