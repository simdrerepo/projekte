

public class main {
    

    public static void main(String[] args) {
        // 2 3 2 Netz
        Double[] inputVektor = {-1.0,1.0}; // Input
        Double[][] hiddenLayerGewichte = {{1.0,-1.0},{1.0,1.0},{-1.0,1.0}}; // Gewichte für Hidden Layer
        Double[][] outputLayerGewichte = {{1.0,-0.5,-0.5},{-0.5,-0.5,1.0}}; // Gewichte für Output Layer 
    
        Netz netz = new Netz(2); // Input Layer
        netz.addInputVektor(inputVektor);
      
        netz.addLast(new Layer(3,hiddenLayerGewichte)); // Hidden Layer 
        netz.addLast(new Layer(2,outputLayerGewichte));  // Output Layer
       
        netz.compute();
        netz.print();
        
        
        

        
        
    }
}
