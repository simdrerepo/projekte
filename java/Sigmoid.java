public class Sigmoid implements Aktivierungsfunktion{

    @Override
    public Double funktion(Double parameter) {
        return 1 / (1 + Math.exp(-parameter));
    }
    
}
