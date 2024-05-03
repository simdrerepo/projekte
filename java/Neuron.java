public class Neuron { 
        
        
        private Funktion aktivierungsfunktion = null;
        private Funktion ausgabefunktion;
        private Double wert ;
       
        
     
       
        public Neuron(Double wert){
            aktivierungsfunktion = new Identität();
            ausgabefunktion = new Identität();
            this.wert=wert;
             
        }
        public Neuron(Double wert,Funktion aktivierungsFunktion){
            this.aktivierungsfunktion=aktivierungsFunktion;
            this.ausgabefunktion = new Identität();
            this.wert=wert;
        }
        public Neuron(){ 
        }

        public void printWert(){
            System.out.println(getWertWith());
        }
       
    public Double getWert(){
        return this.wert;
    }

    public Double getWertWith(){

        return this.ausgabefunktion.execute(this.wert);
    }
    public void setWertWith(Double d){
        this.wert = this.aktivierungsfunktion.execute(d);
    }


    public void setWert(Double d){
       
            this.wert = d;
        
        
    }

   public void setAusgabefunktion(Funktion ausgabefunktion){
        this.ausgabefunktion=ausgabefunktion;
   }
   
    public void setAktivierungsfunktion(Funktion aktivierungsfunktion){
        this.aktivierungsfunktion=aktivierungsfunktion;
    }


}
