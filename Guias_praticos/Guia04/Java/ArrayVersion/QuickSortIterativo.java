package quicksort;

import java.util.*;

public class QuickSortIterativo { 
    
    public class Posicao{
        public int left;
        public int right;
    }   
    
    private void exchange(int[] A, int i, int j){
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }  
    
    public QuickSortIterativo(int[] A){
        final int p = 0;
        int r = A.length-1;
        run(A, p, r);
    }    
    
    public void run(int[] A, int left, int right){
        if(left >= right) return;
        LinkedList<Posicao> pilha = new LinkedList<Posicao>();
        Posicao info = new Posicao();
        info.left = left;
        info.right = right;
        pilha.add(info);
        while(true){
            if( pilha.isEmpty() ) break;
            left = pilha.get(0).left;
            right = pilha.get(0).right;
            pilha.remove(0);
            int q = partition(A, left, right);
            if ( q > 1 ){
                info.left = left;
            }
            info.right = q - 1;
            pilha.add(info);
            if ( q + 1 < right ){
                info.left = q + 1;
                info.right = right;
                pilha.add(info);
            }
        }
    }

    private int partition(int[] A, int p, int r){
        int pivot = A[p];
        while(true){
            while(A[p] < pivot) p++;
            while(A[r] > pivot) r--;
            if ( p < r )
                exchange(A, p, r);
            else return r;
        }
    }
}