package numbers.model;

import java.util.Arrays;

public class ParallelStreamGroupSum extends GroupSum {
    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        return Arrays.stream(numberGroups)
                     .parallel()
                     .mapToInt(arr -> Arrays.stream(arr).sum())
                     .sum();
    }
}