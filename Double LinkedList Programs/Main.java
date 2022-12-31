public class Main {
    public static void main(String[] args){
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.insert("Simran");
        list.insert("Sonal");
        list.insert("Riya");
        
        list.traverseForward();
        System.out.println();
        list.traverseBackward();
    }
}
