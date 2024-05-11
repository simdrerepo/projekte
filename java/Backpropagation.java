public class Backpropagation {
    
   private Netz netz = null;
   private Double[] sollvektor;
   private Double fehler;
   private Funktion qf;

   public Backpropagation(Netz netz){
    this.netz=netz;
   }

   public void start(){
        
        this.netz.compute();
        this.fehler=calcFehler(qf);
        netz.backPropagade(this.sollvektor);
        Double[][][] gewichte = netz.updateGewichte();
        netz.setGewichte(gewichte);
        this.netz.compute();
        this.fehler=calcFehler(qf);
        
   }

   public Double calcFehler(Funktion q){
        return q.execute(netz.extractOutputVektor(),this.sollvektor); 
   }

   public void setSollvektor(Double[] d){
        this.sollvektor=d;
   }

   public Double[] getSollvektor(){
    return this.sollvektor;
   }

}
