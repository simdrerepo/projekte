package projekseminar;
import java.util.List;

public class main {

    

    public static void main(String[] args) {

        String path ="java/projekseminar/data/ampeldata.csv";
        Double[] inputVektor = {0.0,0.0,1.0}; // Input
        Double[] outputVektor = {0.0, 0.0, 0.0,1.0};
        int[] einsen = {1,2,3,4,5,6,7,8,9};
        int samplesize = 10;
        MyCSVReader r = new MyCSVReader(path);
        List<String[]> list = r.read(";");
     
        Netz netz = new Netz();
        netz.addHiddenLayer(3);
        netz.addHiddenLayer(5);
        netz.setInputvektor(inputVektor);
        netz.setOutputvektor(outputVektor);
        KartoffelKäfer kk;
        for(int i=0;i<einsen.length;i++){
          for(int j=0;j<samplesize;j++){
            kk = new KartoffelKäfer(25);
            kk.createKäferMatrix(i);
            Utils.TwoDimToOneDim(kk.getMatrix());
            
          }
        }
       
        
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
            r.write(inputVektor, outputVektor, netz.getGewichte(),"java/projekseminar/ergebnisse/ampel/gewichte",list.indexOf(s),".csv");
            netz.print();
      }

    }
}
