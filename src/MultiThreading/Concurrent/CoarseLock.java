package MultiThreading.Concurrent;

import MultiThreading.Station;
import MultiThreading.Common;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class CoarseLock implements Runnable {
    private Thread t;
    private String name;
    private List<String> rows;
    private final static Map<String, Station> sharedCount
            = Collections.synchronizedMap(new HashMap<>());

    CoarseLock(String name, List<String> rows) {
        this.name = name;
        this.rows = rows;

        //System.out.println("Creating: " + this.name);
    }

    /**
     * Compute final averages and print result.
     */
    void printCountMap() {
        Common.printMap(sharedCount);
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

                synchronized (sharedCount) {
                    if(sharedCount.containsKey(cells[0])) {
                        sharedCount.get(cells[0]).addToSum(parseInt(cells[3]));
                    } else {
                        Station newStation = new Station(cells[0], parseInt(cells[3]));
                        sharedCount.put(cells[0], newStation);
                    }
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
}

