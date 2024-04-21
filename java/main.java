

public class main {
    

    public static void main(String[] args) {
        // 2 3 2 Netz
        Double[] inputVektor = {-1.0,1.0}; // Input
        Double[][][] gewichte={{{1.0,-1.0},{1.0,1.0},{-1.0,1.0}},{{1.0,-0.5,-0.5},{-0.5,-0.5,1.0}}};
        int[] netzaufbau = {2,3,2}; // Netz mit 3 Layern. Input-Layer = 2 Neuronen.Hidden-Layer=3 Neuronen.Output-Layer=2 Neuronen
        Netz netz = new Netz(netzaufbau,inputVektor,gewichte); 
        netz.compute(); 
        netz.print();
        
        

        
        
    }
}
