package callcenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Colin Hanbury
 */
public class CallGenerator
    implements Runnable
{
// ------------------------------ FIELDS ------------------------------

    private SimpleDateFormat formatter;

    private Random random;

    private boolean running;
        
    private int callNumber;
    
    private double randomDouble;
    
    private AtomicInteger failure;
    
    private AtomicInteger total;
    

// --------------------------- CONSTRUCTORS ---------------------------

    public CallGenerator(int callNumber, AtomicInteger failure, AtomicInteger total, double randomDouble){
        this.callNumber = callNumber;
        random = new Random();
        formatter = new SimpleDateFormat( "HH:mm:ss" );
        this.failure = failure;
        this.total = total;
        this.randomDouble = randomDouble;
    }

// ------------------------ INTERFACE METHODS ------------------------

// --------------------- Interface Runnable ---------------------

    @Override
    public void run()
    {
        while ( running )
        {
            long startTime = random.nextInt(59)+1;
            log( "Call No. " + callNumber + " is going to sleep for " + startTime + " minutes" );
            sleep(startTime);
            
            log("Creating call No." + callNumber); //callNumber);            
            ServiceAgent salesAssistant = CallQueue.retrieveSalesAssistant();

            if(salesAssistant == null){
                log("No sales assistant available for call No. " + callNumber);
                failure.getAndIncrement();
                log("Call No. " + callNumber + " failed!");
                total.getAndIncrement();
                break;
            }
            
            Call call = new Call(callNumber, randomDouble);
            long duration = call.getDuration();
            salesAssistant.setStatus(ServiceAgentStatus.IN_A_CALL);
            sleep(duration);
            log("Call No. " + callNumber + " was successful.");
            log("Call No. " + callNumber +  " had a duration of: " + duration + " minutes");
            total.getAndIncrement();
            salesAssistant.setStatus(ServiceAgentStatus.FREE);
            break;
        }
    }

// -------------------------- OTHER METHODS --------------------------

    public void start()
    {
        running = true;
        new Thread( this ).start();
    }

    public void stop()
    {
        
        running = false;
    }

    private void log( String s )
    {
        System.out.println( "[" + formatter.format( new Date() ) + "][CallGenerator] " + s );
    }

    //generates random start times
    private void sleep(long sleep)
    {
        try
        {       
            Thread.sleep( sleep );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
    }
    
}
