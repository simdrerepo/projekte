


public class main {

    

    public static void main(String[] args) {
        // 2 3 2 Netz
        String path ="C:\\KW17_weights_trafficlights_classification_simplified.csv";
        Double[] inputVektor = {1.0,0.0,0.0}; // Input
        Double[] sollVektor = {1.0, 0.0, 0.0,0.0};
       // Double[][][] gewichte={{{-0.081,0.06,-0.01,0.08}, {0.08,0.02,0.003,-0.09},{-0.04,-0.003,-0.09,-0.05} },{ {-0.008,0.06,0.04,-0.08},{0.01,-0.06,0.06,0.06}, {0.01,-0.027,0.08,0.09},{0.00029,-0.01,0.08,-0.001}} };
        MyCSVReader r = new MyCSVReader(path);
     
        int[] netzaufbau = {3,3,4}; 
        Netz netz = new Netz(netzaufbau,inputVektor,sollVektor,new Sigmoid(),new Quadratischerfehler(),new Sigmoid_Ableitung(),1.0,0.001); 
        
        netz.start();
        netz.print();
        
      
    


        
      

        
        
    }
}
