package nn;

import java.io.Serializable;

import Utils.RandomUtils;
import funktionen.Funktion;
import funktionen.Sigmoid;
import funktionen.Sigmoid_Ableitung;


public class Layer implements Serializable {

    public Matrix gewichte;
    public int input_nodes;
    public int nodes;
    public Matrix bias;

    public Layer(int input_nodes,int nodes){
        this.gewichte = new Matrix(nodes,input_nodes); // gewichte erzeugen
        this.bias = new Matrix(nodes,1); // bias erzeugen
        this.input_nodes = input_nodes;
        this.nodes = nodes;  
        this.gewichte.randomize();
        this.bias.randomize();
    }

    public Matrix predict(Matrix m) throws Exception{
        // Neuronen berechnen
        Matrix prediction = Matrix.multiply(this.gewichte, m);
        prediction.add(this.bias);
        prediction.map(new Sigmoid());
        return prediction;
    }


    public Matrix applyError(Matrix pred, Matrix prevPred,Matrix currentErrors,double lernparameter) throws Exception{

        Matrix gradienten = Matrix.map(pred,new Sigmoid_Ableitung());
        gradienten.multiply(currentErrors);
        gradienten.multiply(lernparameter);
        Matrix prevPredTransposed = Matrix.transpose(prevPred);
        Matrix deltas = Matrix.multiply(gradienten, prevPredTransposed);
        this.gewichte.add(deltas);
        this.bias.add(gradienten);

        Matrix gewichteTransposed = Matrix.transpose(this.gewichte);
        
        return Matrix.multiply(gewichteTransposed,currentErrors);
    }


  
}
