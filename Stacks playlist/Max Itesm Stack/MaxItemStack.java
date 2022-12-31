import java.util.Stack;

public class MaxItemStack {
    // this is the original stack
    private Stack<Integer> mainStack;
    // this stack is just for tracking the maximum item.
    private Stack<Integer> maxStack;

    public MaxItemStack(){
        this.mainStack = new Stack<>();
        this.maxStack = new Stack<>();
    }

    public void push(int item){
        // push the item onto the stack
        mainStack.push(item);

        // first item is the same in both the stack
        if(mainStack.size() == 1){
            maxStack.push(item);
            return;
        }

        // if the element is the largest one so far we insert it into the stack
        if(item>maxStack.peek()){
            maxStack.push(item);
        }else{
            // if not the largest one we duplicate the largest one onto the stack
            maxStack.push(maxStack.peek());
        }
    }

    //when removing an item we remove it from both the stack
    public int pop(){
        maxStack.pop();
        return mainStack.pop();
    }

    // max item is the last item wehve inserted into the maxstack - O(1)
    public int getMaxItem(){
        return maxStack.peek();
    }
    
}
