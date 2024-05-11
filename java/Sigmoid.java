public class Sigmoid implements Funktion{

    @Override
    public Double execute(Double parameter) {
        return 1 / (1 + Math.exp(-parameter));
    }

    @Override
    public Double execute(Double[] output, Double[] soll) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
