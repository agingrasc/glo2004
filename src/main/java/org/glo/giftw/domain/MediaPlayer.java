package org.glo.giftw.domain;

import java.util.ArrayList;
import java.util.List;

import org.glo.giftw.domain.Frame;

public class MediaPlayer
{
    private ArrayList<Frame> framesToPlay;

    public MediaPlayer(List<Frame> framesToPlay)
    {
        super();
        this.framesToPlay = new ArrayList<Frame>(framesToPlay);
    }

    public Frame playFrame()
    {
        Frame frame = this.framesToPlay.get(0);
        this.framesToPlay.remove(0);
        return frame;
    }
}
