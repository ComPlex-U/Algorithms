package hashtable;

public class MemoriaLL {
    public int[] next = null;
    public int[] key = null;
    public int[] prev = null;
    
    Pilha s = null;
    
    public MemoriaLL(int n){
        this.next = new int[n];
        this.key = new int[n];
        this.prev = new int[n];
        this.s = new Pilha(n);
        for(int k = 1; k < n; k++){
            this.s.push(k);
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
        for(int k = 0; k < this.next.length; k++){
            res.append(k);
            res.append("-> [");
            res.append(this.prev[k]);
            res.append(", ");
            res.append(this.key[k]);
            res.append(", ");
            res.append(this.next[k]);
            res.append("]\n");
        }
        return res.toString();
    }
}