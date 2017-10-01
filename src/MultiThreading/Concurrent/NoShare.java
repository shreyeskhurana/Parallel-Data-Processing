package MultiThreading.Concurrent;

import MultiThreading.Station;
import MultiThreading.Common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class NoShare implements Runnable{
    private Thread t;
    private String name;
    private List<String> rows;
    Map<String, Station> personalCount = new HashMap<>();

    NoShare(String name, List<String> rows) {
        this.name = name;
        this.rows = rows;

        //System.out.println("Creating: " + this.name);
    }

    /**
     * Build a map of Station IDs and their objects having info:
     * - count
     * - sumOfTemp
     */
    public void run() {
        //System.out.println("Running: " + name);

        for(String row : rows) {
            String[] cells = row.split(",");

            if(cells[2].equals("TMAX")) {
                if(cells[3].isEmpty())
                    continue;

                if(!personalCount.containsKey(cells[0])) {
                    Station newStation = new Station(cells[0], parseInt(cells[3]));
                    personalCount.put(cells[0], newStation);
                } else {
                    personalCount.get(cells[0]).addToSum(parseInt(cells[3]));

                }
            }
        }

        //System.out.println("Thread " + name + " exiting.");
    }

    void start() {
        //System.out.println("Starting: " + name);

        if (t == null) {
            t = new Thread (this, name);
            t.start();
        }
    }

    void join() {
        //System.out.println("Waiting: " + name);
        try {
            t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param otherCount
     *                  Map of the other thread having its
     *                  share of values.
     */
    void assimilate(Map<String, Station> otherCount) {
        for (Map.Entry<String, Station> entry : otherCount.entrySet()) {
            if(!personalCount.containsKey(entry.getKey())) {
                personalCount.put(entry.getKey(), entry.getValue());
            } else {
                personalCount.get(entry.getKey()).addToSum(entry.getValue().getSumOfTemp(), entry.getValue().getCount());

            }
        }

        //Compute average and print result
        Common.printMap(personalCount);
    }
}
