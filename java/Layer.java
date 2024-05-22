public class Layer {

    private Neuron[] neuronen;
    

    public Layer(){
    }
    
    public Layer(int AnzahlNeuronen,Double[] inputVektor,Funktion aktivierungsFunktion){
        this.neuronen = new Neuron[AnzahlNeuronen];
        for(int i=0;i<AnzahlNeuronen;i++){
            neuronen[i] = new Neuron(inputVektor[i],aktivierungsFunktion);
           
        }
    }

    public Layer(int AnzahlNeuronen,Funktion aktivierungsFunktion){
        this.neuronen=new Neuron[AnzahlNeuronen];
        for(int i =0;i<AnzahlNeuronen;i++){
            neuronen[i] = new Neuron(0.0,aktivierungsFunktion);
           
        }
    }
  
    public Neuron[] getNeuronen(){
        return this.neuronen;
    }

    public void setNeuronen(Neuron[] neuronen){
        this.neuronen=neuronen;
    }


  
}
