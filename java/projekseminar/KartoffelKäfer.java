package projekseminar;

import java.util.Random;


public class KartoffelKäfer {
    

    private Double[][] matrix;

    public KartoffelKäfer(int size){

        this.matrix = new Double[size][size];
        for(int i=0;i<this.matrix.length;i++){
            for(int j =0;j<this.matrix.length;j++){
                this.matrix[i][j] = 0.0;
            }
    }
}

    public  void createKäferMatrix(int einsen){
        int x;
        int y;
         
        for(int i=0;i<einsen;i++){
            while(true){
             x = RandomUtils.nextInt(new Random(),0,this.matrix[0].length);
             y = RandomUtils.nextInt(new Random(),0,this.matrix.length);
                if(this.matrix[x][y]==0.0){
                    this.matrix[x][y] = 1.0;
                    break;
        }
            }
        }
    }


    
    public Double[][] getMatrix(){
        return this.matrix;
    }

    
    public static void main(String[] args) {
        int[] käferInMatrix = {1,2,3,4,5,6,7,8,9,10};
        KartoffelKäfer kk = new KartoffelKäfer(25);
        kk.createKäferMatrix(2);
      

        Double[][] bild = (Double[][]) kk.getMatrix();
        Utils.printAsMatrix(bild," ");
    }

}
