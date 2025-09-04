package Utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class serializer<T> {

    public static <T> void save(T toSerialize,String location,String filename) throws IOException{
    String locationAndFilename = location+"/"+filename+".ser";
    FileOutputStream file = new FileOutputStream(locationAndFilename);
    ObjectOutputStream out = new ObjectOutputStream(file);
    out.writeObject(toSerialize);
    out.close();
    file.close(); 
    }

    public static <T> T load(String location, String filename){
        throw new UnsupportedOperationException();
    }
}