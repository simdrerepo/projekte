import java.util.Arrays;
import java.util.List;

public class main {

    

    public static void main(String[] args) {

        String path ="data.csv";
        Double[] inputVektor = {0.0,0.0,1.0}; // Input
        Double[] outputVektor = {0.0, 0.0, 0.0,1.0};
       // Double[][][] gewichte={{{-0.081,0.06,-0.01,0.08}, {0.08,0.02,0.003,-0.09},{-0.04,-0.003,-0.09,-0.05} },{ {-0.008,0.06,0.04,-0.08},{0.01,-0.06,0.06,0.06}, {0.01,-0.027,0.08,0.09},{0.00029,-0.01,0.08,-0.001}} };
        MyCSVReader r = new MyCSVReader(path);
        List<String[]> list = r.read(";");
     

        Netz netz = new Netz();
        netz.addHiddenLayer(3);
        netz.setInputvektor(inputVektor);
        netz.setOutputvektor(outputVektor);
      
        
      
        
      for(String[] s : list){
            Double[] d = r.convertToDouble(s);
            inputVektor[0] = d[0];
            inputVektor[1] = d[1];
            inputVektor[2] = d[2];
            outputVektor[0] = d[3];
            outputVektor[1] = d[4];
            outputVektor[2] = d[5];
            outputVektor[3] = d[6];
            netz.setInputvektor(inputVektor);
            netz.setOutputvektor(outputVektor);
            netz.start();
            r.write(inputVektor, outputVektor, netz.getGewichte(),"gewichte",list.indexOf(s),".csv");
            netz.print();
      }
    


        
      

        
        
    }
}
