import java.io.*;
import java.util.List;

public class WriterInCSV {
    public static void writer(List<String> thingsToWrite, String separator, String fileName){

        try (PrintWriter writer = new PrintWriter(new File(fileName))) {

                writer.write(thingsToWrite.toString().replaceAll("\\[", "").replaceAll("]", "").replaceAll(",", "").replaceAll("  ","false\n"));
            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
