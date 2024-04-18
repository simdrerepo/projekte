public class Layer {

    Neuron[] neuronen;
  

    public Layer(){
    }
    
    public Layer(int AnzahlNeuronen,Double[] inputVektor){
        this.neuronen = new Neuron[AnzahlNeuronen];
        for(int i=0;i<AnzahlNeuronen;i++){
            neuronen[i] = new Neuron(inputVektor[i]);
        }
    }

    public Layer(int AnzahlNeuronen){
        this.neuronen=new Neuron[AnzahlNeuronen];
        for(int i =0;i<AnzahlNeuronen;i++){
            neuronen[i] = new Neuron(0.0);
        }
    }
    public void addNeuronen(){

    }
    public void printNeuronen(){
        for(Neuron n : neuronen){
            n.printWert();
        }
    }
   
    public Neuron[] getNeuronen(){
        return this.neuronen;
    }

    public void setNeuronen(Neuron[] neuronen){
        this.neuronen=neuronen;
    }


  
}
