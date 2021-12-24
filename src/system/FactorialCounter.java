package system;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FactorialCounter implements Callable<Integer> {
    public int factorial;

    public FactorialCounter(int factorial) {
        this.factorial = factorial;
    }

    @Override
    public Integer call() throws InterruptedException {
        int result = 1;
        System.out.println("Starting counting factorial");
        for (int num = 1; num <= 7 && !Thread.currentThread().isInterrupted(); num++) {
            result *= num;
            TimeUnit.SECONDS.sleep(1);
        }
        if (Thread.currentThread().isInterrupted()) {
            System.out.println("Process interrupted");
        }
        System.out.println("Finished, value: " + result);
        return result;
    }
}

