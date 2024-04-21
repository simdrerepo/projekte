public class Neuron { 
        
        
        private Funktion aktivierungsfunktion = null;
        private Funktion ausgabefunktion;
        private Double wert ;
       
        
     
       
        public Neuron(Double wert){
            aktivierungsfunktion = new Identität();
            ausgabefunktion = new Identität();
            this.wert=aktivierungsfunktion.execute(wert);
             
        }
        public Neuron(){ 
        }

        public void printWert(){
            System.out.println(getWert());
        }
       
    public Double getWert(){
        return this.ausgabefunktion.execute(this.wert);
    }


    public void setWert(Double d){
       
            this.wert = this.aktivierungsfunktion.execute(d);
        
        
    }

   public void setAusgabefunltion(Funktion ausgabefunktion){
        this.ausgabefunktion=ausgabefunktion;
   }
   
    public void setAktivierungsfunktion(Funktion aktivierungsfunktion){
        this.aktivierungsfunktion=aktivierungsfunktion;
    }


}
