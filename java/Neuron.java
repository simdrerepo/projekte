public class Neuron { 
        
        private Double wert;
        private Double[] gewichte;
       
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
    public Double[] getGewichte(){
        return this.gewichte;
    }
    public void setGewichte(Double[] gewichte){
    this.gewichte=gewichte;
    }
    public void setWert(Double d){
        this.wert = d;
    }


}
