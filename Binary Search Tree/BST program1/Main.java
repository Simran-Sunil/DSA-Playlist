public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Person> bst = new BinarySearchTree<>();

        bst.insert(new Person(12, "Arial"));
        bst.insert(new Person(14, "Siya"));
        bst.insert(new Person(40, "Sonal"));
        bst.insert(new Person(32, "Simran"));
        bst.insert(new Person(45, "Riya"));

        bst.traversal();

    }
    
}
