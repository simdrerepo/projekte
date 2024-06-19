package projekseminar.funktionen;
public class Quadratischerfehler implements Funktion {

    @Override
    public Double execute(Double parameter) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }

    @Override
    public Double execute(Double[] output, Double[] soll) {
        Double fehler = 0.0;
        for(int i=0,n=output.length;i<n;i++){   
            fehler += Math.pow(soll[i]-output[i],2);  // [0.5, ....] [ 1.0,....] => (1.0-0.5)^2 
        }
        return 0.5*fehler;
       }
    

 
  
  

    
}
