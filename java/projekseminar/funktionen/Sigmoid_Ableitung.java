package projekseminar.funktionen;
public class Sigmoid_Ableitung extends Sigmoid {

    public Double execute(Double parameter) {
        
        return super.execute(parameter)*(1-super.execute(parameter));
    }


    
    
}
