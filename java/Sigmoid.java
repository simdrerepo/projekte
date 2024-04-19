public class Sigmoid implements Funktion{

    @Override
    public Double execute(Double parameter) {
        return 1 / (1 + Math.exp(-parameter));
    }
    
}
