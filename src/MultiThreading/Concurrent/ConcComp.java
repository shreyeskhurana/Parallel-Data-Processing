package MultiThreading.Concurrent;

import java.util.List;

public class ConcComp {
    /**
     * Compute averages on a shared map data structure
     * without synchronization.
     *
     * @param rows
     *             List of Strings.
     *             Each value in the list correspond to rows of the
     *             input csv.
     */
    public void noLock(List<String> rows) {
        long startTime = System.currentTimeMillis();

        //Initialize 2 threads
        NoLock noLock1 = new NoLock("Thread1", rows.subList(0, rows.size()/2));
        NoLock noLock2 = new NoLock("Thread2", rows.subList(rows.size()/2, rows.size()));

        noLock1.start();
        noLock2.start();

        noLock1.join();
        noLock2.join();

        noLock1.printCountMap();

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("NoLock Time:     " + totalTime + "ms");
    }

    /**
     * Compute averages with multiple threads on a shared map
     * data structure with coarse synchronization, i.e. lock
     * the entire map to make updates.
     *
     * @param rows
     *             List of Strings.
     *             Each value in the list correspond to rows of the
     *             input csv.
     */

    public void coarseLock(List<String> rows) {
        long startTime = System.currentTimeMillis();

        //Initialize 2 threads
        CoarseLock coarseLock1 = new CoarseLock("Thread1", rows.subList(0, rows.size()/2));
        CoarseLock coarseLock2 = new CoarseLock("Thread2", rows.subList(rows.size()/2, rows.size()));

        coarseLock1.start();
        coarseLock2.start();

        coarseLock1.join();
        coarseLock2.join();

        coarseLock1.printCountMap();

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("CoarseLock Time: " + totalTime + "ms");
    }

    /**
     * Compute averages with multiple threads on a shared map
     * data structur with fine synchronization, i.e. lock only
     * those entries in the map to be updated.
     *
     * @param rows
     *             List of Strings.
     *             Each value in the list correspond to rows of the
     *             input csv.
     */
    public void fineLock(List<String> rows) {
        long startTime = System.currentTimeMillis();

        //Initialize 2 threads
        FineLock fineLock1 = new FineLock("Thread1", rows.subList(0, rows.size()/2));
        FineLock fineLock2 = new FineLock("Thread2", rows.subList(rows.size()/2, rows.size()));

        fineLock1.start();
        fineLock2.start();

        fineLock1.join();
        fineLock2.join();

        fineLock1.printCountMap();

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("FineLock Time:   " + totalTime + "ms");
    }

    /**
     * Compute averages with multiple threads on a non shared
     * map data structure.
     * Then combine the results of the individual threads.
     *
     *
     * @param rows
     *             List of Strings.
     *             Each value in the list correspond to rows of the
     *             input csv.
     */
    public void NoShare(List<String> rows) {
        long startTime = System.currentTimeMillis();

        //Initialize 2 threads
        NoShare noShare1 = new NoShare("Thread1", rows.subList(0, rows.size()/2));
        NoShare noShare2 = new NoShare("Thread2", rows.subList(rows.size()/2, rows.size()));

        noShare1.start();
        noShare2.start();

        //Wait for them to finish
        noShare1.join();
        noShare2.join();

        //Combine results of the two threads
        noShare1.assimilate(noShare2.personalCount);

        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("NoShare Time:    " + totalTime + "ms");
    }
}
