package projekseminar.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import projekseminar.KartoffelKäfer;

public class tests {
    @Test
    public void test(){
        for(int i=0;i<100;i++){
        KartoffelKäfer kk = new KartoffelKäfer(25);
        kk.createKäferMatrix(i);
        assertEquals(tests.countKäfer(kk.getMatrix()),i);
        }
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

