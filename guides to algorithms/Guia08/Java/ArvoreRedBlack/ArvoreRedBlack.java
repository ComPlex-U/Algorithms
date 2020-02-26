/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoresredblack;

/**
 *
 * @author Boris
 */
public class ArvoreRedBlack extends ArvorePesquisaBinaria {
    int[] color = null;
    final int BLACK = 0;
    final int RED = 1;
    int[] right = null;
    int[] left = null;
    int[] p = null;
    
    public ArvoreRedBlack(int n){
        super(n);
        color = new int[n];
        right = this.m.right;
        left = this.m.left;
        p = this.m.p;
    }
    
    public void left_rotate(int x){
        int y = right[x];
        right[x] = left[y];
        if( left[y] != NIL ){
            p[left[y]] = x;
        }
        
        p[y] = p[x];
        if(p[x] == NIL)
            root = y;
        else if( x == p[left[x]] )
            p[left[x]] = y;
        else
            right[p[x]] = y;
        
        left[y] = x;
        p[x] = y;
    }
    
    public void right_rotate(int x){
        int y = left[x];
        left[x] = right[y];
        if( right[y] != NIL ){
            p[right[y]] = x;
        }
        
        p[y] = p[x];
        if(p[x] == NIL)
            root = y;
        else if( x == p[right[x]] )
            p[right[x]] = y;
        else
            left[p[x]] = y;
        
        right[y] = x;
        p[x] = y;
    }
    
    public void insert_fixup(int z){
        int y = 0;
        while( color[p[z]] == RED ){
            if( p[z] == left[p[p[z]]] ){
                y = right[p[p[z]]];
                
                if( color[y] == RED ){
                    color[p[z]] = BLACK;
                    color[y] = BLACK;
                    color[p[p[z]]] = RED;
                    z = p[p[z]];
                }
                else{
                    if( z == right[p[z]] ){
                        z = p[z];
                        left_rotate(z);
                    }
                    color[p[z]] = BLACK;
                    color[p[p[z]]] = RED;
                    right_rotate( p[p[z]] );
                }
            }
            else{
                y = left[p[p[z]]];
                if( color[y] == RED ){
                    color[p[z]] = BLACK;
                    color[y] = BLACK;
                    color[p[p[z]]] = RED;
                    z = p[p[z]];
                }
                else{
                    if( z == left[p[z]] ){
                        z = p[z];
                        right_rotate(z);
                    }
                    color[p[z]] = BLACK;
                    color[p[p[z]]] = RED;
                    left_rotate( p[p[z]] );
                }
            }
        } /* while */
        
        color[root] = BLACK;
    }
    
    public void insert(int key){
        super.insert(key);
        color[z] = RED;
        System.out.print("z = ");
        System.out.println(z);
        insert_fixup(z);
    }
    
    public void rb_transplant(int u, int v){
        if (this.m.p[u] == NIL) {
            this.root = v;
        }
        else if(u == this.m.left[this.m.p[u]]){
            this.m.left[this.m.p[u]] = v;
        }
        else {
            this.m.right[this.m.p[u]] = v;
        }        
        this.m.p[v] = this.m.p[u];        
    }
    
    public void rb_tree_delete(int z){
        int x;
        int y = z;
        int y_original_color = color[y];
        
        if (this.m.left[z] == NIL){
            x = this.m.right[z];
            rb_transplant(z, this.m.right[z]);
        }
        else if (this.m.right[z] == NIL) {
            x = this.m.left[z];
            rb_transplant(z, this.m.left[z]);
        }
        else {     
            y = super.tree_minimum(this.m.right[z]);
            y_original_color = color[y];
            x = this.m.right[y];
            if (this.m.p[y] != z){
                rb_transplant(y, this.m.right[y]);
                this.m.right[y] = this.m.right[z];  
                this.m.p[this.m.right[y]] = y;
            }
            rb_transplant(z, y);
            this.m.left[y] = this.m.left[z];
            this.m.p[this.m.left[y]] = y;
            color[y] = color[z];
        }
        if( y_original_color == BLACK ) {
            rb_delete_fixup(x);
        }
    }  
    
    public void rb_delete_fixup(int x){
        int w;
        while(x != this.root && color[x] == BLACK){
            if (x == this.m.left[this.m.p[x]]){
                w = this.m.right[this.m.p[x]];
                if (color[w] == RED) {
                    color[w] = BLACK;
                    color[this.m.p[x]] = RED;
                    left_rotate(this.m.p[x]);
                    w = this.m.right[this.m.p[x]];
                }
                if (color[this.m.left[w]] == BLACK &&
                        color[this.m.right[w]] == BLACK){
                    color[w] = RED;
                    x = this.m.p[x];
                }
                else if (color[this.m.right[w]] == BLACK){
                    color[this.m.left[w]] = BLACK;
                    color[w] = RED;
                    right_rotate(w);
                    w = this.m.right[this.m.p[x]];
                }
                color[w] = color[this.m.p[x]];
                color[this.m.p[x]] = BLACK;
                color[this.m.right[w]] = BLACK;
                left_rotate(this.m.p[x]);
                x = this.root;
            }
            else{
                w = this.m.right[this.m.p[x]];
                if (color[w] == RED) {
                    color[w] = BLACK;
                    color[this.m.p[x]] = RED;
                    right_rotate(this.m.p[x]);
                    w = this.m.right[this.m.p[x]];
                }
                if (color[this.m.left[w]] == BLACK &&
                        color[this.m.right[w]] == BLACK){
                    color[w] = RED;
                    x = this.m.p[x];
                }
                else if (color[this.m.right[w]] == BLACK){
                    color[this.m.left[w]] = BLACK;
                    color[w] = RED;
                    left_rotate(w);
                    w = this.m.right[this.m.p[x]];
                }
                color[w] = color[this.m.p[x]];
                color[this.m.p[x]] = BLACK;
                color[this.m.right[w]] = BLACK;
                right_rotate(this.m.p[x]);
                x = this.root;
            }
        }
        color[x] = BLACK;
    }

    @Override public String toString(){
        String s = super.toString();
        StringBuilder res = new StringBuilder();
        res.append("\n");
        for(int k = color.length-1; k >=0; k--){
            res.append(k);
            res.append("-> color = ");
            res.append(color[k] == 0 ? "BLACK" : "RED" );
            res.append("\n");
        }
        return s + res;
    }
}