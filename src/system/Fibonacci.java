package system;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Fibonacci implements Callable<Integer> {
    public int counter;

    public Fibonacci(int counter) {
        this.counter = counter;
    }

    @Override
    public Integer call() throws Exception {
        int previous = 0;
        int current = 1;
        int next = 0;
        System.out.println("Running counting");
        for (int num = 0; num <= 10 && !Thread.currentThread().isInterrupted(); num++) {
            next = previous + current;
            previous = current;
            current = next;
            TimeUnit.SECONDS.sleep(1);
        }

        if (Thread.currentThread().isInterrupted()) {
            System.out.println("We have been interrupted...");
        }

        System.out.println("Result = " + current);
        return current;
    }
}