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

    public static <T> T[] TwoDimToOneDim(T[][] input){

        List<T> list = new ArrayList<>();
        for(T[] ta :input){
            for(T t : ta){
                list.add(t);
            }
        }
        @SuppressWarnings("unchecked")
        T[] ret = (T[]) new Object[list.size()];
        return list.toArray(ret);

    }
    
}
