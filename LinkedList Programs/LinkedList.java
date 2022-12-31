public class LinkedList<T extends Comparable<T>> implements List<T> {

    // this is the head node or the root node
    private Node<T> root;
    private int numOfItems;



    public Node<T> getMiddleNode(){
        Node<T> slow = this.root;
        Node<T> fast = this.root;

        // O(N/2) = O(N) which is linear time complexity.
        while(fast.getNextNode() != null && fast.getNextNode().getNextNode() != null){
            slow = slow.getNextNode();
            fast = fast.getNextNode().getNextNode();
        }
        return slow;
    }

    @Override
    public void reverseList(){
        Node<T> currentNode = this.root;
        Node<T> previousNodeData = null;
        Node<T> nextNodeData = null;

        while(currentNode != null){
            nextNodeData = currentNode.getNextNode();
            currentNode.setNextNode(previousNodeData);
            previousNodeData = currentNode;
            currentNode = nextNodeData; 
        }
        this.root = previousNodeData;
    }

    @Override
    public void insert(T data) {
        if(root == null){
            //this is the first line in the linked list
            root = new Node<>(data);
        }else{
            // we know that this is not the first line in the linked list
            insertBeginning(data);
        }
    }

    //we hve to update to references O(1)
    private void insertBeginning(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNextNode(root);
        root = newNode;
    }

    // beacuse we hve to start with the root node.
    //first we need to find the last node in O(1).
    private void insertEnd(T data, Node<T> node) {
        if(node.getNextNode() != null){
            insertEnd(data, node.getNextNode());
        }else{
            // we have found the last value
            Node<T> newNode = new Node<>(data);
            newNode.setNextNode(newNode);
        }
    }

    @Override
    public void remove(T data) {
        if(root == null) return;

        // we want to remove the first node (root)
        if(root.getData().compareTo(data) == 0){
            root = root.getNextNode();
        }else{
            remove(data, root, root.getNextNode());
        }
    }

    private void remove(T data, Node<T> previousNode, Node<T> actualNode) {
        // we hve to find the node that we want to remove
        while(actualNode != null){
            // this is the node we want to remove
            if(actualNode.getData().compareTo(data) == 0){
                numOfItems--;
                previousNode.setNextNode(actualNode.getNextNode());
                actualNode = null;
                return;
            }

            previousNode = actualNode;
            actualNode = actualNode.getNextNode();
        }

    }

    @Override
    public void traverse() {
        if(root == null) return;

        Node<T> actualNode = root;

        while(actualNode != null){
            System.out.println(actualNode);
            actualNode = actualNode.getNextNode();
        }
    }

    @Override
    public int size() {
        return numOfItems;
    }
    
}
