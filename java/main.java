import java.util.Arrays;


public class main {

    public static Double[] generateInputVektor(int laenge){
        Double[] vektor = new Double[laenge];
        for(int i=0,n=vektor.length;i<n;i++){
            vektor[i] = main.randomInbetween(-1, 1);
        }
        return vektor;
    }

    public static Double randomInbetween(int min, int max){
        return Math.floor(((Math.random() * (max - min)) + min)*100) / 100;
    }
    

    public static void main(String[] args) {
        // 2 3 2 Netz
        String path ="C:\\KW17_weights_trafficlights_classification_simplified.csv";
        Double[] inputVektor = {1.0,0.0,0.0,}; // Input
        Double[] sollVektor = {1.0, 0.0, 0.0,0.0};
        Double[][][] gewichte={{{-0.081,0.06,-0.01,0.08},{0.08,0.02,0.003,-0.09},{-0.04,-0.003,-0.09,-0.05}},{{-0.008,0.06,0.04,-0.08},{0.01,-0.06,0.06,0.06},{0.01,-0.027,0.08,0.09},{0.00029,-0.01,0.08,-0.001}}};
        MyCSVReader r = new MyCSVReader(path);
        Double[][][] gewichte_ampel = r.convert();
        System.out.println(Arrays.deepToString(gewichte_ampel));
        int[] netzaufbau = {3,3,4}; 
        Netz netz = new Netz(netzaufbau,inputVektor,gewichte,new Sigmoid(),1.0); 
        netz.compute(); 
        netz.print();
        netz.backPropagade(sollVektor);
        netz.printDeltaWerte();
        netz.updateGewichte();
        
      
    


        
      

        
        
    }
}
