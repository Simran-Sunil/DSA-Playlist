public class App {

    public static void main(String args[]){

        int[] nums = {4, 5, 8, 1, 2, 0, 8, 9, 5, 6};

        RadixSort sort = new RadixSort(nums);

        sort.sort();
        sort.showArray();
    }
}
