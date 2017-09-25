package Tmax.Concurrent;

import Tmax.Station;
import Util.Common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class NoLock implements Runnable{
    private Thread t;
    private String name;
    private List<String> rows;
    private static Map<String, Station> sharedCount = new HashMap<>();

    NoLock(String name, List<String> rows) {
        this.name = name;
        this.rows = rows;

        //System.out.println("Creating: " + this.name);
    }

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

                if(!sharedCount.containsKey(cells[0])) {
                    sharedCount.put(cells[0], new Station(cells[0], parseInt(cells[3])));
                } else {
                    sharedCount.get(cells[0]).addToSum(parseInt(cells[3]));
                }
            }
        }

        //System.out.println("Thread " + name + " exiting.");
    }

    /**
     * Start a thread.
     */
    void start() {
        //System.out.println("Starting: " + name);

        if (t == null) {
            t = new Thread (this, name);
            t.start();
        }
    }

    /**
     * Wait for other threads to complete.
     */
    void join() {
        //System.out.println("Waiting: " + name);
        try {
            t.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

