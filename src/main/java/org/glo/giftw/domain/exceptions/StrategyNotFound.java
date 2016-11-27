package org.glo.giftw.domain.exceptions;

public class StrategyNotFound extends Exception
{
    public StrategyNotFound()
    {
        super();
    }

    public StrategyNotFound(String s)
    {
        super(s);
    }

    public StrategyNotFound(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public StrategyNotFound(Throwable throwable)
    {
        super(throwable);
    }

    protected StrategyNotFound(String s, Throwable throwable, boolean b, boolean b1)
    {
        super(s, throwable, b, b1);
    }
}
