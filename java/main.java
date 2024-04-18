

public class main {
    

    public static void main(String[] args) {
        // 2 3 2 Netz
        Double[] inputVektor = {-1.0,1.0}; // Input
        Double[][][] gewichte={{{1.0,-1.0},{1.0,1.0},{-1.0,1.0}},{{1.0,-0.5,-0.5},{-0.5,-0.5,1.0}}};
    
        Netz netz = new Netz(3); // Netz Größe 3 anlegen (3 Layer)
      
       netz.addLayer(2,inputVektor); // Input Layer mit 2 Neuronen
       netz.setGewichte(gewichte); // Gewichte setzen
       netz.addLayer(3); // Hidden Layer mit 3 Neuronen
       netz.addLayer(2); // Output Layer mit 2 Neuronen
       netz.compute(); 
       netz.print();
        
        

        
        
    }
}
