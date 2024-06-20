package projekseminar;
import java.util.ArrayList;
import java.util.List;

public class main {

    

    public static void main(String[] args) {

        String path ="java/projekseminar/data/käferdata.csv";
        Double[] inputVektor;
        Double[] outputVektor;
        int[] einsen = {1,2,3,4,5,6,7,8,9};
        Double netzfehler = 0.0;
        MyCSVReader r = new MyCSVReader(path);
        List<String[]> npt = r.read(",");
        r.setPath("java/projekseminar/data/käferdata_output.csv");
        List<String[]> tpt = r.read(",");
        //main.generateData(einsen, 10, 25);  
        Netz netz = new Netz();
        netz.addHiddenLayer(10);
        
      
       
       
      
        
      for(String[] s : npt){
            Double[] input = r.convertToDouble(s);
            Double[] output = r.convertToDouble(tpt.get(npt.indexOf(s)));


            netz.setInputvektor(input);
            netz.setOutputvektor(output);
           netz.start();
           netz.print();
            r.write(input, output, netz.getGewichte(),"java/projekseminar/ergebnisse/käfer/gewichte",npt.indexOf(s),".csv",netz.getGesamtfehler());
            
            
            
            netzfehler+=netz.getGesamtfehler();
            
      }
      System.out.println(netzfehler);
     
    }

    public static void generateData(int[] einsen, int samplesize, int matrixbreite){
      MyCSVReader r = new MyCSVReader();
      List<String> batches = new ArrayList<>();
      List<String> output = new ArrayList<>();
      KartoffelKäfer kk;
       
      for(int i=0;i<einsen.length;i++){
        for(int j=0;j<samplesize;j++){
          kk = new KartoffelKäfer(matrixbreite);
          kk.createKäferMatrix(einsen[i]);
          Double[] ddd =  Utils.TwoDimToOneDim(kk.getMatrix());
          batches.add(r.convertToString(r.convertToString(ddd),",")); 
         
          
          Double[] out = main.createOutput(einsen.length+1, einsen[i]);
          output.add(r.convertToString(r.convertToString(out),","));

        }

      }
      
     for(int i=0;i<batches.size();i++){
      r.write(batches,"C:\\Users\\simon\\projekte\\java\\projekseminar\\data\\käferdata.csv");
     }
     for(int i=0;i<output.size();i++){
      r.write(output,"C:\\Users\\simon\\projekte\\java\\projekseminar\\data\\käferdata_output.csv");
     }
    }

    private static Double[] createOutput(int size, int index){
      Double[] ret = new Double[size];
    
      for(int i=0;i<size;i++){
        ret[i] = 0.0;
      }
      ret[index] = 1.0;
      return ret;
    }
}
