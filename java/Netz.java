import java.util.Arrays;

public class Netz {

    private Layer[] layer;
    private Double bias;

    private Double[][][] gewichte; 
    private Funktion aktivierungsFunktion;
 

    public Netz(int groesse){
        // Legt ein Netz mit der Größe n an
        this.layer = new Layer[groesse];
    }
    public Netz(int[] netzaufbau,Double[] inputVektor,Double[][][] gewichte,Funktion aktivierungsFunktion,Double bias){

        this.layer = new Layer[netzaufbau.length]; // Anzahl der Layer bestimmen
       this.aktivierungsFunktion=aktivierungsFunktion;
        this.bias=bias;
        this.gewichte=gewichte; // Gewichte setzen
        addLayer(netzaufbau[0], inputVektor,aktivierungsFunktion); // Input-Layer initialisieren
        for(int i=1,n=netzaufbau.length;i<n;i++){
            // restliche Layer initialisieren
            addLayer(netzaufbau[i],aktivierungsFunktion);
        }

    }

    public void resetInputvektor(Double[] inputVektor){
        for(int i=0,n=this.layer[0].getNeuronen().length;i<n;i++){
            layer[0].getNeuronen()[i].setWert(inputVektor[i]);
        }
    }

    public void setGewichte(Double[][][] gewichte){
        // Setzt die Gewichte für jeden Layer
        this.gewichte=gewichte;
    }
   
    private void addLayer(int LayerGroesse,Funktion aktivierungsFunktion){
        /*
         * Fügt einen Layer hinzu
         * LayerGroesse bestimmt dabei die Anzahl der Neuronen in einem Layer  
         */
        for(int i=0,n=this.layer.length;i<n;i++){
            if(layer[i]==null){
                layer[i] = new Layer(LayerGroesse,aktivierungsFunktion);
                return;
            }
        }
    }
    
    private void addLayer(int LayerGroesse,Double[] inputVektor,Funktion aktivierungsFunktion){
         /*
          * Diese Funktion wird verwendet um den ersten Layer (Input Layer) hinzuzufügen
          * Da im Input Layer keine Berechnung stattfindet wird der Input Vektor mit übergeben,
          * um die Neuronen mit den Entsprechende Werten zu versehen 
          */
        for(int i=0,n=layer.length;i<n;i++){
            if(layer[i]==null){
                layer[i] = new Layer(LayerGroesse,inputVektor,aktivierungsFunktion);
                return;
            }
        }
    } 

    public Neuron[] extractOutputVektor(){
        Layer l = this.layer[this.layer.length-1];
        Neuron[] outputVektor = new Neuron[l.getNeuronen().length];
        for(int i=0,n=l.getNeuronen().length;i<n;i++){
            outputVektor[i] = l.getNeuronen()[i];
        }

        return outputVektor;
    }

  
    
    public void compute(){
        /*
         * Startet die Berechnung des Neuronalen Netzes
         * 
         * Wir iterieren über jeden Layer (1ste Vorschleife)
         * Die Vorschleife startet bei Layer 1, weil wir die Berechnung erst bei Layer 1 beginnen und dabei auf die Werte
         * des i-1ten Layers (Input Layer) zurückgreifen
         * 
         * Für jeden Layer holen wir uns die Neuronen und iterieren über diese (2te Vorschleife)
         * Die 3te Vorschleife iteriert über den vorherigen Layer und Berechnet den Wert für das jeweilige Neuron
         * 
         */
       for(int i=1,n=layer.length;i<n;i++){
            for(int j=0,m=layer[i].getNeuronen().length;j<m;j++){
                Double wert = 0.0;
                for(int l=0,k=layer[i-1].getNeuronen().length;l<k;l++){
                    Neuron neuron = layer[i-1].getNeuronen()[l];         
                   wert = wert + neuron.getOutput() * this.gewichte[i-1][j][l];                           
            }      
            wert = wert + this.gewichte[i-1][j][this.gewichte[i-1][j].length-1]  * this.bias; // Bias addieren   
       
            layer[i].getNeuronen()[j].setInput(wert);     
            layer[i].getNeuronen()[j].setOutput(wert, this.aktivierungsFunktion);
            
            }
           
        }
    }

    public void print(){
        System.out.println("---------------------------------------------------------");
        for(int i=0,n=layer.length;i<n;i++){
            System.out.println("---------------------------Layer-"+(i+1)+"-----------------------");
            System.out.println("---------------------------------------------------------");
            System.out.printf("| %-25s | %-25s |%n", "Input", "Output");
           for(int j=0,m=layer[i].getNeuronen().length;j<m;j++){
           
            System.out.printf("| %-25s | %-25s |%n", layer[i].getNeuronen()[j].getInput().toString(), layer[i].getNeuronen()[j].getOutput().toString());
                //System.out.print("Input : "+layer[i].getNeuronen()[j].getInput().toString()+" Output : "+layer[i].getNeuronen()[j].getOutput().toString());
               // System.out.println();
           }
           System.out.println("---------------------------------------------------------");
        }
       
    }
    public void printDeltaWerte(){  
        for(int i=1,n=layer.length;i<n;i++){
            for(int j=0,m=layer[i].getNeuronen().length;j<m;j++){
                System.out.println(layer[i].getNeuronen()[j].getDeltawert().toString());
            }
            System.out.println("------------------------------------");
        }

    }

        public void backPropagade(Double[] sollVektor) {
            if(sollVektor.length!=this.layer[this.layer.length-1].getNeuronen().length){
               
                    return;

            }
            Funktion f = new Sigmoid_Ableitung();

                // Deltawerte für die Ouputschicht berechnen
                for(int i=0,n=layer[this.layer.length-1].getNeuronen().length;i<n;i++){
                    Neuron neuron = layer[this.layer.length-1].getNeuronen()[i];
                    neuron.setDeltawert(f.execute(neuron.getInput())*(sollVektor[i]-neuron.getOutput()));
                }
       
            for(int i=layer.length-2;i>0;i--){
                for(int j=0,n=layer[i].getNeuronen().length;j<n;j++){
                    Neuron neuron = layer[i].getNeuronen()[j];
                    Double deltawert = 0.0;
                        for(int k=0,l=layer[i+1].getNeuronen().length;k<l;k++){
                            Neuron neuron2 = layer[i+1].getNeuronen()[k];
                        
                            deltawert+=neuron2.getDeltawert()*gewichte[i][k][j+1];
                            
                            System.out.println("deltawert : "+neuron2.getDeltawert()+" * "+"gewicht : "+gewichte[i][k][j+1]+Arrays.toString(gewichte[i][k]) + " i = "+i+" j = "+j+" k = "+k);
                         }
                         System.out.println("dw = "+deltawert);

                        neuron.setDeltawert(f.execute(neuron.getInput())*deltawert);
                }
            }
        }

        public void updateGewichte(){
            Double[] deltawerte;
           
            for(int i=1,n=this.layer.length;i<n;i++){
              deltawerte = new Double[this.layer[i].getNeuronen().length];
              for(int j=0,m=this.layer[i].getNeuronen().length;j<m;j++){
                deltawerte[j] = this.layer[i].getNeuronen()[j].getDeltawert();
              
              }
              System.out.println(Arrays.toString(deltawerte));
              

            }
        

        }


 
    }






 

