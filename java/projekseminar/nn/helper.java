package nn;
import java.util.Arrays;
import java.util.List;

import Utils.RandomUtils;
import Utils.Helper;
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

    
    Netz nn = new Netz(2, new int[]{10,10}, 1);
 
   nn.train(traindata, targetdata, 10000);
    double[] pred = {0.0,1.0};
    System.out.println(Arrays.toString(nn.predict(pred)));
    serializer.save(nn, "./nn", "xor");
  

    Matrix true_label = new Matrix(3,1);
    true_label.data = new double[][]
    {
      {1.0},
      {0.0},
      {0.0}
    };
    Matrix predicted = new Matrix(3,1);
    predicted.data = new double[][]{
      {0.7},
      {0.2},
      {0.1}
    };

    true_label.multiply(predicted);
    double sum = Matrix.sum(true_label);
   nn.evaluate(traindata,targetdata);
  }
  
  


}
