package numbers.model;

import numbers.task.OneGroupSum;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorGroupSum extends GroupSum {
    int res = 0;

    public ExecutorGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        OneGroupSum[] tasks = new OneGroupSum[numberGroups.length];
        for (int i = 0; i < tasks.length; i++) {
            tasks[i] = new OneGroupSum(numberGroups[i]);
        }

        int poolSize = Runtime.getRuntime().availableProcessors();

//        ExecutorService executorService = Executors.newFixedThreadPool(12);   // very good time
//        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
//        ExecutorService executorService = Executors.newCachedThreadPool();    // worst time
        ExecutorService executorService = Executors.newWorkStealingPool();      // best time

        for (int i = 0; i < tasks.length; i++) {
            executorService.execute(tasks[i]);
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < tasks.length; i++) {
            res += tasks[i].getSum();
        }

        return res;

//        return Arrays.stream(tasks)
//                .mapToInt(OneGroupSum::getSum).sum();
    }
}