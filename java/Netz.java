
public class Netz {

    private Layer[] layer;
    private Double bias;
    private Double fehler = 1.0;
    private Double[][][] gewichte;
    private Funktion aktivierungsFunktion;
    private Funktion fehlerFunktion;
    private Funktion ableitungsFunktion;
    private Double lernparameter; // Eta
    private Double[] sollvektor;

    public Netz(int groesse) {
        // Legt ein Netz mit der Größe n an
        this.layer = new Layer[groesse];
    }

    public Netz(int[] netzaufbau, Double[] inputVektor, Double[][][] gewichte, Double[] sollVektor,
            Funktion aktivierungsFunktion, Funktion fehlerFunktion, Funktion ableitungsFunktion, Double bias,
            Double lernparameter) {

        this.layer = new Layer[netzaufbau.length]; // Anzahl der Layer bestimmen
        this.aktivierungsFunktion = aktivierungsFunktion;
        this.ableitungsFunktion = ableitungsFunktion;
        this.fehlerFunktion = fehlerFunktion;
        this.bias = bias;
        this.lernparameter = lernparameter;
        this.sollvektor = sollVektor;
        this.gewichte = gewichte; // Gewichte setzen
        addLayer(netzaufbau[0], inputVektor, aktivierungsFunktion); // Input-Layer initialisieren
        for (int i = 1, n = netzaufbau.length; i < n; i++) {
            // restliche Layer initialisieren
            addLayer(netzaufbau[i], aktivierungsFunktion);
        }

    }

    public void resetInputvektor(Double[] inputVektor) {
        for (int i = 0, n = this.layer[0].getNeuronen().length; i < n; i++) {
            layer[0].getNeuronen()[i].setWert(inputVektor[i]);
        }
    }

    public void setGewichte(Double[][][] gewichte) {
        // Setzt die Gewichte für jeden Layer
        this.gewichte = gewichte;
    }

    private void addLayer(int LayerGroesse, Funktion aktivierungsFunktion) {
        /*
         * Fügt einen Layer hinzu
         * LayerGroesse bestimmt dabei die Anzahl der Neuronen in einem Layer
         */
        this.aktivierungsFunktion = aktivierungsFunktion;
        for (int i = 0, n = this.layer.length; i < n; i++) {
            if (layer[i] == null) {
                layer[i] = new Layer(LayerGroesse);
                return;
            }
        }
    }

    private void addLayer(int LayerGroesse, Double[] inputVektor, Funktion aktivierungsFunktion) {
        /*
         * Diese Funktion wird verwendet um den ersten Layer (Input Layer) hinzuzufügen
         * Da im Input Layer keine Berechnung stattfindet wird der Input Vektor mit
         * übergeben,
         * um die Neuronen mit den Entsprechende Werten zu versehen
         */
        this.aktivierungsFunktion = aktivierungsFunktion;
        for (int i = 0, n = layer.length; i < n; i++) {
            if (layer[i] == null) {
                layer[i] = new Layer(LayerGroesse, inputVektor);
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

    public void feedForward() {
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
                layer[i].getNeuronen()[j].setInput(wert); // Berechneten Wert für das Neuron setzen
                if (i == layer.length - 1) {
                    // Der letzte Layer ist der Outputlayer
                    // Nach Convention ist hier der Input gleich dem Output
                    layer[i].getNeuronen()[j].setOutput(wert);
                } else {
                    //
                    layer[i].getNeuronen()[j].setOutput(wert, this.aktivierungsFunktion);
                }
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
        }

    }

    public Double[][][] getGewichte() {
        return this.gewichte;
    }

    public Double[] getSollvektor() {
        return this.sollvektor;
    }

    public void setSollvektor(Double[] sollvektor) {
        this.sollvektor = sollvektor;
    }

    public void printDeltaWerte() {
        for (int i = 1, n = layer.length; i < n; i++) {
            for (int j = 0, m = layer[i].getNeuronen().length; j < m; j++) {
                System.out.println(layer[i].getNeuronen()[j].getDeltawert().toString());
            }
            System.out.println("------------------------------------");
        }

    }

    public void calcDeltawerte() {
        if (this.sollvektor.length != this.layer[this.layer.length - 1].getNeuronen().length) {
            return;
        }
        // Deltawerte für die Ouputschicht berechnen
        for (int i = 0, n = layer[this.layer.length - 1].getNeuronen().length; i < n; i++) {
            Neuron neuron = layer[this.layer.length - 1].getNeuronen()[i];
            neuron.setDeltawert(
                    ableitungsFunktion.execute(neuron.getInput()) * (this.sollvektor[i] - neuron.getInput()));
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

    public Double[][][] updateGewichte() {

        Double[][][] gewichte_neu = { { { 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0 } },
                { { 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0 }, { 0.0, 0.0, 0.0, 0.0 } } };

        for (int i = 1, n = layer.length; i < n; i++) {
            for (int j = 0, m = layer[i].getNeuronen().length; j < m; j++) {
                Double wert = 0.0;
                Neuron post_neuron = layer[i].getNeuronen()[j];
                Double deltawert = layer[i].getNeuronen()[j].getDeltawert();
                for (int l = 0, k = layer[i - 1].getNeuronen().length; l < k; l++) {
                    wert = (this.lernparameter * (deltawert * post_neuron.getOutput())) + this.gewichte[i - 1][j][l];

                    gewichte_neu[i - 1][j][l] = wert;

                }

                gewichte_neu[i - 1][j][this.gewichte[i - 1][j].length
                        - 1] = this.gewichte[i - 1][j][this.gewichte[i - 1][j].length - 1]
                                + (this.lernparameter * (deltawert * this.bias));

            }

        }

        return gewichte_neu;
    }

    public Double calcFehler() {
        return this.fehlerFunktion.execute(extractOutputVektor(), this.sollvektor);
    }

    public Boolean isImprovement(Double[][][] updated_weights) {
        this.feedForward();
        return null;
    }

    public Double[][][] backPropagate() {
        this.calcDeltawerte();
        return this.updateGewichte();
    }

    public void start() {
        Double fehler_neu = 0.0;
        Double prev_fehler = 0.0;
        Double prev_fehler_neu = 0.0;
        this.feedForward();
        fehler_neu = this.calcFehler();

        while (Math.abs(this.fehler - fehler_neu) > 0.000000000001) {
            Double[][][] current_weights = this.gewichte;
            Double[][][] updated_weights = this.backPropagate();
            this.setGewichte(updated_weights);
            this.feedForward();
            prev_fehler = this.fehler; // fehler merken
            prev_fehler_neu = fehler_neu; // neuen fehler merken
            this.fehler = fehler_neu;
            fehler_neu = this.calcFehler(); // neuen Fehler berechnen
            if (fehler_neu > this.fehler) { // wenn der neue Fehler größer ist als der alte
                this.lernparameter = this.lernparameter / 2.0; // den Lernparameter halbieren
                this.setGewichte(current_weights); // neuen Gewichte verwerfen und die alten gewichte wieder verwenden
                this.fehler = prev_fehler; // alten fehler wieder herstellen
                fehler_neu = prev_fehler_neu; // alten neuen fehler wiederherstellen

            } else {
                this.lernparameter = this.lernparameter * 1.1; // wenn der neue fehler kleiner als der alte ist, den
                                                               // perparameter erhöhen und weiter machen :-)
            }
        }
    }

}
