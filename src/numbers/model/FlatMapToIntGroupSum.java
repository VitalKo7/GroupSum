package numbers.model;

import java.util.Arrays;

public class FlatMapToIntGroupSum extends GroupSum {
    public FlatMapToIntGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        return Arrays.stream(numberGroups)
                     .flatMapToInt(Arrays::stream).sum();
    }
}