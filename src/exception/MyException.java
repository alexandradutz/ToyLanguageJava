package exception;

/**
 * Created by Dutzi on 10/21/2015.
 */
public class MyException extends Exception{
    public MyException()
    {
        super("Undefined!");
    }

    public MyException(String msg)
    {
        super(msg);
    }

}
