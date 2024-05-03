public class Schwellenwert implements Funktion{

    private Double schwellenwert = 1.5;

    @Override
    public Double execute(Double parameter) {
     
       return (parameter >= schwellenwert) ? 1.0 : 0.0;
    }

    public void setSchwellenwert(Double d){
        this.schwellenwert = d;
    }
    public Double getSchwellenwert(){
        return this.schwellenwert;
    }
    
}
