public class App {

    public static void main(String[] args){

        Tree<Integer> rbt = new RedBlackTrees<>();

        rbt.insert(32);
        rbt.insert(10);
        rbt.insert(55);
        rbt.insert(1);
        rbt.insert(19);
        rbt.insert(79);
        rbt.insert(16);
        rbt.insert(23);
        rbt.insert(12);

        rbt.traverse();
    }
}
