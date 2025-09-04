package Utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
 
    public static double getRandomNumber(int min,int max){
        return (double) ((Math.random() * (max-min))+min);
    }

    public static int getRandomInt(int min,int max){
        // max included
        if(min > max){
            throw new IllegalArgumentException();

        }
        
        return ThreadLocalRandom.current().nextInt(min,max+1);
    }

}
