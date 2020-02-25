/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksortlist;

import java.util.ArrayList;

public class QuickSortList {

   private void exchange(ArrayList<Integer> A, int i, int j){
        int temp = A.get(i);
        A.set(i, A.get(j));
        A.set(j, temp);
    }
   
   public QuickSortList(ArrayList<Integer> A){
       final int p = 0;
       int r = A.size() - 1;
       run(A, p, r);
    }
   
   public void run(ArrayList<Integer> A, int p, int r){
       if(p < r){
           int q = partition(A, p, r);
           run(A, p, q-1);
           run(A, q+1, r);
        }
    }
   
   private int partition(ArrayList<Integer> A, int p, int r){
       int x = A.get(r);
       int i = p-1;
       for(int j=p; j <= r-1; j++){
           if(A.get(j) <= x){
               i = i + 1;
               exchange(A, i, j);
            }
        }
        exchange(A, i+1, r);
        return i+1;
    }    
    
}
