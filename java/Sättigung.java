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
    
}
