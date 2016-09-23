import org.junit.Test;
import static org.junit.Assert.*;

public class Default {

    @Test
    public void success() {
        assertTrue(false);
    }

    @Test
    public void failure() {
        assertFalse(false);
    }
}
