package callcenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 * @author Colin Hanbury
 */
public class CallQueue
{
// ------------------------------ FIELDS ------------------------------

    private static CallQueue instance;

    private int counter;

    private SimpleDateFormat formatter;

    private LinkedBlockingQueue<ServiceAgent> queue;
    //private LinkedBlockingQueue<Call> queue;

// -------------------------- STATIC METHODS --------------------------

    public static void queueSalesAssistant( ServiceAgent salesAssistant )
    {
        try
        {
            //Call call = new Call( getInstance().counter++, duration );
            log( "Queueing sales assistant: " + salesAssistant.getName());
            getInstance().queue.put( salesAssistant );
        }
        catch ( InterruptedException e )
        {
            log( "There was an error making the sales assistant available to take the call." );
        }
    }

    public static ServiceAgent retrieveSalesAssistant()
    {
        ServiceAgent salesAssistant = getInstance().queue.poll();
        if ( salesAssistant != null )
        {
            log( "Connecting call to sales assistant: " + salesAssistant.getName() );
        }
        return salesAssistant;
    }

    private static CallQueue getInstance()
    {
        if ( instance == null )
        {
            instance = new CallQueue();
        }
        return instance;
    }

    private static void log( String s )
    {
        System.out.println( "[" + getInstance().formatter.format( new Date() ) + "][CallQueue] " + s );
    }

// --------------------------- CONSTRUCTORS ---------------------------

    private CallQueue()
    {
        this.queue = new LinkedBlockingQueue<ServiceAgent>();
        this.counter = 1;
        this.formatter = new SimpleDateFormat( "HH:mm:ss" );
    }
}
