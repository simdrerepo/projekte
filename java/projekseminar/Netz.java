package projekseminar;

import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import projekseminar.funktionen.Funktion;
import projekseminar.funktionen.Quadratischerfehler;
import projekseminar.funktionen.Sigmoid_Ableitung;

public class Netz {

    private Layer[] layer;
    private Double bias = 1.0;
    private Double fehler = Double.MAX_VALUE;
    private Double[][][] gewichte;
    private Funktion fehlerFunktion = new Quadratischerfehler();
    private Funktion ableitungsFunktion = new Sigmoid_Ableitung();



    private Double lernparameter = 0.00001; // Eta
    private Double[] Outputvektor;
    

    public Netz() {
   
        this.layer = new Layer[2];
    }
  


    public Double[][][] initializeWeights(Double min, Double max) {
        Double[][][] weights = initialize3dArray();
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                for (int k = 0; k < weights[i][j].length; k++) {
                    weights[i][j][k] = ThreadLocalRandom.current().nextDouble(min, max);
                }
            }
        }

        return weights;
    }

    private Double[][][] initialize3dArray() {
        Double[][][] weights = new Double[this.layer.length - 1][][];
        for (int i = 0, n = weights.length; i < n; i++) {
            weights[i] = new Double[this.layer[i + 1].getNeuronen().length][this.layer[i].getNeuronen().length + 1];
        }
        return weights;
    }

    private void extendArray(){
        Layer[] l = new Layer[this.layer.length+1];
        
        for(int i=0;i<this.layer.length;i++){
            l[i] = this.layer[i];
        }
        this.layer = l;
    }

    

    public void setInputvektor(Double[] inputVektor) {
        Layer l = new Layer(inputVektor.length);
        for (int i = 0, n = l.getNeuronen().length; i < n; i++) {
            l.getNeuronen()[i].setInput(inputVektor[i]);
            l.getNeuronen()[i].setOutput(inputVektor[i]);
        }
        this.layer[0] = l;
    }



    public void setGewichte(Double[][][] gewichte) {
        // Setzt die Gewichte für jeden Layer
        this.gewichte = gewichte;
    }

    public void setSize(int size){
        this.layer = new Layer[size];
    }
    public Layer[] getLayer(){
        return this.layer;
    }
    

    public void addHiddenLayer(int size) {
        /*
         * Fügt einen Layer hinzu
         * LayerGroesse bestimmt dabei die Anzahl der Neuronen in einem Layer
         */

         this.extendArray();
       
        for (int i = 1, n = this.layer.length; i < n; i++) {
            if (layer[i] == null) {
                layer[i] = new Layer(size);
                return;
            }
        }
    }




    public Double[] extractOutputVektor() {
        // Extrahiert den Outputvektor
        // Convertiert diesen zu einem Doublevektor
        Layer l = this.layer[this.layer.length - 1];
        Double[] outputVektor = new Double[l.getNeuronen().length];
        for (int i = 0, n = l.getNeuronen().length; i < n; i++) {
            outputVektor[i] = l.getNeuronen()[i].getOutput();
        }
        return outputVektor;
    }

    private void feedForward() {
        /*
         * Startet die Berechnung des Neuronalen Netzes
         * 
         * Wir iterieren über jeden Layer (1ste Vorschleife)
         * Die Vorschleife startet bei Layer 1, weil wir die Berechnung erst bei Layer 1
         * beginnen und dabei auf die Werte
         * des i-1ten Layers (Input Layer) zurückgreifen
         * 
         * Für jeden Layer holen wir uns die Neuronen und iterieren über diese (2te
         * Vorschleife)
         * Die 3te Vorschleife iteriert über den vorherigen Layer und Berechnet den Wert
         * für das jeweilige Neuron
         * 
         */
        for (int i = 1, n = layer.length; i < n; i++) {
            for (int j = 0, m = layer[i].getNeuronen().length; j < m; j++) { // Iteration über die Neuronen deren Werte
                                                                             // wir berechnen wollen
                Double wert = 0.0;
                for (int l = 0, k = layer[i - 1].getNeuronen().length; l < k; l++) { // Iteration über die Neuronen der
                                                                                     // Schicht davor
                    Neuron neuron = layer[i - 1].getNeuronen()[l]; // Neuron
                    wert = wert + neuron.getOutput() * this.gewichte[i - 1][j][l]; // Neuronoutput * zugehörigem Gewicht
                                                                                   // -> Aufaddieren
                }
                wert = wert + this.gewichte[i - 1][j][this.gewichte[i - 1][j].length - 1] * this.bias; // Bias + zu
                                                                                                       // gehörigem
                                                                                                       // Gewicht
                                                                                                       // addieren
                layer[i].getNeuronen()[j].setInput(wert);
                if (i == layer.length - 1) {layer[i].getNeuronen()[j].setOutput(wert);
                } else {layer[i].getNeuronen()[j].setOutput(wert, layer[i].getNeuronen()[j].getAktivierungsfunktion());}
            }

        }
    }

    public void print() {
        System.out.println("---------------------------------------------------------");
        for (int i = 0, n = layer.length; i < n; i++) {
            System.out.println("---------------------------Layer-" + (i + 1) + "-----------------------");
            System.out.println("---------------------------------------------------------");
            System.out.printf("| %-25s | %-25s |%n", "Input", "Output");
            for (int j = 0, m = layer[i].getNeuronen().length; j < m; j++) {

                System.out.printf("| %-25s | %-25s |%n", layer[i].getNeuronen()[j].getInput().toString(),
                        layer[i].getNeuronen()[j].getOutput().toString());
                // System.out.print("Input : "+layer[i].getNeuronen()[j].getInput().toString()+"
                // Output : "+layer[i].getNeuronen()[j].getOutput().toString());
                // System.out.println();
            }
            System.out.println("---------------------------------------------------------");
            System.out.println();
        }

    }



    public void setOutputvektor(Double[] Outputvektor) {
       this.Outputvektor=Outputvektor;
       Layer l = new Layer(Outputvektor.length);
       this.layer[this.layer.length-1] = l;
  
    }

    public void printDeltaWerte() {
        for (int i = 1, n = layer.length; i < n; i++) {
            for (int j = 0, m = layer[i].getNeuronen().length; j < m; j++) {
                System.out.println(layer[i].getNeuronen()[j].getDeltawert().toString());
            }
            System.out.println("------------------------------------");
        }

    }

    private void calcDeltawerte() {
        if (this.Outputvektor.length != this.layer[this.layer.length - 1].getNeuronen().length) {
            return;
        }
        // Deltawerte für die Ouputschicht berechnen
        for (int i = 0, n = layer[this.layer.length - 1].getNeuronen().length; i < n; i++) {
            Neuron neuron = layer[this.layer.length - 1].getNeuronen()[i];
            neuron.setDeltawert(ableitungsFunktion.execute(neuron.getInput()) * (this.Outputvektor[i] - neuron.getOutput()));
        }
        // Deltawerte für die Hiddenschicht berechnen
        for (int i = layer.length - 2; i > 0; i--) {
            for (int j = 0, n = layer[i].getNeuronen().length; j < n; j++) {// Iteration über die Schicht vor der
                                                                            // letzten Schicht
                Neuron neuron = layer[i].getNeuronen()[j];
                Double deltawert = 0.0;
                for (int k = 0, l = layer[i + 1].getNeuronen().length; k < l; k++) {
                    Neuron neuron2 = layer[i + 1].getNeuronen()[k];
                    deltawert += neuron2.getDeltawert() * gewichte[i][k][j];
                }
                neuron.setDeltawert(ableitungsFunktion.execute(neuron.getInput()) * deltawert);
            }
        }
    }

    private Double[][][] updateGewichte() {

        Double[][][] gewichte_neu = this.initialize3dArray();

        for (int i = 1, n = layer.length; i < n; i++) {
            for (int j = 0, m = layer[i].getNeuronen().length; j < m; j++) {
                Double wert = 0.0;
                Neuron post_neuron = layer[i].getNeuronen()[j];
                Double deltawert = layer[i].getNeuronen()[j].getDeltawert();
                for (int l = 0, k = layer[i - 1].getNeuronen().length; l < k; l++) {
                    wert = (this.lernparameter * (deltawert * post_neuron.getOutput())) + this.gewichte[i - 1][j][l];
                    gewichte_neu[i - 1][j][l] = wert;
                }
                gewichte_neu[i - 1][j][this.gewichte[i - 1][j].length- 1] = this.gewichte[i - 1][j][this.gewichte[i - 1][j].length - 1] + (this.lernparameter * (deltawert * this.bias));
            }
        }
        return gewichte_neu;
    }

    private Double calcFehler() {
        return this.fehlerFunktion.execute(extractOutputVektor(), this.Outputvektor);
    }

    private Double[][][] backPropagate() {
        this.calcDeltawerte();
        return this.updateGewichte();
    }

    public void start() {
        this.gewichte = this.initializeWeights(-0.04, 0.04);

        Double fehler_neu = 0.0;
        this.feedForward();
        fehler_neu = this.calcFehler();
        while (Math.abs(this.fehler - fehler_neu) > 0.000000000001) {
            Double[][][] current_weights = this.gewichte;
            Double[][][] updated_weights = this.backPropagate();
            this.setGewichte(updated_weights);
            this.feedForward();
            Stack<Double> prev_fehler_stack = new Stack<>();
            Stack<Double> new_fehler_stack = new Stack<>();
            prev_fehler_stack.push(this.fehler);
            new_fehler_stack.push(fehler_neu);
            this.fehler = fehler_neu;
            fehler_neu = this.calcFehler(); // neuen Fehler berechnen
            if (fehler_neu > this.fehler) { // wenn der neue Fehler größer ist als der alte
                this.lernparameter = this.lernparameter / 2.0; // den Lernparameter halbieren
                this.setGewichte(current_weights); // neuen Gewichte verwerfen und die alten gewichte wieder verwenden
               this.fehler = prev_fehler_stack.pop();
                fehler_neu = new_fehler_stack.pop();
            } else {
                this.lernparameter = this.lernparameter * 1.1; // wenn der neue fehler kleiner als der alte ist, den
                                                               // lernparameter erhöhen und weiter machen :-)
            }
        }
      
    }

    public void setLayer(Layer[] layer) {
        this.layer = layer;
    }



    public void setBias(Double bias) {
        this.bias = bias;
    }



    public void setFehler(Double fehler) {
        this.fehler = fehler;
    }



    public void setFehlerFunktion(Funktion fehlerFunktion) {
        this.fehlerFunktion = fehlerFunktion;
    }



    public void setAbleitungsFunktion(Funktion ableitungsFunktion) {
        this.ableitungsFunktion = ableitungsFunktion;
    }



    public void setLernparameter(Double lernparameter) {
        this.lernparameter = lernparameter;
    }



    public Double getBias() {
        return bias;
    }



    public Double getFehler() {
        return fehler;
    }



    public Funktion getFehlerFunktion() {
        return fehlerFunktion;
    }



    public Funktion getAbleitungsFunktion() {
        return ableitungsFunktion;
    }



    public Double getLernparameter() {
        return lernparameter;
    }

    public Double[][][] getGewichte() {
        return this.gewichte;
    }

    public Double[] getOutputvektor() {
        return this.Outputvektor;
    }

}
