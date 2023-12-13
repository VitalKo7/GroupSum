package numbers.model;

public abstract class GroupSum {
    protected int[][] numberGroups; // двух-мерный массив: массив массивов

    public GroupSum(int[][] numberGroups) {
        this.numberGroups = numberGroups;
    }
    public abstract int computeSum();
}