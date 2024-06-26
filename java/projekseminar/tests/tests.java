package projekseminar.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import projekseminar.KartoffelKäfer;
import projekseminar.Utils;


public class tests {
    @Test
    public void test(){
        int matrixbreite = 25;
        int length = matrixbreite*matrixbreite;

        for(int i=0;i<length;i++){
            KartoffelKäfer kk = new KartoffelKäfer(matrixbreite);
            kk.createKäferMatrix(i);
            assertEquals(tests.countKäfer(kk.getMatrix()),i);
            assertEquals(tests.countKäfer(Utils.TwoDimToOneDim(kk.getMatrix())),i);
        }
      

    }

    public static int countKäfer(Double[] input){
        int cnt=0;
        for(Double d : input){
            if(d==1.0){
                cnt++;
            }
    }
    return cnt;
}

    public static int countKäfer(Double[][] input){
        int cnt=0;
        for(Double[] dar : input){
            for(Double d : dar){
                if(d==1.0){
                    cnt++;
                }
            }
        }
        return cnt;
    }

}

