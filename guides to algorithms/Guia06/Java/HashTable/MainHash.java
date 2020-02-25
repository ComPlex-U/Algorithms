package hashtable;

public class MainHash {    
    public static void main(String[] args) {
        final int m = 5;
        final int n = 10;
        HashTable ht = new HashTable(m, n);
        System.out.println(ht);
        for(int key = 0; key < 34; key++){
            ht.insert(key);
        }        
        System.out.println("AFTER");
        System.out.println(ht);
        String res = ht.search(7);
        System.out.println(res);
    }    
}