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
        Double[] vektor = new Double[neuronen.length];
        for(int i =0;i<neuronen.length;i++){
            vektor[i] = neuronen[i].getWert();
        }
        Double[][] matrix = new Double[tmp.next.neuronen.length][neuronen.length];
      
        Neuron[] nextneuronen = tmp.next.getNeuronen();
        for(int i =0;i < nextneuronen.length;i++){
            matrix[i] = nextneuronen[i].gewichte;
        }

      //  System.out.println(Arrays.deepToString(matrix));
       // System.out.println(Arrays.deepToString(vektor));

        Double[] newvektor = MatrixVektorMulti(matrix, vektor);
        for(int i =0;i<newvektor.length;i++){
            tmp.next.neuronen[i].setWert(Aktivierungsfunktion.sigmoid(newvektor[i]));
        }
        tmp =tmp.next;
       
     }
    
    
}

public Double[] MatrixVektorMulti(Double[][] matrix, Double[] vektor){
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