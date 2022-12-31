package DataStructures.PriorityQueue;

// constructor - Done
//isEmpty()  - done
//clear()  -done
//size()  -done
//peek()  -done
//poll() - done
//removeAt() - done
//contains()  -done
//add() - done
//less()  -done
//swim() - done
//sink() - done
//remove() - done
//isMinHeap
//mapAdd - done
//mapRemove - done
//mapGet - done
//mapSwap - done


import com.sun.source.tree.Tree;

import java.util.*;

public class binaryHeap<T extends Comparable<T>> {

    private List<T> heap = null;
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    public binaryHeap(){
        this(1);
    }
    public binaryHeap(int size){
        heap = new ArrayList<>(size);
    }

    public binaryHeap(Collection<T> elems){
        this(elems.size());
        for(T elem : elems) heap.add(elem);
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public int size(){
        return heap.size();
    }

    public void clear(){
        heap.clear();
        map.clear();
    }

    public T peek(){
        if(isEmpty()) return null;
        return heap.get(0);
    }
    //remove the root of the heap
    public void poll(){
        removeAt(0);
    }

    public boolean contains(T elem){
        if(elem == null || isEmpty()) return false;
        return map.containsKey(elem);
    }

    public void add(T elem){
        if(elem == null) throw new IllegalArgumentException("Element is null");
        heap.add(elem);
        int indexAdded = size()-1;
        mapAdd(elem,indexAdded);

        swim(indexAdded);
    }

    public boolean less(int i, int j){
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <=0;
    }

    public void swap(int i, int j){

        T elem_i = heap.get(i);
        T elem_j = heap.get(j);

        heap.set(i,elem_j);
        heap.set(j,elem_i);

        mapSwap(elem_i,elem_j,i,j);
    }

    public void swim(int k){

        int parent = (k-1)/2;

        while(k>=0 && less(k,parent)){

            swap(k,parent);
            k = parent;
            parent = (k-1)/2;
        }
    }

    public void sink(int k){

        while(true){

            int left = (2*k) +1;
            int right = (2*k) +2;
            //assume left is smallest
            int smallest = left;

            //check is right is smaller
            if(right<size() && less(right,left)) smallest=right;

            // stop if out of bounds or cannot sink anymore
            if(left>=size() || less(k,smallest)) break;
            swap(k,smallest);
            k = smallest;
        }
    }

    // remove the root node
    public boolean remove(T elem){
        if(elem == null) return false;

        Integer index = mapGet(elem);
        if(index!=null) removeAt(index);
        return index!=null;

    }

    public T removeAt(int index){

        int lastIndex = size()-1;
        T removedData = heap.get(index);

        T elem_index = heap.get(index);
        T elem_lastIndex = heap.get(lastIndex);
        //swap
        heap.set(index,elem_lastIndex);
        heap.set(lastIndex, elem_index);
        //destroy lastIndex value
        heap.set(lastIndex,null);
        //swap in map
        mapSwap(elem_index,elem_lastIndex,index,lastIndex );
        mapRemove(elem_index,lastIndex);

        if(index == lastIndex) return removedData;
        T elem = heap.get(index);
        sink(index);
        if(heap.get(index).equals(elem)) swim(index);

        return removedData;
    }

    public boolean isMinHeap(int k){

        int left = (2*k)+1;
        int right = (2*k)+2;

        if(k>size()) return true;

        if(left<size() && !less(k,left)) return false;
        if(right<size() && !less(k,right)) return false;

        return isMinHeap(left) && isMinHeap(right);
    }

    public void mapAdd(T elem, int index){

        TreeSet<Integer> set = map.get(elem);
        // no element in heap
        if(set == null){
            set = new TreeSet<>();
            set.add(index);
            map.put(elem,set);
        }
        else set.add(index);
    }

    public void mapRemove(T elem, int index){

        TreeSet<Integer> set = map.get(elem);
        if(set == null)
            throw new IllegalArgumentException("No Such element in heap");

        set.remove(index);
        if(set.size()==0) map.remove(elem);

    }

    public int mapGet(T elem){

        TreeSet<Integer> set  = map.get(elem);
        if(set == null) throw new IllegalArgumentException("No such element present");
        return set.last();
    }

    public void mapSwap(T node1, T node2, int index1, int index2){

        TreeSet<Integer> set1 = map.get(node1);
        TreeSet<Integer> set2 = map.get(node2);

        set1.remove(index1);
        set2.remove(index2);
        set1.add(index2);
        set2.add(index1);

    }













}
