package projekseminar.funktionen;
public class Identität implements Funktion {

    @Override
    public Double execute(Double parameter) {
        return parameter;
    }

    @Override
    public Double execute(Double[] output, Double[] soll) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
