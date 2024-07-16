package projekseminar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class main {

    

    public static void main(String[] args) {

        
        List<kNNData> netzoutput = new ArrayList<>();
        int[] einsen = {1,2,3,4,5,6,7,8,9};
        List<Double> fehler = new ArrayList<>();
/*         Double[][][] gew = {
          {
            {-0.081,0.06,-0.01,0.08},
            {0.08,0.02,0.003,-0.09},
            {-0.04,-0.003,-0.09,-0.05}
          },
          {
            {-0.008,0.06,0.04,-0.08
            },
            {0.01,-0.06,0.06,0.06
            },
            {0.01,-0.027,0.08,0.09
            },
            {0.00029,-0.01,0.08,-0.001
            }
          }
         
          
           }; */

           Double[][][] gewichte = {
            {
              {0.15,0.20,0.35},
              {0.25,0.30,0.35}
            },
            {
              {0.4,0.45,0.6},
              {0.5,0.55,0.6}
            }
           };
        
        MyCSVReader r = new MyCSVReader();
        r.setPath("java\\projekseminar\\data\\training\\ampeldata.csv");
        List<Double[]> ampel_input = r.read(";");
      
        System.out.println();
          r.setPath("java\\projekseminar\\data\\training\\ampeldata_output.csv");
        List<Double[]> ampel_output = r.read(";");
       
        Double[] input = {3.0,1.0};
        Double[] output = {1.0,0.0};
      
        Netz netz = new Netz(3);
        netz.addLayer(625);
        netz.addLayer(10);
        netz.addLayer(10);
  
 

        
        List<Data> trainingsdaten = main.generateData(einsen, 10, 25);
       List<Data> testdaten = main.generateData(einsen, 10, 25);
       /* 
       Utils.saveToDisk("java\\projekseminar\\serialized_objects\\training\\trainingsdaten", trainingsdaten);
       Utils.saveToDisk("java\\projekseminar\\serialized_objects\\test\\testdaten", testdaten);  */
       trainingsdaten = Utils.readFromDisk("java\\projekseminar\\serialized_objects\\training\\trainingsdaten");
        
       testdaten = Utils.readFromDisk("java\\projekseminar\\serialized_objects\\test\\testdaten");
 
      
       List<kNNData> netz_output = main.calc(trainingsdaten,netz);
       Utils.saveToDisk("java\\projekseminar\\serialized_objects\\netzoutput", netz_output);
        netz_output = Utils.readFromDisk( "java\\projekseminar\\serialized_objects\\netzoutput");
       Funktion fehlerFunktion = new Quadratischerfehler();
    
       Double[] opv = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 ,0.0, 0.0, 0.0};
       List<Double> fehler_testdaten = new ArrayList<>();
       List<Double> fehler_trainingsdaten = new ArrayList<>();
       //List<Double[]> ergebnisse = new ArrayList<>();

    
      int korrektevorhersagen = 0;
        for(int i=0;i<netz_output.size();i++){
          netz.setGewichte(netz_output.get(i).getGewichte());
          netz.setInputvektor(testdaten.get(i).getInputvektor());
          netz.setSollvektor(opv);
          netz.feedForward();
     
        
        
         fehler_testdaten.add(fehlerFunktion.execute(netz.extractOutputVektor(), testdaten.get(i).getOutputvektor()));
         Utils.print(netz.extractOutputVektor(),testdaten.get(i).getOutputvektor());
         if(Utils.isVorhersageTrue(netz.extractOutputVektor(), testdaten.get(i).getOutputvektor())==true){
          korrektevorhersagen++;
         }
        }
       
       
      Double d =  fehler_testdaten.stream().reduce(0.0,(a,b)->a+b);
     
    
      System.out.println(d); 
      System.out.println(korrektevorhersagen);
       // Utils.saveToDisk("java\\projekseminar\\serialized_objects\\netzoutput", netzoutput);
       //netzoutput = Utils.readFromDisk("java\\projekseminar\\serialized_objects\\netzoutput");
       List<List<Double>> liste_aller_fehlerverläufe = new ArrayList<>();
       List<Double> fehlerverlauf_gesamtes_netz = new ArrayList<>();
       Double trainings_fehler = 0.0;
       for(kNNData de :netz_output){
       liste_aller_fehlerverläufe.add(de.getFehlerverlauf());
        trainings_fehler += de.getFehlerverlauf().getLast();
       }

       for(int i =0;i<liste_aller_fehlerverläufe.get(0).size();i++){
        Double f = 0.0;
        for(int j=0;j<liste_aller_fehlerverläufe.size();j++){
          f = f+liste_aller_fehlerverläufe.get(j).get(i);
          
          
        }
        fehlerverlauf_gesamtes_netz.add((f/90));
    
       }
        
       System.out.println(trainings_fehler);
       System.out.println(fehlerverlauf_gesamtes_netz);
       
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
          Double[] out = main.createOutput(einsen, einsen[i]);
          Data d = new Data();
          d.setInputvektor(ddd);
          d.setOutputvektor(out);
          daten.add(d);
        }
       
      }
     return daten;
    }

    private static Double[] createOutput(int[] array, int index){ 
     
      int max = array[0];

      for(int i=0;i<array.length;i++){
        if(array[i]> max){
          max = array[i];
        }
      }
      Double[] ret = new Double[max+1];
      ret[index] = 1.0;
      return ret;
    }
}
