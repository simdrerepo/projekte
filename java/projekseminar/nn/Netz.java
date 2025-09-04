package nn;

import java.io.Serializable;

import funktionen.Funktion;
import funktionen.Sigmoid;
import funktionen.Sigmoid_Ableitung;


public class Netz implements Serializable{
    private int input_nodes;
    private int[] hidden_nodes;
    private int output_nodes;
    private Double bias = 1.0;
    private Funktion ableitungsFunktion;
    private Funktion aktivierungsFunktion;
    private Double lernparameter = 0.00001; // Eta
    private Layer[] layers; 
    

    

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
        layers[layers.length-1] = new Layer(hidden_nodes[hidden_nodes.length-1],output_nodes);
        
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

    public void feedForward(Matrix[] predictions,Matrix inputs) throws Exception{
        Matrix prediction = inputs;
         // Vorwärtspropagation
        for (int i = 0; i < this.layers.length; i++) {
            prediction = layers[i].predict(prediction);
            predictions[i] = prediction;
        }
        
    }

    public void backPropagade(Matrix[] predictions, Matrix currentErrors, Matrix inputs) throws Exception{
                // Backpropagation
       for(int i=layers.length-1;i>=0;i--){
        if(0==i){
            currentErrors = layers[i].applyError(predictions[i], inputs, currentErrors);
        }
        else{
            currentErrors = layers[i].applyError(predictions[i], predictions[i-1], currentErrors);
        }
       }
    }

    public void train(double[][] data,double[][] target,int iterations) throws Exception{
        for(int i=0; i<iterations; i++){
            for (int j = 0; j < data.length; j++) {
                Matrix inputs = Matrix.fromArray(data[j]);
                Matrix targets = Matrix.fromArray(target[j]);
                Matrix[] predictions = new Matrix[layers.length];
                feedForward(predictions,inputs);
                Matrix outputs = predictions[predictions.length-1];
                // Fehler berechnen
                Matrix currentErrors = Matrix.subtract(targets,outputs);
                backPropagade(predictions,currentErrors, inputs);
            }
        }
    }

    public void train(double[] input_array,double[] output_array) throws Exception{
        Matrix inputs = Matrix.fromArray(input_array);
        Matrix targets = Matrix.fromArray(output_array);
        Matrix[] predictions = new Matrix[layers.length];
        Matrix prediction = inputs;
        
        // Vorwärtspropagation
        for (int i = 0; i < this.layers.length; i++) {
            prediction = layers[i].predict(prediction);
            predictions[i] = prediction;
        }
       
        // Fehler der Ausgabeschicht
        Matrix outputs = predictions[predictions.length-1];
        Matrix currentErrors = Matrix.subtract(targets,outputs);
        
        // Backpropagation
       for(int i=layers.length-1;i>=0;i--){
        if(0==i){
            currentErrors = layers[i].applyError(predictions[i], inputs, currentErrors);
        }
        else{
            currentErrors = layers[i].applyError(predictions[i], predictions[i-1], currentErrors);
        }
       }
        
    }

  

   

   

    

   



   

}
