public class Sättigung  implements Aktivierungsfunktion{

    @Override
    public Double funktion(Double parameter) {
        
        if(parameter >= 1.0){
            return 1.0;
        }
        if(parameter <= -1.0){
            return -1.0;
        }
        return parameter;
    }
    
}
