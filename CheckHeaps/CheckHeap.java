/* ------Design an algorithms that can check whether a heap 
(with array representation) is a valid min heap or not. 
Valid min heap is when the parent node is smaller than the children. ------ */

public class CheckHeap {
    
    public boolean isMinHeap(int[] heap){
        // leaf nodes do not have children
        // if 2*i+2 >= N then we know that the node is leaf node( no need to consider )
        // so we have to rearrange the equation to get how many nodes to consider
        for(int i=0;i<=(heap.length-2) / 2;i++){
            // for non lead nodes we just have to check the min heap properties
            if(heap[i] > heap[2*i+1] || heap[i] > heap[2*i+2]){
                return false;
            }
        }
        return true;
    }
}
