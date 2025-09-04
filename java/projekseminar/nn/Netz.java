package nn;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Utils.Helper;
import funktionen.Funktion;
import funktionen.Sigmoid;
import funktionen.Sigmoid_Ableitung;
import funktionen.ToThePower;


public class Netz implements Serializable{
    private int input_nodes;
    private int[] hidden_nodes;
    private int output_nodes;
    private Funktion ableitungsFunktion;
    private Funktion aktivierungsFunktion;
    private double lernparameter = 0.1; // Eta
    private Layer[] layers; 
    public List<double[]> errors;
    

    

    public Netz(int input_nodes,int[] hidden_nodes,int output_nodes) {
        this.input_nodes=input_nodes;
        this.hidden_nodes=hidden_nodes;
        this.output_nodes=output_nodes;
        this.aktivierungsFunktion = new Sigmoid();
        this.ableitungsFunktion = new Sigmoid_Ableitung();
        // Layers erzeugen
        layers = new Layer[hidden_nodes.length+1];
        // Erster Layer
        layers[0] = new Layer(input_nodes,hidden_nodes[0]);

        for (int i = 1;i < hidden_nodes.length; i++) {
            layers[i] = new Layer(hidden_nodes[i-1],hidden_nodes[i]);
        }
        // Letzter Layer
        layers[layers.length-1] = new Layer(hidden_nodes[hidden_nodes.length-1],output_nodes);
        // Errors jeder Epoche tracken
        this.errors = new ArrayList<>();
        
    }
    
  
  

    public double[] predict(double[] input_array) throws Exception{
        Matrix prediction = Matrix.fromArray(input_array);
        for (int i = 0; i < this.layers.length; i++) {
            prediction = this.layers[i].predict(prediction);
            
        }

        return prediction.toArray();
    }

    public Matrix calcError(Matrix targets,Matrix ouputs){

        return Matrix.subtract(targets, ouputs);
    }

    public Matrix[] feedForward(Matrix inputs) throws Exception{
        Matrix[] predictions = new Matrix[this.layers.length];
        Matrix prediction = inputs;
         // Vorwärtspropagation
        for (int i = 0; i < this.layers.length; i++) {
            prediction = layers[i].predict(prediction);
            predictions[i] = prediction;
        }
        return predictions;
    }

    public void backPropagade(Matrix[] predictions, Matrix currentErrors, Matrix inputs) throws Exception{
                // Backpropagation
       for(int i=layers.length-1;i>=0;i--){
        if(0==i){
            currentErrors = layers[i].applyError(predictions[i], inputs, currentErrors,this.lernparameter);
        }
        else{
            currentErrors = layers[i].applyError(predictions[i], predictions[i-1], currentErrors,this.lernparameter);
        }
       }
    }

    public void train(double[][] data,double[][] target,int epochen) throws Exception{
        for(int i=0; i<epochen; i++){
            // Reihenfolge durchmischen
            List<double[][]> shuffled = Helper.shuffle(data, target);
            data = shuffled.get(0);
            target = shuffled.get(1);
            double[] errors_of_epoche = new double[data.length];
            // Epoche
            for (int j = 0; j < data.length; j++) {
                Matrix inputs = Matrix.fromArray(data[j]);
                Matrix targets = Matrix.fromArray(target[j]);
                Matrix[] predictions = feedForward(inputs);
                Matrix outputs = predictions[predictions.length-1];
                // Fehler berechnen
                Matrix currentErrors = calcError(targets, outputs);
                // quantify error for every batch
                Matrix m = Matrix.map(currentErrors,new ToThePower(2));
                double sum = Matrix.sum(m);
                errors_of_epoche[j] = sum;
                // backpropagade
                backPropagade(predictions,currentErrors, inputs);
            }
            // quantify error per epoche
            double sum = 0.0;
            for (int j = 0; j < errors_of_epoche.length ; j++) {
                sum+=errors_of_epoche[j];
            }
            System.out.println(sum/errors_of_epoche.length);          
        }
    }
   

    public void evaluate(double[][] data,double[][] targets) throws Exception{
        double[][] results = new double[data.length][data[0].length];
        for (int i = 0; i < data.length; i++) {
            results[i] = predict(data[i]);
        }
        System.out.printf("%-10s %-10s %-10s%n","data","target","trained");
        for (int i = 0; i < results.length; i++) {
            String result_str = "";
            String targets_str = "";
            String data_str = "";
            for (int j = 0; j < results[0].length; j++) {
                result_str+=results[i][j]+" ";
                targets_str+=targets[i][j]+" ";
        
            }
            for (int j = 0; j < data[0].length; j++) {
                    data_str+=data[i][j]+" ";
            }
            System.out.printf("%-10s %-10s %-10s%n",data_str,targets_str,result_str);
    }
}

   


   

}
