public class Main {
    public static void main(String[] args){
        Stack<Integer> nums = new Stack<>();

        nums.push(12);
        nums.push(20);
        nums.push(55);
        nums.push(40);
        nums.push(76);

        while(!nums.isEmpty()){
            System.out.println(nums.pop());
        }
    }
}
