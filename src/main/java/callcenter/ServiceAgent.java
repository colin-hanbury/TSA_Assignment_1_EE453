package callcenter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Colin Hanbury
 */
public class ServiceAgent
    implements Runnable
{
// ------------------------------ FIELDS ------------------------------

    private SimpleDateFormat formatter;

    private String name;

    private boolean running;

    private ServiceAgentStatus status;
    

// --------------------------- CONSTRUCTORS ---------------------------

    public ServiceAgent( String name )
    {
        this.name = name;
        this.status = ServiceAgentStatus.FREE;
        formatter = new SimpleDateFormat( "HH:mm:ss" );
    }

// ------------------------ INTERFACE METHODS ------------------------

// --------------------- Interface Runnable ---------------------

    @Override
    public void run()
    {
        while ( running )
        {
            //lenght of experiment
            long startTime = System.currentTimeMillis();
            while(System.currentTimeMillis() < (startTime + 60)){
                if ( status == ServiceAgentStatus.FREE )
                {
                    CallQueue.queueSalesAssistant(this);
                    log( "Awaiting a call ");
                    status = ServiceAgentStatus.WAITING_FOR_CALLS;
                }
            }
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
        System.out.println( "[" + formatter.format( new Date() ) + "][ServiceAgent][Agent " + name + "] " + s );
    }


    public String getName() {
        return this.name;
    }

    void setStatus(ServiceAgentStatus newStatus) {
            status  = newStatus;
    }
}
