package MultiThreading;


import java.util.Map;

public class Common {
    /**
     * Function to print the key value pairs of the argument map,
     * while computing their averages by calling the computeAverage()
     * method of the Station object.
     *
     * @param count
     *             Map(String -> Station).
     *             Key: Station ID.
     *             Value: Associated Station Object.
     */
    public static void printMap(Map<String, Station> count) {
        for (Map.Entry<String, Station> entry : count.entrySet()) {
            entry.getValue().computeAverage();
//            System.out.println(entry.getKey() + ": " + entry.getValue().getAverage());
        }
    }
}
