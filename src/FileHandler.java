import java.io.BufferedReader;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public FileHandler() {
        System.out.println("File read initiated.");
    }

    static List<String> generateFile(String path) {
        System.out.println("File being read from: " + path);
        List<String> rows = new ArrayList<String>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            List<String> lines = new ArrayList<>();
            String row = null;

            while ((row = reader.readLine()) != null) {
                lines.add(row);
                System.out.println(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows;
    }
}
