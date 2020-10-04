import au.com.bytecode.opencsv.CSVReader;

import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ParseCSVLineByLine {

    @SuppressWarnings("resource")
    public static String getContent(String fileName) throws Exception {
        //Build reader instance
        //Read MyCSV.csv
        //Default seperator is comma
        //Default quote character is double quote
        //Start reading from line number 2 (line numbers start from zero)
        CSVReader reader = new CSVReader(new FileReader(fileName), ',', '"', 1);
        //Read CSV line by line and use the string array as you want
        StringBuilder result = new StringBuilder();
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            //Verifying the read data here
            result.append(Arrays.toString(nextLine)).append("\n");
        }
        return result.toString();
    }

    public static int countVertex(String data) {
        Set<Integer> integers = new HashSet<>();
        int[] arr = fromStringToArrayInt(data);
        for (int i = 0; i < arr.length; i++) {
            if (i % 3 != 0) {
                integers.add(i);
            }
        }
        return integers.size();
    }

    public static int countEdge(String data) {
        String[] strings = data.split("\n");
        return strings.length;
    }


    public static int startVertexEdge(String data,int i) {
        String [] strings = fromStringToArrayString(data);
        int [] arr = fromStringToArrayInt(strings[i]);
        return arr[0];
    }

    public static int endVertexEdge(String data,int i) {
        String [] strings = fromStringToArrayString(data);
        int [] arr = fromStringToArrayInt(strings[i]);
        return arr[1];
    }

    public static int weightOfEdge(String data,int i) {
        String [] strings = fromStringToArrayString(data);
        int [] arr = fromStringToArrayInt(strings[i]);
        return arr[2];
    }

    public static int numberOfQueries(String data) {
        String[] strings = data.split("\n");
        return strings.length;
    }

    public static int sourceVertex(String data,int i) {
        String [] strings = fromStringToArrayString(data);
        int [] arr = fromStringToArrayInt(strings[i]);
        return arr[0];
    }

    public static int targetVertex(String data,int i) {
        String [] strings = fromStringToArrayString(data);
        int [] arr = fromStringToArrayInt(strings[i]);
        return arr[1];
    }

    private static int[] fromStringToArrayInt(String s) {
        String[] items = s.replaceAll("\\[", "").replaceAll("]", "").replaceAll("\\s", "").split(",");

        int[] results = new int[items.length];

        for (int i = 0; i < items.length; i++) {
            results[i] = Integer.parseInt(items[i]);
        }
        return results;
    }

    public static String[] fromStringToArrayString(String s) {
        return s.replaceAll("\\[", "")
                .replaceAll("]", "")
                .replaceAll(" ", "").split("\n");

    }

}
