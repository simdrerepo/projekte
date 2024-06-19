

public class Utils<T> {


    public static <T> void printAsMatrix(T[][] input,String seperator){

        for(int i=0;i<input.length;i++){
            for(int j=0;j<input[0].length;j++){
                System.err.print(input[i][j]+seperator);
            }
            System.err.println();
        }
    }
    
}
