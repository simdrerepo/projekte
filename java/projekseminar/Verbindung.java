package projekseminar;

public class Verbindung {

    private Double gewicht;
    private Neuron links;
    private Neuron rechts;
    public Verbindung(Neuron links, Neuron rechts) {
        this.links = links;
        this.rechts = rechts;
    }
    public Double getGewicht() {
        return gewicht;
    }
    public void setGewicht(Double gewicht) {
        this.gewicht = gewicht;
    }
    public Neuron getLinks() {
        return links;
    }
    public void setLinks(Neuron links) {
        this.links = links;
    }
    public Neuron getRechts() {
        return rechts;
    }
    public void setRechts(Neuron rechts) {
        this.rechts = rechts;
    }

   
    
}
