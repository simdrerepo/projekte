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
            fehler = fehler + Math.pow(soll[i]-output[i],2);
        }
        return 0.5*fehler;
       }
    

 
  
  

    
}
