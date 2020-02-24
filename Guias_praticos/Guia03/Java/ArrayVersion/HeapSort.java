package heapsort;

public class HeapSort {
    int heapsize = 0;
    public void Exchange(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
    
    public int Left(int i){
        return 2 * i;
    }
    
    public int Right(int i){
        return 2 * i + 1;
    }
    
    public HeapSort(int[] A){
        BuildMaxHeap(A);
        for(int i=A.length-1; i >= 2; i--){
            Exchange(A, 1, i);
            heapsize = heapsize-1;
            MaxHeapify(A,1);
        }
    }
    
    public void BuildMaxHeap(int[] A){
        heapsize = A.length-1;
        for(int i = (int) Math.floor(A.length/2.0); 
                i >= 1;
                i--) MaxHeapify(A, i);
    }
    
    public void MaxHeapify(int[] A, int i){
        int l = Left(i);
        int r = Right(i);
        int largest = 0;
        if(l <= heapsize && A[l] > A[i]) largest = l;
        else largest = i;
        if(r <= heapsize && A[r] > A[largest]) largest = r;
        if(largest != i){
            Exchange(A, i, largest);
            MaxHeapify(A, largest);
        }
    }
}