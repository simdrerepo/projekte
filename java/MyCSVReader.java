import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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


public List<List<String[]>>test(){

    List<List<String[]>> list3d  = new ArrayList<>();
    List<String[]> g_1 = new ArrayList<>();
    List<String[]> list = read(";");
    while(!list.isEmpty()){
        if(list.get(0).length==0){
            list.remove(0);
            List<String[]> tmp = new ArrayList<>(g_1);
            list3d.add(tmp);
            g_1.clear();
        }
        g_1.add(list.remove(0));
    }
        List<String[]> tmp = new ArrayList<>(g_1);
            list3d.add(tmp);
            g_1.clear();

            return list3d;

}

public Double[][][] convert(){
    List<List<String[]>> list3d = test();
    List<List<Double>> list = new ArrayList<>();
    List<List<List<Double>>> ret = new ArrayList<>();
    List<Double> tmp = null;
  
    for(List<String[]> l : list3d){
        for(int i=0;i<l.get(0).length;i++){
            tmp = new ArrayList<>();
            
            for(String[] s : l){  
                tmp.add(Double.parseDouble(s[i]));
            }
            list.add(tmp);
           
        }
        ret.add(list);
        list = new ArrayList<>();
       
        
    }
   
   
    return ret.stream().map(u1 -> u1.stream().map(u2 -> u2.toArray(new Double[0])).toArray(Double[][]::new)).toArray(Double[][][]::new);
}




}
