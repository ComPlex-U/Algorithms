package hashtable;

public class HashTable {

    ListaLigada[] T = null;
    int m = 0;
    int n = 0;
    
    private int hash_function_division(int k){
        return k % this.m;
    }
    
    private int hash_function_mult(int k){
        final double A = (Math.sqrt(5) - 1) / 2.0;
        return (int) Math.floor(this.m * ((k * A) % 1));
    }
    
    public HashTable(int m, int n){
        this.m = m;
        this.n = n;
        this.T = new ListaLigada[m];
        for(int k = 0; k < m; k++){
            this.T[k] = new ListaLigada(n);
        }
    }
    
    public void insert(int key){
        int h = this.hash_function_mult(key);
        System.out.println("chave = " + key + " hash = " + h);
        this.T[h].insert(key);
    }
    
    public String search(int k){
        int h = this.hash_function_mult(k);
        int x = this.T[h].search(k);
        StringBuilder res = new StringBuilder();
        res.append("----\nh = ");
        res.append(h);
        res.append(" x = ");
        res.append(x);
        return res.toString();
    }
    
    @Override public String toString(){
        String s = "";
        int k = 0;
        for(ListaLigada l : this.T){
            s += "\nLista = " + k;
            s += l;
            k++;
        }
        return s;
    }        
}