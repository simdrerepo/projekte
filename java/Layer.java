public class Layer {


    Neuron[] neuronen;
   
    Layer next = null;
    Layer previous=null; 
    

    public Layer(){

    }
    
    public Layer(int vektorLaenge,Double[][] gewichte){
        this.neuronen = new Neuron[vektorLaenge];
        for(int i=0;i<vektorLaenge;i++){
            neuronen[i] = new Neuron(gewichte[i]);
            
        }
       
    }
    public Layer(int vektorLaenge){
        this.neuronen=new Neuron[vektorLaenge];
        for(int i =0;i<vektorLaenge;i++){
            neuronen[i] = new Neuron();
        }
    }
   

    public Neuron[] getNeuronen(){
        return this.neuronen;
    }
    public void setNeuronen(Neuron[] neuronen){
        this.neuronen=neuronen;
    }

    public void printNeuronenWert(){
        
       for(Neuron n : neuronen){
            System.out.println(n.getWert());
       }
       System.out.println("---------------");
      

    }
  
}
