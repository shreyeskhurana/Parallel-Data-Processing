package Tmax;

/**
 * Station Class
 */
public class Station {
    private String id;                  //ID
    private Integer count;              //Number of temperature entries
    private Integer sumOfTemp;          //Running Sum of the entries
    private Double Average;             //Average of the entries

    public Station(String id, Integer firstTemp) {
        this.id = id;
        this.sumOfTemp = firstTemp;
        count = 1;
        //fibonacci(17);
    }

    public void addToSum(Integer tempSum) {
        sumOfTemp += tempSum;
        count++;
        //fibonacci(17);
    }

    public void addToSum(Integer tempSum, Integer tempCount) {
        sumOfTemp += tempSum;
        count += tempCount;
        //fibonacci(17);
    }

    public void computeAverage() {
        Average = 0.0;

        if(count != 0)
            Average = sumOfTemp/(double)count;
    }

    public Integer getSumOfTemp() {
        return sumOfTemp;
    }

    public Integer getCount() {
        return count;
    }

    public Double getAverage() {
        return Average;
    }

    /** Fibonacci: To calculate the nth fibonacci number.
     *
     * Using the naive fibonacci Algorithm
     * to see the effect of time consuming commands
     * while attaining the lock. */
    private int fibonacci(int n) {
        if(n == 0) {
            return 0;
        } else if(n == 1) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
