

public class Netz {

    private Layer[] layer;

    private Double[][][] gewichte; 

    public Netz(int groesse){
        // Legt ein Netz mit der Größe n an
        this.layer = new Layer[groesse];
    }

    public void setGewichte(Double[][][] gewichte){
        // Setzt die Gewichte für jeden Layer
        this.gewichte=gewichte;
    }
   
    public void addLayer(int LayerGroesse){
        /*
         * Fügt einen Layer hinzu
         * LayerGroesse bestimmt dabei die Anzahl der Neuronen in einem Layer  
         */
        for(int i=0,n=this.layer.length;i<n;i++){
            if(layer[i]==null){
                layer[i] = new Layer(LayerGroesse);
                return;
            }
        }
    }
    
    public void addLayer(int LayerGroesse,Double[] inputVektor){
         /*
          * Diese Funktion wird verwendet um den ersten Layer (Input Layer) hinzuzufügen
          * Da im Input Layer keine Berechnung stattfindet wird der Input Vektor mit übergeben,
          * um die Neuronen mit den Entsprechende Werten zu versehen 
          */
        for(int i=0,n=layer.length;i<n;i++){
            if(layer[i]==null){
                layer[i] = new Layer(LayerGroesse,inputVektor);
                return;
            }
        }
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
                   wert = wert + layer[i-1].getNeuronen()[l].getWert() * this.gewichte[i-1][j][l];    
            }
            layer[i].getNeuronen()[j].setWert(wert);
            }
        }
    }

    public void print(){
        for(Layer l : layer){
            l.printNeuronen();
            System.out.println("---------------");
        }
    }


  

 

}