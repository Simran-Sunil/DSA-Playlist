public class InsertionSort {
    
    private int[] nums;

    public InsertionSort(int[] nums){
        this.nums = nums;
    }

    public void sort(){
        for(int i=1;i<nums.length;++i){
            int j = i;
            // insertion sort makes a lot of shifts
            while(j > 0 && nums[j-1] > nums[j]){
                swap(j, j-1);
                j--;
            }
        }

    }

    public void showArray(){
        for(int i=0;i<nums.length;++i){
            System.out.print(nums[i]+" ");
        }
    }

    // swap 2 items in the nums array in O(1)
    private void swap(int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
