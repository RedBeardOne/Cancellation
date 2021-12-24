package system;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Stopwatch implements Callable<Integer> {
    public int endTimeInSeconds;
    public int timeInSeconds;

    public Stopwatch(int timeInSeconds) throws NumbNegative {
        this.timeInSeconds = timeInSeconds;
        if (timeInSeconds < 0) {
            throw new NumbNegative("Value cannot be <0",
                    new IllegalArgumentException());
        }
    }

    @Override
    public Integer call() throws InterruptedException {
        while (timeInSeconds >= 0 && !Thread.currentThread().isInterrupted()) {
            if (endTimeInSeconds > 0 && timeInSeconds == 0) {
                endTimeInSeconds = 0;
                break;
            }
            if (timeInSeconds == 0) {
                endTimeInSeconds = 0;
                System.out.println("Broken ");
                break;
            }
            if (timeInSeconds > 60) {
                break;
            }
            System.out.println("Remain seconds: " + timeInSeconds);
            timeInSeconds--;
            endTimeInSeconds++;
            TimeUnit.SECONDS.sleep(1);
        }
        if (Thread.currentThread().isInterrupted()) {
            System.out.println("Process interrupted");
        }

        return endTimeInSeconds;
    }
}
