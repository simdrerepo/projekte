

public class Netz {

    private Layer[] layer;

    private Double[][][] gewichte; 

    public Netz(int groesse){
        this.layer = new Layer[groesse];
    }

    public void setGewichte(Double[][][] gewichte){
        this.gewichte=gewichte;
    }
   
    public void addLayer(int LayerGroesse){
      
        for(int i=0,n=this.layer.length;i<n;i++){
            if(layer[i]==null){
                layer[i] = new Layer(LayerGroesse);
                return;
            }
        }
    }
    
    public void addLayer(int LayerGroesse,Double[] inputVektor){
        
        for(int i=0,n=layer.length;i<n;i++){
            if(layer[i]==null){
                layer[i] = new Layer(LayerGroesse,inputVektor);
                return;
            }
        }
    }
    
    public void compute(){
       for(int i=1,n=layer.length;i<n;i++){
            for(int j=0,m=layer[i].getNeuronen().length;j<m;j++){
                Double wert = 0.0;
                for(int l=0,k=layer[i-1].getNeuronen().length;l<k;l++){
                   wert = wert + layer[i-1].getNeuronen()[l].getWert() * this.gewichte[i-1][j][l];    
            }
            layer[i].getNeuronen()[j].setWert(Aktivierungsfunktion.sigmoid(wert));
            }
        }
    }

    public void print(){
        for(Layer l : layer){
            l.printNeuronen();
        }
    }


  

 

}