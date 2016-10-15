import org.glo.giftw.domain.GameObject;
import org.glo.giftw.domain.Vector;

/**
 * Contient des données sur l'emplacement dans une @see Frame d'un GameObject
 */
public class Position
{
    private GameObject ref;
    private Vector position;
    private Vector speed;
    private boolean hasProjectile;


    public Position(GameObject ref, Vector position)
    {
        this.ref = ref;
        this.position = position;
        this.speed = new Vector(0, 0);
        this.hasProjectile = false;
    }

    public Position(GameObject ref, Vector position, boolean hasProjectile)
    {
        this(ref, position);
        this.hasProjectile = hasProjectile;
    }

    public Position(GameObject ref, Vector position, Vector speed, boolean hasProjectile)
    {
        this(ref, position, hasProjectile);
        this.speed = speed;
    }

    public GameObject getRef()
    {
        return this.ref;
    }

    public Vector getPosition()
    {
        return this.position;
    }

    /**
     * On ne devrait pas en avoir de besoin
     */
    public void setPosition(Vector position)
    {
        this.position = position;
    }

    public Vector getSpeed()
    {
        return this.speed;
    }

    public void setSpeed(Vector speed)
    {
        this.speed = speed;
    }

    public boolean hasProjectile()
    {
        return hasProjectile;
    }

    public void takeProjectile()
    {
        this.hasProjectile = true;
    }

    public void dropProjectile()
    {
        this.hasProjectile = false;
    }
}
