/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvorepesquisabinaria;

public class Memoria{
    public int[] p = null;
    public int[] key = null;
    public int[] left = null;
    public int[] right = null;
    final int NIL = 0;
    Pilha s = null;
    
    public Memoria(int n){
        this.p = new int[n];
        this.key = new int[n];
        this.left = new int[n];
        this.right = new int[n];
        this.s = new Pilha(n);
        for(int k = 1; k < n; k++){
            this.s.push(k);
        }
        
        for(int k = 0; k < n; k++){
            this.p[k] = this.NIL;
            this.left[k] = this.NIL;
            this.right[k] = this.NIL;
        }
    }
    
    public int allocate_object(){
        int x = this.s.pop();
        if( x == 0 ){
            return 0;
        } else{
            return x;
        }
    }
    
    public void free_object(int x){
        this.s.push(x);
    }
    
    @Override public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("\n");
        for(int k = this.p.length - 1; k >= 0; k--){
            res.append(k);
            res.append("-> [p = ");
            res.append(this.p[k] == 0 ? "nil" : this.p[k] );
            res.append(", key = ");
            res.append(this.key[k]);
            res.append(", left = ");
            res.append(this.left[k] == 0 ? "nil" : this.left[k]);
            res.append(", right = ");
            res.append(this.right[k] == 0 ? "nil" : this.right[k]);
            res.append("]\n");
        }
        return res.toString();
    }
}