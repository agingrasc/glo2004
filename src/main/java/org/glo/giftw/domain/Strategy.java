import java.util.List;
import java.util.ArrayList;
import org.glo.giftw.domain.Frame;

/**
 * Contient les frames et les appels nécessaires pour les jouers
 */
public class Strategy
{
    private ArrayList<Frame> frames;
    private String name;

    public Strategy(String name)
    {
        super();
        this.name = name;
        this.frames = new ArrayList<Frame>();
    }

    public void addFrame(Frame frame)
    {
        this.frames.add(frame);
    }

    public List<Frame> getFrames()
    {
        return this.frames;
    }
}
