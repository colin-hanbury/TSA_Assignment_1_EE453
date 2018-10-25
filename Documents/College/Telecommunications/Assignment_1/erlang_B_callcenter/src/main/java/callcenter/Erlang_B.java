package callcenter;

/**
 *
 * @author Colin Hanbury
 */
public class Erlang_B {

    
    public static void main(String[] args) {
        int n=4; // number of channels
        int k=0;
        double callsOnDay;
        double avgDuration = 4.2;//minutes
        double probability;
        double traffic;
        double tempBottom;
        double topOfEquation;
        double trafficToN;
        double trafficToJ;
        double totalBottomOfEquation;


            //loop through to get each load
            for(k=0; k<150; k++){
                callsOnDay = k;
                avgDuration = 4.2;//minutes
                probability = 0;
                traffic = 0;
                tempBottom=0;
                topOfEquation = 0;
                trafficToN = 0;
                trafficToJ = 0;
                totalBottomOfEquation = 0;
                //Ao
                traffic = callsOnDay*avgDuration;
                //System.out.println("traffic: " + traffic);
                //Ao^n
                trafficToN = Math.pow(traffic, n);
                //System.out.println("trafficToN: " + trafficToN);
                //n factorial
                double nFactorial = 1;
                int N = n;
                for(N=N; N>0; N--){
                   nFactorial = nFactorial * N;
                }
                //System.out.println("nFactorial: " + nFactorial);

                topOfEquation = trafficToN/nFactorial;
                //System.out.println("topOfEquation: " + topOfEquation);



                int j=0;
                double iFactorial = 0;
                //add n times
                for(j=0; j<=n; j++){ 
                    //Ao^i
                    trafficToJ = Math.pow(traffic, j);
                    //System.out.println("trafficToJ: " + trafficToJ);
                    //i factorial
                    int i=0;
                    for(i=0; i<=j; i++){
                        iFactorial = iFactorial * i;
                        if(iFactorial == 0){
                            iFactorial = 1;
                        }
                    }
                    //System.out.println("iFactorial: " + iFactorial);


                    tempBottom = trafficToJ/iFactorial;
                    //System.out.println("bottomOfEquation: " + tempBottom);
                    //add iteratively
                    totalBottomOfEquation = totalBottomOfEquation + tempBottom;
                    //System.out.println("totalBottomOfEquation: " + totalBottomOfEquation);
                }


                probability = topOfEquation/totalBottomOfEquation;
                double GOS = (probability*100);
                System.out.println(GOS);
            }
            
        }
    
}
