package callcenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Colin Hanbury
 */
public class CallCenterSimulator
{
// --------------------------- main() method ---------------------------

    public static void main( String... args ) throws InterruptedException
    {
        Random random = new Random();
        List<Double> gosValues = new ArrayList<Double>();
        //run simulation k times
        for(int k = 0; k<150; k++){
            int numCalls = k+1;
            String[] employees = {"Abby", "Caroline", "John", "David", "Tara", "Tom", "Laura", "James", "Dee", "Rachael"};
            AtomicInteger failures = new AtomicInteger(0);
            AtomicInteger total = new AtomicInteger(0);        
            
            for ( int i = 0; i < 10; i++ ){
                new ServiceAgent( employees[i] ).start();
            }
            for ( int j = 0; j < numCalls; j++){
                new CallGenerator(j + 1, failures, total, random.nextDouble()).start();
            }
            while( total.intValue()< numCalls){
                //wait until all calls have completed
            }
            double fails = failures.intValue();
            double totalCalls = total.intValue();
            System.out.println("Failures: " + failures.intValue());
            System.out.println("Total: " + total.intValue());

            double gos = ((fails)/(totalCalls))*100;
            System.out.println("Grade of Service for simulation is: " + gos + "%");
            gosValues.add(gos);
        }
        int n = 1;
        System.out.println("\nResults:\n");
        for(double gos: gosValues){
            //System.out.println("GOS: " + gos + " for N = " + n);
            System.out.println(gos);
            n++;
        }
    }

}