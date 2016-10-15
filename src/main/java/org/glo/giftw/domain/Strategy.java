package org.glo.giftw.domain;

import java.util.List;
import java.util.ArrayList;
import org.glo.giftw.domain.Frame;

/**
 * Contient les frames et les appels nécessaires pour les jouers
 */
public class Strategy
{
    private String name;
    private Sport sport;
    private ArrayList<Frame> frames;

    public Strategy(String name, Sport sport)
    {
        this.name = name;
        this.sport = sport;
        this.frames = new ArrayList<Frame>();
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Sport getSport()
    {
        return this.sport;
    }
    
    public void setSport(Sport sport)
    {
        this.sport = sport;
    }

    public List<Frame> getFrames()
    {
        return this.frames;
    }
    
    public void setFrames(ArrayList<Frame> frames)
    {
        this.frames = frames;
    }
    
    public Frame getFrame(int frameId)
    {
        return this.frames.get(frameId);
    }
    
    public void addFrame(int frameId, Frame frame)
    {
        this.frames.add(frameId, frame);
    }
}
