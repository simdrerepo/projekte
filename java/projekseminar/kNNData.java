package projekseminar;


import java.util.Arrays;
import java.util.List;

public class kNNData extends Data {
    
    Double[][][] gewichte;
    Double[] ergebnisvektor;
    List<Double> fehlerverlauf;
    Double fehler;

    public kNNData(){

    }
    public Double[][][] getGewichte() {
        return gewichte;
    }
    public void setGewichte(Double[][][] gewichte) {
        this.gewichte = gewichte;
    }

    public Double getFehler() {
        return fehler;
    }
    public void setFehler(Double fehler) {
        this.fehler = fehler;
    }
    
    @Override
    public String toString() {
        return "MyDataStructure [gewichte=" + Arrays.deepToString(gewichte) + ", inputvektor="
                + Arrays.toString(inputvektor) + ", outputvektor=" + Arrays.toString(outputvektor) + ", fehler="
                + fehler + ", ergebnisvektor="+ergebnisvektor+"]";
    }
    public Double[] getErgebnisvektor() {
        return ergebnisvektor;
    }
    public void setErgebnisvektor(Double[] ergebnisvektor) {
        this.ergebnisvektor = ergebnisvektor;
    }
    public List<Double> getFehlerverlauf() {
        return fehlerverlauf;
    }
    public void setFehlerverlauf(List<Double> fehlerverlauf) {
        this.fehlerverlauf = fehlerverlauf;
    }
    

}
