package org.glo.giftw.domain.exceptions;

/**
 *
 */
public class GameObjectNotFound extends Exception
{
    public GameObjectNotFound()
    {
        super();
    }

    public GameObjectNotFound(String s)
    {
        super(s);
    }

    public GameObjectNotFound(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public GameObjectNotFound(Throwable throwable)
    {
        super(throwable);
    }

    protected GameObjectNotFound(String s, Throwable throwable, boolean b, boolean b1)
    {
        super(s, throwable, b, b1);
    }
}
