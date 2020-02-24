/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksort;

/**
 *
 * @author GeekCase
 */
public class QuickSort {

   private void exchange(int[] A, int i, int j){
       int temp = A[i];
       A[i] = A[j];
       A[j] = temp;
    }
   
   public QuickSort(int[] A){
       final int p = 0;
       int r = A.length-1;
       run(A, p, r);
    }
   
   public void run(int[] A, int p, int r){
       if(p < r){
           int q = partition(A, p, r);
           run(A, p, q-1);
           run(A, q+1, r);
        }
    }
   
   private int partition(int[] A, int p, int r){
       int x = A[r];
       int i = p-1;
       for(int j=p; j <= r-1; j++){
           if(A[j] <= x){
               i = i + 1;
               exchange(A, i, j);
            }
        }
        exchange(A, i+1, r);
        return i+1;
    }    
}
