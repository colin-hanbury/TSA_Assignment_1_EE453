package callcenter;

import java.io.Serializable;

/**
 *
 * @author Colin Hanbury
 */
public class Call
    implements Serializable
{
// ------------------------------ FIELDS ------------------------------

    private long duration;
    private int number;
    private double random;

// --------------------------- CONSTRUCTORS ---------------------------

    public Call( int number, double random)
    {
        this.random = random;
        this.number = number;
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public long getDuration()
    {
        //System.out.println("Random number: " + random);
        double exponential = ((Math.log(random)/(-1.2073))*30);
        System.out.println("Exponential: " + exponential);
        duration = (long) exponential;
        return duration;
    }

    public int getNumber()
    {
        return number;
    }
}
