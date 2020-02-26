/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoresredblack;

public class ArvorePesquisaBinaria {
    protected Memoria m = null;
    protected final int NIL = 0;
    protected int root = NIL;
    protected int z = 0;
    
    public ArvorePesquisaBinaria(int n){
        this.m = new Memoria(n);
    }
    
    public int getRoot(){
        return this.root;
    }
    
    private void inorder_tree_walk(int x) {
        if (x == NIL)
            return;
        inorder_tree_walk(this.m.left[x]);
        //System.out.print(this.m.key[x] + " ");
        inorder_tree_walk(this.m.right[x]);       
    }
    
    public void inorder_tree_walk(){ 
        inorder_tree_walk(this.root); 
    }
   
    private int tree_search(int x, int k){
        if (x == NIL || k == this.m.key[x])
            return x;
        if (k < this.m.key[x]) { 
            return tree_search(this.m.left[x], k); 
        }
        else{ 
            return tree_search(this.m.right[x], k); 
        }           
    }    
        
    public int tree_search(int chave){ 
        //System.out.println(tree_search(this.root, chave)); 
        return tree_search(this.root, chave);
    }
    
    private int iterative_tree_search(int x, int k){
        while (x == NIL || k != this.m.key[x]){
            if (k < this.m.key[x]) { 
                x = this.m.left[x];
            }
            else{
                x = this.m.right[x];
            }
        }
        return x;
    }
    
    public int iterative_tree_search(int chave){ 
        //System.out.println(iterative_tree_search(this.root, chave)); 
        return iterative_tree_search(this.root, chave);
    }
    
    public int tree_minimum(int x){
        while (this.m.left[x] != NIL) {
            x = this.m.left[x];
        }
        return x;
    }
    
    public int tree_minimum(){
        //System.out.println(tree_minimum(this.root)); 
        return tree_minimum(this.root);
    }
    
    private int tree_maximum(int x){
        while (this.m.right[x] != NIL) {
            x = this.m.right[x];
        }
        return x;
    }
    
    public int tree_maximum(){
        //System.out.println(tree_maximum(this.root)); 
        return tree_maximum(this.root);
    }
    
    private int tree_successor(int x){
        if (this.m.right[x] != NIL){
            return tree_minimum(this.m.right[x]);
        }
        int y = this.m.p[x];
        while (y != NIL && x == this.m.right[y]){
            x = y;
            y = this.m.p[y];
        }
        return y;
    }
    
    public int tree_successor() {
       //System.out.println(tree_successor(this.root));
       return tree_successor(this.root);
    }
    
    private void transplant(int u, int v){
        if (this.m.p[u] == NIL) {
            this.root = v;
        }
        else if(u == this.m.left[this.m.p[u]]){
            this.m.left[this.m.p[u]] = v;
        }
        else {
            this.m.right[this.m.p[u]] = v;
        }
        if (v != NIL) {
            this.m.p[v] = this.m.p[u];
        }
    }
    
    public void tree_delete(int z){
        if (this.m.left[z] == NIL){
            transplant(z, this.m.right[z]);
        }
        else if (this.m.right[z] == NIL) {
            transplant(z, this.m.left[z]);
        }
        else {     
            int y = tree_minimum(this.m.right[z]);
            if (this.m.p[y] != z){
                transplant(y, this.m.right[y]);
                this.m.right[y] = this.m.right[z];  
                this.m.p[this.m.right[y]] = y;
            }
            transplant(z, y);
            this.m.left[y] = this.m.left[z];
            this.m.p[this.m.left[y]] = y;
        }
    }     
    
    public void insert(int key){
        z = this.m.allocate_object();
        this.m.key[z] = key;
        
        int y = this.NIL;
        int x = this.root;
        while( x != this.NIL ){
            y = x;
            if( this.m.key[z] < this.m.key[x] ){
                x = this.m.left[x];
                } 
            else{
                x = this.m.right[x];
            }    
        }
        
        this.m.p[z] = y;
        if( y == this.NIL ){
            this.root = z;
        }
        else if( this.m.key[z] < this.m.key[y] ){
            this.m.left[y] = z;
            } 
        else {
            this.m.right[y] = z;
        }
    }
    
    
    
    @Override public String toString(){
        String s = this.m.toString() + "root -> "
                + (this.root == 0 ? "nil" : this.root);
        return s;
    }
}
