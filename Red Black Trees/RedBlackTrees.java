public class RedBlackTrees<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;
    
    @Override
    public void insert(T data){
        // this is when we insert the first node in the tree ( ie. parent is null)
        if(root == null){
            root = new Node<>(data, null);
            settleViolations(root);
        } else{
            // there are already nodes in the tree
            insert(data, root);
        }  
    }

    // insertion has O(log N) time complexity.
    private void insert(T data, Node<T> node) {
        // this is the case when the data is lesser than the value in the node
        // Go to the left subtree
        if(node.getData().compareTo(data) > 0){
            // there is valid (not null) so we go there
            if(node.getLeftChild() != null){
                insert(data, node.getLeftChild());
            }else{
                // if left child is null then we create a left child
                Node<T> newNode = new Node<>(data, node);
                node.setLeftChild(newNode);
                // we have to check whether the red - black property is violated or not.
                settleViolations(newNode);

            }
        }else{
            // this is the case when the value is greator than the value in the node
            // Go to the right subtree
            if(node.getRightChild() != null){
                insert(data, node.getRightChild());
            }else{
                // if right child is null then we create a left child
                Node<T> newNode = new Node<>(data, node);
                node.setRightChild(newNode);
                // we have to check whether the red - black property is violated or not.
                settleViolations(newNode);
            }
        }
    }

    @Override
    public void remove(T data) {
        if (root != null)
            remove(data, root);
    }

    private void remove(T data, Node<T> node) {
        if (node == null)
            return;

        // first we have to search for item we have to remove
        if (data.compareTo(node.getData()) < 0) {
            remove(data, node.getLeftChild());
        } else if (data.compareTo(node.getData()) > 0) {
            remove(data, node.getRightChild());
        } else {
            // we have found the item we want to remove!

            // if the node is a leaf node (without left and right children)
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                // whether the node is a left child or right child of the parent
                System.out.println("Removing a leaf node:");

                Node<T> parent = node.getParentNode();

                // Case (1)
                // if the node is a left child to the parent
                if (parent != null && parent.getLeftChild() == node) {
                    parent.setLeftChild(null);

                    // if the node is a right child to the parent
                } else if (parent != null && parent.getRightChild() == node) {
                    parent.setRightChild(null);
                }

                // maybe the root node is the one we want to remove
                if (parent == null)
                    root = null;

                // remove the node and make it eligible for Garbage Collection
                node = null;

                // updateHeight(parent);
                // settleViolations(parent);
            }

            // Case (2) - when we remove items with a single child
            // a single right child
            else if (node.getLeftChild() == null && node.getRightChild() != null) {
                System.out.println("Removing a node with a single right child:");
                Node<T> parent = node.getParentNode();

                // if the node is a left child
                if (parent != null && parent.getLeftChild() == node) {
                    parent.setLeftChild(node.getRightChild());
                    // the node is a right child
                } else if (parent != null && parent.getRightChild() == node) {
                    parent.setRightChild(node.getRightChild());
                }

                // when we deal with the root node
                if (parent == null) {
                    root = node.getRightChild();
                }

                // have to update the right childs parent
                node.getRightChild().setParentNode(parent);
                node = null;

                // updateHeight(parent);
                // settleViolations(parent);
            }

            // same as CASE (2) but we hve to deal with left child
            else if (node.getLeftChild() != null && node.getRightChild() == null) {
                System.out.println("Removing a node with a single left child:");
                Node<T> parent = node.getParentNode();

                // if the node is a left child
                if (parent != null && parent.getLeftChild() == node) {
                    parent.setLeftChild(node.getLeftChild());
                    // the node is a right child
                } else if (parent != null && parent.getRightChild() == node) {
                    parent.setRightChild(node.getLeftChild());
                }

                // when we deal with the root node
                if (parent == null) {
                    root = node.getLeftChild();
                }

                // have to update the right childs parent
                node.getLeftChild().setParentNode(parent);
                node = null;

                // updateHeight(parent);
                // settleViolations(parent);
            }

            // when we have to remove 2 childern
            else {
                System.out.println("Removing a node with 2 childern:");
                // find the predecessor (max item in the left subtree)
                Node<T> predecessor = getPredecessor(node.getLeftChild());

                // swap just the values
                T temp = predecessor.getData();
                predecessor.setData(node.getData());
                node.setData(temp);

                // we have to call the delete method recursively on the predecessor
                remove(data, predecessor);
            }
        }
    }

    // takes atmost O(logN)
    private void settleViolations(Node<T> node){
        Node<T> parentNode = null;
        Node<T> grandParentNode = null;

        // we have to check the violations up to the root node
        while(node != root && isRed(node) && isRed(node.getParentNode())){

            parentNode = node.getParentNode();
            grandParentNode = node.getParentNode().getParentNode();

            // parent is a left child of its parent (so the grandparent)
            if(parentNode == grandParentNode.getLeftChild()){
                Node<T> uncle = grandParentNode.getRightChild();

                // case 1) and case 4) RECOLOURING
                if(uncle != null && isRed(uncle)){
                    grandParentNode.setColor(NodeColor.RED);
                    parentNode.setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);
                    node = grandParentNode;
                }else{
                    // case 2
                    if(node == parentNode.getRightChild()){
                        leftRotation(parentNode);
                        // update the reference we keep going up to the root node 
                        node = parentNode;
                        parentNode = grandParentNode;
                    }

                    // case 3: rotation on the grandparent + parent and grandparent switch color
                    rightRotation(grandParentNode);
                    System.out.println("Recolouring "+parentNode+" + "+grandParentNode);
                    // swap the color of parent and grandparent
                    NodeColor tempColor = parentNode.getColor();
                    parentNode.setColor(grandParentNode.getColor());
                    grandParentNode.setColor(tempColor);
                    //update the reference because we keep going to the root node
                    node = parentNode;
                }
            }else{
                // parent is the right child of its parent (so the grandparent)
                Node<T> uncle = grandParentNode.getLeftChild();

                // case 1) and case 4) are symmetric partners
                if(uncle != null  && isRed(uncle)){
                    grandParentNode.setColor(NodeColor.RED);
                    parentNode.setColor(NodeColor.BLACK);
                    uncle.setColor(NodeColor.BLACK);
                    node = grandParentNode;
                }else{
                    // case 2) is a symmetric partner
                    if(node == parentNode.getLeftChild()){
                        rightRotation(parentNode);
                        node = parentNode;
                        parentNode = grandParentNode;
                    }

                    // case 3) symmetric partner
                    leftRotation(grandParentNode);
                    System.out.println("Recolouring "+parentNode+" + "+grandParentNode);
                    // swap the color of parent and grandparent
                    NodeColor tempColor = parentNode.getColor();
                    parentNode.setColor(grandParentNode.getColor());
                    grandParentNode.setColor(tempColor);
                    //update the reference because we keep going to the root node
                    node = parentNode;
                }
            }
        }

        // if the root node color is RED then we hve to recolor if necessary
        if(isRed(root)){
            System.out.println("Recoloring the root to black...");
            root.setColor(NodeColor.BLACK);
        }
    }

    private boolean isRed(Node<T> node){
        if(node ==  null){
            return false;
        }
        return node.getColor() == NodeColor.RED;
    }

    private Node<T> getPredecessor(Node<T> node) {
        if (node.getRightChild() != null) {
            return getPredecessor(node.getRightChild());
        }
        return node;
    }

    @Override
    public void traverse(){
        //in-order traversal in O(N) linear running time
        if(root != null) 
            traversal(root);
    }

    //O(N)
    private void traversal(Node<T> node) {
        if(node.getLeftChild() != null)
            traversal(node.getLeftChild());

        System.out.print(node + " - ");

        if(node.getRightChild() != null)
            traversal(node.getRightChild());
        
    }

    // it can be done in O(1)
    private void rightRotation(Node<T> node) {
        System.out.println("Right Rotation on node: " + node);
        // this is the new root node after the rotation (Node B)
        Node<T> tempLeftChild = node.getLeftChild();
        // Node C
        Node<T> grandChild = tempLeftChild.getRightChild();

        // make the rotation - the new root node will be the tempLeftChild
        tempLeftChild.setRightChild(node);
        node.setLeftChild(grandChild);

        // we have to handle the parents
        if (grandChild != null) {
            grandChild.setParentNode(node);
        }

        // we have to handle the parents for the node
        Node<T> tempParent = node.getParentNode();
        node.setParentNode(tempLeftChild);
        tempLeftChild.setParentNode(tempParent);

        // we have to handle the parent
        if (tempLeftChild.getParentNode() != null && tempLeftChild.getParentNode().getLeftChild() == node) {
            tempLeftChild.getParentNode().setLeftChild(tempLeftChild);
        }
        if (tempLeftChild.getParentNode() != null && tempLeftChild.getParentNode().getLeftChild() == node) {
            tempLeftChild.getParentNode().setRightChild(tempLeftChild);
        }

        // no parent after the rotation because it has become the root node
        if (node == root) {
            root = tempLeftChild;
        }
    }

    // it can be done in O(1)
    private void leftRotation(Node<T> node) {
        System.out.println("Left Rotation on node: " + node);
        // this is the new root node after the rotation (Node B)
        Node<T> tempRightChild = node.getRightChild();
        // Node C
        Node<T> grandChild = tempRightChild.getLeftChild();

        // make the rotation - the new root node will be the tempLeftChild
        tempRightChild.setLeftChild(node);
        node.setRightChild(grandChild);

        // we have to handle the parents
        if (grandChild != null) {
            grandChild.setParentNode(node);
        }

        // we have to handle the parents for the node
        Node<T> tempParent = node.getParentNode();
        node.setParentNode(tempRightChild);
        tempRightChild.setParentNode(tempParent);

        // we have to handle the parent
        if (tempRightChild.getParentNode() != null && tempRightChild.getParentNode().getLeftChild() == node) {
            tempRightChild.getParentNode().setLeftChild(tempRightChild);
        }
        if (tempRightChild.getParentNode() != null && tempRightChild.getParentNode().getLeftChild() == node) {
            tempRightChild.getParentNode().setRightChild(tempRightChild);
        }

        // no parent after the rotation because it has become the root node
        if (node == root) {
            root = tempRightChild;
        }
    }

}