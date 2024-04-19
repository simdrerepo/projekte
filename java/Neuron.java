public class Neuron { 
        
        private Double wert;
        
     
       
        public Neuron(Double wert){
        
                this.wert=wert;
        }
        public Neuron(){ 
        }

        public void printWert(){
            System.out.println(wert);
        }
       
    public Double getWert(){
        return this.wert;
    }

    public void setAF(){
        this.wert = sigmoid(this.wert);
    }

    private Double sigmoid(Double parameter){
        return  1 / (1 + Math.exp(-parameter));
    }

    public void setWert(Double d){
        Aktivierungsfunktion af = new Sigmoid();
        this.wert = af.funktion(d);
    }
    public void setWert(Double d, Aktivierungsfunktion af){
        this.wert = af.funktion(d);
        
    }


}
