package Util;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    /**
     * Function to parse the lines of the input csv file.
     *
     * @param path
     *             Type: String
     *             Location of the file on the machine
     */
    public static List<String> readFile(String path) {
        long startTime = System.currentTimeMillis();

        System.out.println("File being read: " + path);
        List<String> rows = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String row;

            while ((row = reader.readLine()) != null) {
                rows.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("File read complete. (Time taken: " + totalTime + "ms)\n\n");

        return rows;
    }
}
