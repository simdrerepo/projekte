package projekseminar;

import java.io.Serializable;
import java.util.Arrays;

public class Data implements Serializable {
    
    Double[] inputvektor;
    Double[] outputvektor;

    public Double[] getInputvektor() {
        return inputvektor;
    }
    public void setInputvektor(Double[] inputvektor) {
        this.inputvektor = inputvektor;
    }
    public Double[] getOutputvektor() {
        return outputvektor;
    }
    public void setOutputvektor(Double[] outputvektor) {
        this.outputvektor = outputvektor;
    }
    @Override
    public String toString() {
        return "Data [inputvektor=" + Arrays.toString(inputvektor) + ", outputvektor=" + Arrays.toString(outputvektor)
                + "]";
    } 
}
