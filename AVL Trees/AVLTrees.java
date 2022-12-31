public class AVLTrees<T extends Comparable<T>> implements Tree<T> {

    private Node<T> root;

    @Override
    public void insert(T data) {
        // this is when we insert the first node in the tree ( ie. parent is null)
        if (root == null) {
            root = new Node<>(data, null);
        } else {
            // there are already nodes in the tree
            insert(data, root);
        }
    }

    private void insert(T data, Node<T> node) {
        // this is the case when the data is lesser than the value in the node
        // Go to the left subtree
        if (node.getData().compareTo(data) > 0) {
            // there is valid (not null) so we go there
            if (node.getLeftChild() != null) {
                insert(data, node.getLeftChild());
            } else {
                // if left child is null then we create a left child
                node.setLeftChild(new Node<>(data, node));
            }
        } else {
            // this is the case when the value is greator than the value in the node
            // Go to the right subtree
            if (node.getRightChild() != null) {
                insert(data, node.getRightChild());
            } else {
                // if right child is null then we create a left child
                node.setRightChild(new Node<>(data, node));
            }
        }

        updateHeight(node);
        // settle the violation
        settleViolations(node);
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

                updateHeight(parent);
                settleViolations(parent);
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

                updateHeight(parent);
                settleViolations(parent);
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

                updateHeight(parent);
                settleViolations(parent);
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
        // settle the violation

    }


    private Node<T> getPredecessor(Node<T> node) {
        if (node.getRightChild() != null) {
            return getPredecessor(node.getRightChild());
        }
        return node;
    }

    @Override
    public void traversal() {
        // in-order traversal in O(N) linear running time
        if (root == null)
            return;

        traversal(root);
    }

    // O(N)
    private void traversal(Node<T> node) {
        if (node.getLeftChild() != null)
            traversal(node.getLeftChild());

        System.out.print(node + " - ");

        if (node.getRightChild() != null)
            traversal(node.getRightChild());

    }

    private void settleViolations(Node<T> node) {
        // we have to check upto the root node - O(log N)
        while (node != null) {
            updateHeight(node);
            settleViolationsHelper(node);
            node = node.getParentNode();
        }
    }

    private void settleViolationsHelper(Node<T> node) {
        int balance = getBalance(node);

        // we know that the tree is left-heavy tree but it can be left-right heavy or
        // left-left heavy tree as well.
        if (balance > 1) {
            // left right heavy situation: left rotation
            if (getBalance(node.getLeftChild()) < 0) {
                leftRotation(node.getLeftChild());
            }

            // doubly left heavy situation then just a single right rotation is needed.
            // this is right rotation
            rightRotation(node);
        }

        // we know that the tree is right-heavy tree but it can be right-right heavy or
        // right-left heavy tree as well.
        if (balance < -1) {
            // right-left heavy situation: right rotation
            if (getBalance(node.getRightChild()) < 0) {
                rightRotation(node.getRightChild());
            }

            // doubly right heavy situation then just a single left rotation is needed.
            // this is left rotation
            leftRotation(node);
        }
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

        // after rotations the height parameters can change.
        updateHeight(node);
        updateHeight(tempLeftChild);
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

        // after rotations the height parameters can change.
        updateHeight(node);
        updateHeight(tempRightChild);
    }

    // update the height of the given node
    private void updateHeight(Node<T> node) {
        node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
    }

    // it returns the height parameter for a given node
    private int height(Node<T> node) {
        if (node == null)
            return -1;

        return node.getHeight();
    }

    // balance factor to decide the left heavy or right heavy cases
    private int getBalance(Node<T> node) {
        if (node == null)
            return 0;

        return height(node.getLeftChild()) - height(node.getRightChild());
    }
}
