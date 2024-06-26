package projekseminar.funktionen;

import projekseminar.Funktion;

public class Sättigung  implements Funktion{

    @Override
    public Double execute(Double parameter) {
        
        if(parameter >= 1.0){
            return 1.0;
        }
        if(parameter <= -1.0){
            return -1.0;
        }
        return parameter;
    }

    @Override
    public Double execute(Double[] output, Double[] soll) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
