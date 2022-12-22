package DataStructures.DynamicArray;
import java.util.Arrays;

// methods of java.util.Arrays class
// java.util.Arrays.copyOf()
// java.util.Arrays.binarySearch()
// java.util.Arrays.sort()

// java.lang.System.arrayCopy()



public class intArray {

    public int []arr;
    public int len = 0; // what user thinks
    private int capacity=0;// actual capacity

    public intArray()
    {
        this(3);
    }

    public intArray(int capacity)
    {
        if (capacity <=0) throw new IllegalArgumentException("Capacity cannot be zero");
        this.capacity  = capacity;
        arr = new int[capacity];
    }

    public intArray(int[] staticArray)
    {
        if(staticArray==null) throw new IllegalArgumentException("Array cannot be null");
        arr = Arrays.copyOf(staticArray, staticArray.length);
        this.capacity = len = staticArray.length;
    }

    public int size()
    {
        return len;
    }

    public boolean isEmpty()
    {
        return size()==0;
    }

    public void add(int elem)
    {
        if(len+1>=capacity)
        {
            if(capacity ==0) capacity =1;
            else capacity*= 2;
            arr = Arrays.copyOf(arr,capacity);
        }

        arr[len++] = elem;
    }

    public void removeAt(int rm_index)
    {
        System.arraycopy(arr,rm_index+1,arr,rm_index,capacity-rm_index-1);
        len--;
    }

    public void remove(int elem)
    {
        int key=-1;
        for(int i=0;i<len;i++)
        {
            if(arr[i] == elem ) key = i;
        }
        if(key>=0) removeAt(key);
        else System.out.println("element not in array");
    }

    public void reverseArray(){
        int tmp;
        for(int i=0;i<len/2;i++)
        {
            tmp = arr[len-i-1];
            arr[len-i-1] = arr[i];
            arr[i] = tmp;
        }
    }


    public static void main(String[] args) {

        intArray myArray = new intArray(5);
        myArray.add(1);
        myArray.add(5);
        myArray.add(7);
        myArray.add(9);
        System.out.println(Arrays.toString(myArray.arr));
        System.out.println(myArray.size());
        myArray.remove(5);
        System.out.println(Arrays.toString(myArray.arr));




    }
}
