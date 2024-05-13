import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyCSVReader {
    

private String path;

public MyCSVReader (String pathString){
    this.path=pathString;
}

public List<String[]> read(String splitBy){

     List<String[]> list = new ArrayList<>();
     String line = ""; 
   
    try{  
        BufferedReader br = new BufferedReader(new FileReader(path));  
        line = br.readLine(); 
        while((line = br.readLine()) != null){  
        String[] array = line.split(splitBy); 
            list.add(array);
            } 
           br.close();
    }   
    catch (IOException e){  
    e.printStackTrace();  
    }  
        return list;

}









}
