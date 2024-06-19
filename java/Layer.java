public class Layer {

    private Neuron[] neuronen;
    

    public Layer(){
    }


    public Layer(int AnzahlNeuronen){
        this.neuronen=new Neuron[AnzahlNeuronen];
        for(int i =0;i<AnzahlNeuronen;i++){
            neuronen[i] = new Neuron(0.0);
           
        }
    }
  
    public Neuron[] getNeuronen(){
        return this.neuronen;
    }

    public void setNeuronen(Neuron[] neuronen){
        this.neuronen=neuronen;
    }


  
}
