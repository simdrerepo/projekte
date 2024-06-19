import java.lang.Math;


public class KartoffelKäfer {
    

    private Double[][] matrix;

    public KartoffelKäfer(int size){

        this.matrix = new Double[size][size];
    }

    private void createKäfer(){
        
        for(int i=0;i<this.matrix.length;i++){
            for (int j = 0; j < matrix[0].length; j++) {
            this.matrix[i][j] =  (double) Math.round(Math.random());
            }
        }
    }
    
    public Double[][] getMatrix(){
        return this.matrix;
    }
    public static void main(String[] args) {
        KartoffelKäfer kk = new KartoffelKäfer(5);
        kk.createKäfer();

        Double[][] bild = (Double[][]) kk.getMatrix();
        Utils.printAsMatrix(bild," ");
    }

}
