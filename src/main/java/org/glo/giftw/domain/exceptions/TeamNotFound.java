package org.glo.giftw.domain.exceptions;

public class TeamNotFound extends Exception
{
    public TeamNotFound()
    {
        super();
    }

    public TeamNotFound(String s)
    {
        super(s);
    }

    public TeamNotFound(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public TeamNotFound(Throwable throwable)
    {
        super(throwable);
    }

    protected TeamNotFound(String s, Throwable throwable, boolean b, boolean b1)
    {
        super(s, throwable, b, b1);
    }
}
