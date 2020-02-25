package heapsortlist;

import java.util.ArrayList;

public class HeapSortList {
    int heapsize = 0;
    public void Exchange(ArrayList<Integer> A, int i, int j){
        int temp = A.get(i);
        A.set(i, A.get(j));
        A.set(j, temp);
    }
    
    public int Left(int i){
        return 2 * i;
    }
    
    public int Right(int i){
        return 2 * i + 1;
    }
    
    public HeapSortList(ArrayList<Integer> A){
        BuildMaxHeap(A);
        for(int i = A.size() - 1; i >= 2; i--){
            Exchange(A, 1, i);
            heapsize = heapsize-1;
            MaxHeapify(A,1);
        }
    }
    
    public void BuildMaxHeap(ArrayList<Integer> A){
        heapsize = A.size() - 1;
        for(int i = (int) Math.floor(A.size() / 2.0); 
                i >= 1;
                i--) MaxHeapify(A, i);
    }
    
    public void MaxHeapify(ArrayList<Integer> A, int i){
        int l = Left(i);
        int r = Right(i);
        int largest = 0;
        if(l <= heapsize && A.get(l) > A.get(i)) largest = l;
        else largest = i;
        if(r <= heapsize && A.get(r) > A.get(largest)) largest = r;
        if(largest != i){
            Exchange(A, i, largest);
            MaxHeapify(A, largest);
        }
    }
}