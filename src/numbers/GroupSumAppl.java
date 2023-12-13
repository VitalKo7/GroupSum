package numbers;

import numbers.model.*;
import numbers.test.GroupSumPerformanceTest;

import java.util.Random;

public class GroupSumAppl {
    private static final int N_GROUPS = 10_000;
    private static final int NUMBER_PER_GROUP = 10_000;
    private static final int[][] arr = new int[N_GROUPS][NUMBER_PER_GROUP];

    private static Random random = new Random();

    public static void main(String[] args) {

        fillArray();

        GroupSum threadGroupSum = new ThreadGroupSum(arr);
        GroupSum executorGroupSum = new ExecutorGroupSum(arr);
        GroupSum streamGroupSum = new ParallelStreamGroupSum(arr);
        GroupSum flatMapToIntGroupSum = new FlatMapToIntGroupSum(arr);

                                                                                        // best time performance, ms:
        new GroupSumPerformanceTest("ThreadGroupSum", threadGroupSum).runTest();            // 603
        new GroupSumPerformanceTest("ExecutorGroupSum", executorGroupSum).runTest();        // 14
        new GroupSumPerformanceTest("ParallelStreamGroupSum", streamGroupSum).runTest();    // 13
        new GroupSumPerformanceTest("FlatMapToIntGroupSum", flatMapToIntGroupSum).runTest(); // 60  ? why slower
    }

    private static void fillArray() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = random.nextInt();
            }
        }
    }
}