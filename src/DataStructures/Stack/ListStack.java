package DataStructures.Stack;
import java.util.EmptyStackException;
import java.util.LinkedList;

public class ListStack<T> {

    private LinkedList<T> stack = new LinkedList<T>();

    public ListStack(){}

    public ListStack(T elem){
        push(elem);
    }

    public int size(){
        return stack.size();
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public void push(T elem){
        stack.addLast(elem);
    }

    public void pop(){
        if(isEmpty()) throw new EmptyStackException();
        stack.removeLast();
    }

    public T peek(){
        if(isEmpty()) throw new EmptyStackException();
        return stack.peekLast();
    }

    public int search(T elem){
        return stack.lastIndexOf(elem);
    }


}
