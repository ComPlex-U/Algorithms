/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilha;

public class FilaEspera {
    int length = 0;
    int[] Q = null;
    int tail = 0;
    int head = 0;
    
    public FilaEspera(int n){
        this.length = n - 1;
        this.tail = 0;
        this.head = 0;
        this.Q = new int[this.length];
    }
    
    public void enqueue(int x){
        this.Q[this.tail] = x;
        if ( this.tail == this.length ){
            this.tail = 0;
        } else {
            this.tail = this.tail + 1;
        }
    }
    
    public int dequeue(){
        int x = this.Q[this.head];
        if ( this.head == this.length ){
            this.head = 0;
        } else{
            this.head = this.head + 1;
        }
        return x;
    }    
}
