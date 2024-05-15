import java.util.Arrays;
import java.util.List;

public class main {

    

    public static void main(String[] args) {
        // 2 3 2 Netz
        String path ="data.csv";
        Double[] inputVektor = {0.0,0.0,1.0}; // Input
        Double[] sollVektor = {0.0, 0.0, 0.0,1.0};
       // Double[][][] gewichte={{{-0.081,0.06,-0.01,0.08}, {0.08,0.02,0.003,-0.09},{-0.04,-0.003,-0.09,-0.05} },{ {-0.008,0.06,0.04,-0.08},{0.01,-0.06,0.06,0.06}, {0.01,-0.027,0.08,0.09},{0.00029,-0.01,0.08,-0.001}} };
        MyCSVReader r = new MyCSVReader(path);
        List<String[]> list = r.read(";");
     
        int[] netzaufbau = {3,3,4}; 
        Netz netz = new Netz(netzaufbau,inputVektor,sollVektor,new Sigmoid(),new Quadratischerfehler(),new Sigmoid_Ableitung(),1.0,0.00001); 
      
        
      for(String[] s : list){
            Double[] d = r.convertToDouble(s);
            inputVektor[0] = d[0];
            inputVektor[1] = d[1];
            inputVektor[2] = d[2];
            sollVektor[0] = d[3];
            sollVektor[1] = d[4];
            sollVektor[2] = d[5];
            sollVektor[3] = d[6];
            netz.resetInputvektor(inputVektor);
            netz.setSollvektor(sollVektor);
            netz.start();
            r.write(inputVektor, sollVektor, netz.getGewichte(),"gewichte",list.indexOf(s),".csv");
      }
    


        
      

        
        
    }
}
