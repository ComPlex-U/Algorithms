/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoresredblack;

public class Pilha {
    int n = 0;
    int top = -1;
    int[] S = null;
    public boolean stack_empty(){
        if(this.top == -1) return true;
        else return false;
    }
    
    public void push(int x){
        this.top = this.top + 1;
        this.S[this.top] = x;
    }
        
    public int pop(){
        if(this.stack_empty()){
            System.out.println("underflow");
            return 0;
        } else{
            this.top = this.top - 1;
            return this.S[this.top+1];
        }
    }
    
    public Pilha(int n){
        this.n = n;
        this.top = -1;
        this.S = new int[this.n];
    }
    
    @Override public String toString(){
        StringBuilder res = new StringBuilder();
        for(int x: this.S){
            res.append(x);
            res.append(" ");
        }
        return res.toString();
    }
}
