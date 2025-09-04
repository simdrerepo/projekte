package nn;
import java.util.Arrays;

import Utils.serializer;
class helper {

  public static void main(String[] args) throws Exception {

    double[][] traindata= 
    {
      {1.0,1.0},
      {1.0,0.0},
      {0.0,1.0},
      {0.0,0.0}
    };
    double[][] targetdata=
    {
      {0.0},
      {1.0},
      {1.0},
      {0.0}
    };

    
    Netz nn = new Netz(2, new int[]{2}, 1);
 
   nn.train(traindata, targetdata, 10000);
    double[] pred = {0.0,1.0};
    System.out.println(Arrays.toString(nn.predict(pred)));
    serializer.save(nn, "./nn", "xor");
  
  }
  
  


}
