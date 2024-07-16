package projekseminar;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Utils<T,E> {

    public static Boolean isVorhersageTrue(Double[] istVektor, Double[] sollVektor){

        return findHighestValueIndex(istVektor)==findHighestValueIndex(sollVektor);

    }

    public static int findHighestValueIndex(Double[] vektor){

        // returns index of highest element in an array

        Double highest = vektor[0];
        int index = 0;

        for(int i=0;i<vektor.length;i++){
            if(vektor[i] > highest){
                highest = vektor[i];
                index = i;
            }
        }

        
        return index;
    }

    public static void print(Double[] istVektor,Double[] sollVektor) {
    
            System.out.println("---------------------------------------------------------");
            System.out.printf("| %-25s | %-25s |%n", "Ist", "Soll");
            for (int i = 0, m = istVektor.length; i < m; i++) {

                System.out.printf("| %-25s | %-25s |%n", istVektor[i],sollVektor[i]);

            }
            System.out.println("---------------------------------------------------------");
            System.out.println();
        

    }


        public static <T> void saveToDisk(String path, T input){

            try{
                FileOutputStream fileOutputStream = new FileOutputStream(path);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
             
                objectOutputStream.writeObject(input);
    
                objectOutputStream.close();
                }catch(IOException e){
                  e.printStackTrace();
                }

        }
        public static<T> T readFromDisk(String path){
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
                @SuppressWarnings("unchecked")
                T restored = (T) objectInputStream.readObject();
                objectInputStream.close();
                return restored;
                
                }catch (Exception e){
                    e.printStackTrace();
                    }
                    return null;
        }
        @SuppressWarnings("unchecked")
        public static<T,E> T[] convertTo(E[] array) {
        T[] ar = (T[]) new Object[array.length];
        for(int i=0;i<array.length;i++){
            ar[i] = (T) array[i];
        }
        return ar;
    }


    public static <T> void printAsMatrix(T[][] input,String seperator){

        for(int i=0;i<input.length;i++){
            for(int j=0;j<input[0].length;j++){
                System.err.print(input[i][j]+seperator);
            }
            System.err.println();
        }
    }

  
    public static Double[] TwoDimToOneDim(Double[][] input){

        List<Double> list = new ArrayList<>();

        for(int i=0;i<input.length;i++){
            for(int j=0;j<input[0].length;j++){
                list.add(input[i][j]);
            }
        }
    
       Double[] ret = new Double[input.length*input[0].length];
       for(int i=0;i<list.size();i++){
        ret[i] = list.get(i);
       }

      
       
        return ret;

    }
    
}
