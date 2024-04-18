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

    public void setWert(Double d){
        this.wert = d;
    }


}
