public class Tangenshyperbolicus implements Aktivierungsfunktion{

    @Override
    public Double funktion(Double parameter) {
        return Math.tanh(parameter);
    }
    
}
