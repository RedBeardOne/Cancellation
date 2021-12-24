import system.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        try {
            Main.run();
        } catch (NumbNegative numbNegative) {
            numbNegative.printStackTrace();
        }
    }

    private static void run() throws NumbNegative {
        Copy copying = new Copy("picture.jpg", "picture2.jpg");
        Fibonacci fibonacci = new Fibonacci(10);
        FactorialCounter factorialCounter = new FactorialCounter(10);
        Stopwatch stopwatch = new Stopwatch(60);

        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<Integer>> tasks = new ArrayList<>();
        tasks.add(executor.submit(copying));
        tasks.add(executor.submit(fibonacci));
        tasks.add(executor.submit(factorialCounter));
        tasks.add(executor.submit(stopwatch));

        Future<Integer> integerFuture = null;
        int counter = 0;
        int counterTwo = tasks.size();

        while (counterTwo > 0) {
            integerFuture = tasks.get(counter);
            counter++;
            try {
                System.out.println();
                System.out.printf("The values %d: %d%n", counter, integerFuture.get(5, TimeUnit.SECONDS));
            } catch (TimeoutException | InterruptedException e) {
                integerFuture.cancel(true);
                System.out.printf("Thread canceled!", counter);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            --counterTwo;
        }
    }
}

