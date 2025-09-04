package funktionen;

public class Log implements Funktion {
    // Logarithmus naturalis
    @Override
    public double apply(double parameter) {

        return Math.log(parameter);
    }
    
}
