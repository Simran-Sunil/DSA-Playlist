// Fenwick tree or binary indexed tree
public class FenwickTree {
    
    private int[] fenwickTree;

    public FenwickTree(int[] nums){
        // the constructed Fenwick tree (first item is 0 always so the size is greater)
        // initialize the values to be 0s
        this.fenwickTree = new int[nums.length+1];
        construct(nums);
    }

    // the sum of the numbers in the interval [start: end]
    // O(logN running time complexity)
    public int rangeSum(int startIndex, int endIndex){
        return sum(endIndex)-sum(startIndex-1);
    }

    // sum of the integers in the range [0:index]
    // O(logN) running time complexity
    public int sum(int index){
        // indexes starts with 0 but in the theory/implementation we start with 1
        index++;
        // the final result (so the sum og the integers)
        int sum = 0;

        // we may consider the sum of the multiple ranges so we have to iterate until index > 0
        while(index > 0){
            // binary index tree contains the sums of the given ranges 
            sum = sum + this.fenwickTree[index];
            // go to the parent and keep going (basically the items on the left)
            index = parent(index);
        }
        return sum;
    }

    // constructing the Fenwick Tree from the original array of integers
    // O(NlogN) running time complexity
    private void construct(int[] nums){
        // consider all the items in the original array
        for(int index = 1; index <=nums.length; ++index){
            update(index, nums[index - 1]);
        }
    }

    // update an existing item in the tree with index and value accordingly
    // O(logN) running time complexity
    public void update(int index, int num){
        // have to check all the ranges that include the index
        while(index < this.fenwickTree.length){
            this.fenwickTree[index]+=num;
            //index of the item on the right
            index = next(index);
        }
    }

    // index of the item on the left
    //O(1) running time complexity
    public int next(int index){
        return index+(index&-index);
    }

    // index of the item on the right (so the parent)
    // O(1) running time complexity
    public int parent(int index){
        return index-(index&-index);
    }
}
