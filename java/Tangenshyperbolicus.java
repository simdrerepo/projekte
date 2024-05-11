public class Tangenshyperbolicus implements Funktion{

    @Override
    public Double execute(Double parameter) {
        return Math.tanh(parameter);
    }

    @Override
    public Double execute(Double[] output, Double[] soll) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
