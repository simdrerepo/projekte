package funktionen;

import java.io.Serializable;

public class Sigmoid_Ableitung implements Funktion, Serializable{

    public double apply(double parameter) {
        
        return parameter * (1-parameter);
    }


    
    
}
