public class App {
    public static void main(String[] args){
        int[] nums = {1, 2, 3, 4, 5};
        CheckHeap checkHeap = new CheckHeap();
        System.out.println(checkHeap.isMinHeap(nums));
    }
    
}
