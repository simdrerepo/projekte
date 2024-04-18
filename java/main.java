

public class main {
    

    public static void main(String[] args) {
        // 2 3 2 Netz
        Double[] inputVektor = {-1.0,1.0}; // Input
        Double[][][] gewichte={{{1.0,-1.0},{1.0,1.0},{-1.0,1.0}},{{1.0,-0.5,-0.5},{-0.5,-0.5,1.0}}};
    
        Netz netz = new Netz(3);
      
       netz.addLayer(2,inputVektor);
       netz.setGewichte(gewichte);
       netz.addLayer(3);
       netz.addLayer(2);
       netz.compute(); 
       netz.print();
        
        

        
        
    }
}
