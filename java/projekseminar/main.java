package projekseminar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class main {

    

    public static void main(String[] args) {

        
        List<kNNData> netzoutput = new ArrayList<>();
        int[] einsen = {1,2,3,4,5,6,7,8,9};
        List<Double> fehler = new ArrayList<>();
      
     
        Netz netz = new Netz();
        netz.addHiddenLayer(10);
        

        //List<Data> trainingsdaten = main.generateData(einsen, 10, 25);
       // List<Data> testdaten = main.generateData(einsen, 10, 25);
       // Utils.saveToDisk("java\\projekseminar\\serialized_objects\\training\\trainingsdaten", trainingsdaten);
       // Utils.saveToDisk("java\\projekseminar\\serialized_objects\\test\\testdaten", testdaten); 
         List<Data> trainingsdaten = Utils.readFromDisk("java\\projekseminar\\serialized_objects\\training\\trainingsdaten");
        
      List<Data> testdaten = Utils.readFromDisk("java\\projekseminar\\serialized_objects\\test\\testdaten");

        
      // List<kNNData> output = main.calc(trainingsdaten,netz);
      // Utils.saveToDisk("java\\projekseminar\\serialized_objects\\netzoutput", output);
       List<kNNData> output = Utils.readFromDisk( "java\\projekseminar\\serialized_objects\\netzoutput");
       Funktion fehlerFunktion = new Quadratischerfehler();
       System.out.println(output.size());
       Double[] opv = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
       List<Double> fehler_testdaten = new ArrayList<>();
       List<Double[]> fehler_trainingsdaten = new ArrayList<>();
       //List<Double[]> ergebnisse = new ArrayList<>();
        for(int i=0;i<output.size();i++){
          netz.setGewichte(output.get(i).getGewichte());
          netz.setInputvektor(testdaten.get(i).getInputvektor());
          netz.setSollvektor(opv);
          netz.feedForward();
          fehler_trainingsdaten.add(netz.extractOutputVektor());
         System.out.println(Arrays.toString(output.get(i).getErgebnisvektor()));

        }
        System.err.println("####################################################################");
        for(Double[] da : fehler_trainingsdaten){
          System.out.println(Arrays.toString(da));
        }

       
       
  

      
       // Utils.saveToDisk("java\\projekseminar\\serialized_objects\\netzoutput", netzoutput);
       //netzoutput = Utils.readFromDisk("java\\projekseminar\\serialized_objects\\netzoutput");
       

     /*   for(MyDataStructure mds :netzoutput){

        netz.setGewichte(mds.getGewichte());
        netz.setInputvektor(input_data_validation.get(netzoutput.indexOf(mds)));
        
        netz.feedForward();
        
       System.out.println(Arrays.toString(netz.extractOutputVektor()));
       

       } */
        
   
       
    }
    public static Double calcNetzFehler(List<kNNData> daten){

      List<Double> fehler = new ArrayList<>();
      for(kNNData d :daten){
        fehler.add(d.getFehler());
      }

      return fehler.stream().reduce(0.0,(a,b)->a+b)/fehler.size();

    }

    public static List<kNNData> calc(List<Data> daten,Netz netz){
  
      List<kNNData> netzoutput = new ArrayList<>();
      for(Data d : daten){

          netz.setInputvektor(d.getInputvektor());
          netz.setSollvektor(d.getOutputvektor());
          netz.start();
         
        
          kNNData mds = new kNNData();
          mds.setGewichte(netz.getGewichte());
          mds.setInputvektor(d.getInputvektor());
          mds.setOutputvektor(d.getOutputvektor());
          mds.setFehlerverlauf(netz.getFehlerverlauf());
          mds.setErgebnisvektor(netz.extractOutputVektor());
          mds.setFehler(netz.getGesamtfehler());
          
          netzoutput.add(mds); 
     }

     return netzoutput;

  }

    public static List<Data> generateData(int[] einsen, int samplesize, int matrixbreite){
      List<Data> daten = new ArrayList<>();
      KartoffelKäfer kk;
      for(int i=0;i<einsen.length;i++){
        for(int j=0;j<samplesize;j++){
          kk = new KartoffelKäfer(matrixbreite);
          kk.createKäferMatrix(einsen[i]);
          Double[] ddd =  Utils.TwoDimToOneDim(kk.getMatrix());
          Double[] out = main.createOutput(einsen.length+1, einsen[i]);
          Data d = new Data();
          d.setInputvektor(ddd);
          d.setOutputvektor(out);
          daten.add(d);
        }
       
      }
     return daten;
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
