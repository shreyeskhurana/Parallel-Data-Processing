import java.util.List;

public class PDPMain {
    public static void main(String[] args) {
        String path = "files/1813.csv";

        List<String> rows = FileHandler.generateFile(path);
    }
}
