package funktionen;

import java.io.Serializable;

public class Sigmoid implements Funktion,Serializable{

    @Override
    public double apply(double parameter) {
        return 1 / (1 + Math.exp(-parameter));
    }

 
    
}
