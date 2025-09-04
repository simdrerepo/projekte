package funktionen;

import java.io.Serializable;

public class ToThePower implements Funktion,Serializable {

    int of;
    public ToThePower(int of){
        this.of = of;
    }

    @Override
    public double apply(double parameter) {
        return Math.pow(parameter,this.of);
    }
 
    
    
}
