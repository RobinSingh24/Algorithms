package DynamicArray;

//methods
//clear()
//size()
//isEmpty()
//addFirst()
//addLast()
//add()
//addAtIndex()
//removeFirst()
//removeLast()
//remove(Node<T> node)
//removeAt()


public class DoublyLinkedList<T> {

    private int size=0;
    private Node<T> head;
    private Node<T> tail;

    private static class Node<T> {
        private T data;
        private Node<T> prev,next;
        public Node(T elem, Node<T> prev, Node<T> next)
        {
            this.data = elem;
            this.prev = prev;
            this.next = next;
        }
    }

    public void clear(){
        Node<T> trav = head;
        while(trav!=null)
        {
            Node<T> next = trav.next;
            trav.prev = trav.next = null;
            trav.data = null;
            trav = next;
        }
        trav = head = tail = null;
        size =0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public void addFirst(T elem){

        if(isEmpty()) head = tail = new Node<T>(elem,null,null);
        else {
            head.prev = new Node<T>(elem,null,head);
            head = head.prev;
        }
        size++;
    }

    public void addLast(T elem){

        if(isEmpty()) head = tail = new Node<T>(elem,null,null);
        else {
            tail.next = new Node<T>(elem, tail, null);
            tail = tail.next;
        }
        size++;
    }

    //add to last
    public void add(T elem){
        addLast(elem);
    }

    //add at a given index
    public void addAt(T elem,int index) throws Exception{

        if(index<0 || index>size) throw new Exception("Index is invalid");
        if(index==0){
            addFirst(elem);
            return;
        }
        if(index==size){
            addLast(elem);
            return;
        }

        Node<T> tmp = head;
        for(int i=0;i<index-1;i++)
        {
            tmp = tmp.next;
        }
        tmp.next.prev = new Node<T>(elem,tmp,tmp.next);
        tmp.next = tmp.next.prev;

        size++;
    }

    public T removeFirst() throws Exception{

        if(isEmpty()) throw new Exception("Linked List is empty");

        T data = head.data;
        head = head.next;
        size--;

        // set tail to null if list is empty after removal else do cleanup
        if(isEmpty()) tail=null;
        else head.prev =null;

        return data;
    }

    public T removeLast() throws Exception{

        if(isEmpty()) throw new Exception("List empty");

        T data = tail.data;
        tail = tail.prev;
        size--;

        //set head to null if size is 0 after removal else do cleanup
        if(isEmpty()) head =null;
        else tail.next = null;

        return data;
    }

    private T remove(Node<T> node) throws Exception{

        //if node is at head or tail then handle removal independently
        if(node.prev==null) removeFirst();
        if(node.next==null) removeLast();

        //skip the node when it is between two nodes
        node.next.prev = node.prev;
        node.prev.next = node.next;

        T data = node.data;
        node.next = node.prev = null;
        node.data = null;

        size--;

        return data;
    }

    public T removeAt(int index) throws Exception{

        if(index<0 || index>=size) throw new Exception("Invalid index" + index);
        if(index==0) removeFirst();
        if(index==size-1) removeLast();

        Node<T> trav;
        int i;
        //go to the index node
        // either traverse entire list in one way or search first half or last half

        //search the first half from head
        if(index<size/2)
        {
            for(i=0,trav=head;i!=index;i++)
            {
                trav = trav.next;
            }
        }
        else //search from tail
        {
            for(i=size-1,trav=tail;i!=index;i--)
            {
                trav = trav.prev;
            }
        }
        return remove(trav);
    }
















}
