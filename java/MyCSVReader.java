import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MyCSVReader {

    private String path;

    public MyCSVReader(String pathString) {
        this.path = pathString;
    }

    public List<String[]> read(String splitBy) {

        List<String[]> list = new ArrayList<>();
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            while ((line = br.readLine()) != null) {
                String[] array = line.split(splitBy);
                System.out.println(Arrays.toString(array));
                list.add(array);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }

    public void write(Double[] inputVektor, Double[] sollVektor, Double[][][] gewichte, String pfad,int index,String format) {
        String[] concat = Stream
                .concat(Arrays.stream(convertToString(inputVektor)), Arrays.stream(convertToString(sollVektor)))
                .toArray(String[]::new);
        String str = convertToString(concat, ";");
        List<String> data = new ArrayList<>();
        data.add(str);

        String pfadname = pfad+index+format;
        for (Double[][] d3 : gewichte) {
            data.add(";;");
            for (Double[] d2 : d3) {
                data.add(convertToString(convertToString(d2), ";"));
            }

        }

        try {
           
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(pfadname));
            for (String s : data) {
                bw.write(s);
                bw.write("\n");
            }

            bw.flush();
            bw.close();
        } catch (IOException e) {

        }

    }

    public String convertToString(String[] array, String seperator) {
        String str = "";
        for (int i = 0; i < array.length - 1; i++) {
            str += array[i] + seperator;
        }
        str += array[array.length - 1];
        return str;
    }

    public Double[] convertToDouble(String[] array) {
        return Arrays.stream(array).map(Double::valueOf).toArray(Double[]::new);
    }

    public String[] convertToString(Double[] array) {
        return Arrays.stream(array).map(String::valueOf).toArray(String[]::new);
    }

}
