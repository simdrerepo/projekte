package projekseminar;
import java.util.ArrayList;
import java.util.List;

public class Utils<T,E> {

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
