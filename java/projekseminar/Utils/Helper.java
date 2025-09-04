package Utils;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    
    public static List<double[][]> shuffle(double[][] ar1, double[][] ar2){
        // shuffles 2 2d-Arrays in the same order
        List<double[]> list1 = new ArrayList<>();
        List<double[]> list2 = new ArrayList<>();

        for (int i = 0; i < ar1.length; i++) {
            list1.add(ar1[i]);
            list2.add(ar2[i]);
        }

        double[][] new_Ar1 = new double[ar1.length][ar1[0].length];
        double[][] new_Ar2 = new double[ar2.length][ar2[0].length];
        int cnt = 0;
        while(!list1.isEmpty()){
            int randomInt = RandomUtils.getRandomInt(0, list1.size()-1);
            new_Ar1[cnt] = list1.get(randomInt);
            list1.remove(randomInt);
            new_Ar2[cnt] = list2.get(randomInt);
            list2.remove(randomInt);
            cnt++;

        }
     List<double[][]> ret = new ArrayList<>();
     ret.add(new_Ar1);
     ret.add(new_Ar2);
     return ret; 
    }

 public static List<double[][]> readMnistDataset(String dataFilePath, String labelFilepath){
    
    return null;
 }
}
