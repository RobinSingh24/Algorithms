package DataStructures.Queue;

public class IntQueue {

    private int front,end,size;
    private int[] data;

    public IntQueue(int maxSize){
        front = end = size = 0;
        data = new int[maxSize];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public boolean isFull(){
        return size == data.length;
    }

    public void offer(int elem){
        if (isFull())
            throw new RuntimeException("Queue is full");
        data[end++] = elem;
        size++;
        end = end % data.length;
    }

    public int poll(){
        if(isEmpty())
            throw new RuntimeException("Queue is empty");
        size--;
        front = front % data.length;
        return data[front++];
    }

    public int peek(){
        if (isEmpty())
            throw new RuntimeException("Queue is Empty");
        front = front % data.length;
        return data[front];
    }

    public static void main(String[] args) {
        IntQueue queue = new IntQueue(5);
        queue.offer(1);
        queue.offer(3);
        queue.offer(5);
        queue.offer(7);

        System.out.println(queue.peek());
        queue.poll();
        System.out.println(queue.peek());

    }
}
