package DataStructures.BinarySearchTree;


public class binarySearchTree<T extends Comparable> {

    private int noNodes=0;
    private Node root=null;

    private class Node{
        private T data;
        Node left,right ;

        public Node(Node left,Node right,T elem){
            this.data = elem;
            this.left = left;
            this.right = right;
        }
    }

    public int size(){
        return noNodes;
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public boolean contains(T elem){
        if (isEmpty()) return false;
        return contains(root,elem);
    }

    private boolean contains(Node node, T elem){

        if(node==null) return false;

        int cmp = elem.compareTo(node.data);

        if(cmp<0)
            return contains(node.left,elem);
        else if(cmp>0)
            return contains(node.right,elem);
        else
            return true;
    }

    // return true if element gets added
    public boolean add(T elem){
        if(contains(elem)) return false;
        root = add(root,elem);
        noNodes++;
        return true;
    }

    private Node add(Node node, T elem) {

        //base condition
        if(node==null)
        {
            node = new Node(null,null,elem);
        }
        else {
            int cmp = elem.compareTo(node.data);

            if (cmp < 0)
                node.left = add(node.left, elem);
            else if (cmp > 0)
                node.right = add(node.right, elem);
        }
    return node;
    }

    public boolean remove(T elem){
        if(contains(elem)) {
            root = remove(root, elem);
            noNodes--;
            return true;
        }
        return false;
    }

    private Node remove(Node node,T elem){

        if(node==null) return null;

        int cmp = elem.compareTo(node.data);

        if(cmp<0)
            node.left = remove(node.left,elem);
        else if(cmp>0)
            node.right = remove(node.right,elem);
        // we have found the value that we need to remove
        else {

            // left subtree is null
            if(node.left==null) return node.right;

            // right subtree is null
            else if(node.right==null) return node.left;

            // if both subtrees are present
            else {
                // find the smallest element in right subtree
                Node tmp = findMin(node.right);
                node.data = tmp.data;
                node.right = remove(node.right,tmp.data);
            }
        }
    return node;
    }

    private Node findMin(Node node){
        while (node.left!=null) node = node.left;
        return node;
    }

    private Node FindMax(Node node){
        while (node.right!=null)node = node.right;
        return node;
    }



}
