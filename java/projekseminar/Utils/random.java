package Utils;

public class random {
 
    public static double getRandomNumber(int min,int max){
        return (double) ((Math.random() * (max-min))+min);
    }

}
