package MultiThreading.Sequential;

import MultiThreading.Station;
import MultiThreading.Common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class SeqComp {
    /**
     * Function to build a mapping of Station IDs and their
     * corresponding Station objects.
     *
     * @param rows
     *             List of Strings.
     *             Each value in the list correspond to rows of the
     *             input csv.
     */
    public void countByStation(List<String> rows) {
        long startTime = System.currentTimeMillis();

        Map<String, Station> count = new HashMap<>();

        for(String row : rows) {
            String[] cells = row.split(",");

            if(cells[2].equals("TMAX")) {
                if(cells[3].isEmpty())
                    continue;

                if(count.containsKey(cells[0])) {
                    count.get(cells[0]).addToSum(parseInt(cells[3]));
                } else {
                    Station newStation = new Station(cells[0], parseInt(cells[3]));
                    count.put(cells[0], newStation);
                }
            }
        }

        //Compute average and print results.
        Common.printMap(count);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Sequential Time: " + totalTime + "ms");
    }
}
