public class Neuron { 
        
        private Double input ;
        private Double output;
        private Double deltawert;
        
       
        public Neuron(Double wert){

            this.input=wert;
            this.output=wert;
             
        }
        public Neuron(Double wert,Funktion aktivierungsFunktion){
            this.input=wert;
            this.input=wert;
        }
        public Neuron(){ 
        }

     
       
    public Double getInput(){
        return this.input;
    }
    public Double getOutput(){
        return this.output;
    }
    public Double getDeltawert(){
        return this.deltawert;
    }
    public void setDeltawert(Double d){
        this.deltawert=d;
    }
    public void setOutput(Double d,Funktion f){ // Setzt den Wert mit einer Aktivierungsfunktion
        this.output=f.execute(d);
    }
    public void setOutput(Double d){
        this.output=d;
    }
    public void setInput(Double d){
        this.input=d;
    }
    public void setWert(Double d){
            this.input = d;
    }



}
