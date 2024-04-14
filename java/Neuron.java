public class Neuron {
    
        
        private Double wert;
        public Double[] gewichte;
       
        public Neuron(Double[] gewichte){
                this.gewichte=gewichte;
        }
        public Neuron(){
            
        }
       
    public Double getWert(){
        return this.wert;
    }

    public void setWert(Double d){
        this.wert = d;
    }


}
