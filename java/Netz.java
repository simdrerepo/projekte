import java.util.Arrays;
import java.util.stream.Stream;

public class Netz {


    Layer firstLayer;
    
    int size = 0;


    public Netz(int laenge){
        // Laenge legt die Anzahl der Neuronen in diesem Layer fest
        this.firstLayer = new Layer(laenge);
        size++;
    }

    public void addLast(Layer l){       
       Layer tmp = firstLayer;
        while(tmp.next!=null){
            tmp = tmp.next;
        }
        tmp.next = l;
      
        size++;
    }

    public void print(){
        Layer tmp = firstLayer;
        while(tmp.next!=null){
            tmp.printNeuronenWert();
            tmp = tmp.next;
            
        }
        tmp.printNeuronenWert();
        
        
    }

    public void addInputVektor(Double[] input){
            for(int i = 0;i<input.length;i++){
                firstLayer.neuronen[i].setWert(input[i]);
            }

    }

   

    
    public void compute(){
     Layer tmp = firstLayer;
     
     while(tmp.next!=null){
        Neuron[] neuronen = tmp.getNeuronen();
        Neuron[] nextneuronen = tmp.next.getNeuronen();
        Neuron[] neueNeuronen = new Neuron[nextneuronen.length];

        Double[] gewichte;

        for(int i=0,n=neuronen.length;i<n;i++){
            
            
            for(int j=0,m=nextneuronen.length;j<m;j++){
                gewichte = nextneuronen[j].getGewichte();
                Neuron neuron = new Neuron();
                neuron.setWert(Aktivierungsfunktion.sigmoid(calcNextWert(gewichte,neuronen)));
                
                neueNeuronen[j]=neuron;
            }
        }
        
        tmp.next.setNeuronen(neueNeuronen);

        tmp = tmp.next;

        
       
     }
    
    
}
private Double calcNextWert(Double[] gewichte,Neuron[] neuronen){
            //Berechnet Skalarprodukt
        Double nextWert = 0.0;
  
        for(int i=0,n=gewichte.length;i<n;i++){
            nextWert = nextWert + gewichte[i]*neuronen[i].getWert();
        }
       
        return nextWert;
           
        }
       

private Double[] MatrixVektorMulti(Double[][] matrix, Double[] vektor){
    Double[] return_vektor = new Double[matrix.length];
    for(int i=0;i<return_vektor.length;i++){
        return_vektor[i] = 0.0;
    }
    for(int i =0;i<matrix.length;i++){
        for(int j=0;j<matrix[0].length;j++){
           return_vektor[i] = return_vektor[i] + matrix[i][j] * vektor[j];
          
        }
    }
        return return_vektor;
}
}