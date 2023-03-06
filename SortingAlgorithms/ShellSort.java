public class ShellSort {
    private int[] nums;

    public ShellSort(int[] nums){
        this.nums = nums;
    }

    // shell sort is an insertion sort varient
    public void sort(){
        
        // usually the gap = number of items / 2
        // when the gap = 1 this is the standard insertion
        for(int gap = nums.length/2; gap > 0; gap/=2){
            for(int i=gap;i<nums.length;++i){
                int j = i;
                // insertion sort makes a lot of shifts
                while(j >= gap && nums[j-gap] > nums[j]){
                    swap(j, j-gap);
                    j-=gap;
                }
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
