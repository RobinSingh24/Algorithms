package DataStructures.UnionFind;

public class unionFind {

    // no. of elements in this union find
    int size;

    // no. of components in union find
    int noComponents;

    //id[i] points to the parent of i, if id[i]= i then it is root node
    int[] id;

    //stores the size of the component
    int[] sz;

    public unionFind(int sz){
        size = noComponents = sz;
        id = new int[sz];
        this.sz = new int[sz];
        for(int i=0;i<size;i++)
        {
            id[i] = i;
            this.sz[i] = 1;
        }
    }

    public int find(int p){
        int root = p;
        //finding the root/parent of the given index/letter
        while(root != id[root])
        {
            root = id[root];
        }

        // compress the path leading back to the root
        //its known as path compression

        while(p !=root)
        {
            int next = id[p];
            id[p] = root;
            p = next;
        }
    return root;
    }

    public boolean connected(int p,int q){
        return find(p) == find(q);
    }

    public int componentSize(int p){
        return sz[find(p)];
    }

    public int getSize(){
        return size;
    }

    public int getNoComponents(){
        return noComponents;
    }

    public void unify(int p, int q){

        // already part of same component
        if(find(p) == find(q)) return;

        int root1 = find(p);
        int root2 = find(q);

        if(sz[root1] > sz[root2])
        {
            sz[root1] += sz[root2];
            id[root2] = root1;
            sz[root2] = 0;
        }
        else {
            sz[root2] += sz[root1];
            id[root1] = root2;
            sz[root1] = 0;
        }
        noComponents--;
    }








}
