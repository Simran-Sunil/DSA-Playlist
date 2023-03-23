public class App {

    public static void main (String[] args){

        int[] nums = {3, 2, -1, 6, 5, 4, -3, 3, 7, 2, 3};

        FenwickTree fenwickTree = new FenwickTree(nums);

        System.out.println(fenwickTree.rangeSum(5, 10));
    } 
}
