public class Neuron { 
        
        
        private Aktivierungsfunktion af = null;
        private Double wert ;
        
     
       
        public Neuron(Double wert){
            af = new Identität();
            this.wert=af.funktion(wert);
             
        }
        public Neuron(){ 
        }

        public void printWert(){
            System.out.println(wert);
        }
       
    public Double getWert(){
        return this.wert;
    }


    public void setWert(Double d){
       
            this.wert = this.af.funktion(d);
        
        
    }

   
   
    public void setAktivierungsfunktion(Aktivierungsfunktion af){
        this.af=af;
    }


}
