package org.glo.giftw.domain.exceptions;

public class MaxNumberException extends Exception
{
    public MaxNumberException()
    {
        super();
    }

    public MaxNumberException(String s)
    {
        super(s);
    }

    public MaxNumberException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public MaxNumberException(Throwable throwable)
    {
        super(throwable);
    }

    protected MaxNumberException(String s, Throwable throwable, boolean b, boolean b1)
    {
        super(s, throwable, b, b1);
    }
}
