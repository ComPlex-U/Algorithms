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
public class Main {
    public static void main(String[] args) {
        final int n = 10;
        int key = 0;
        
        ArvoreRedBlack bs = new ArvoreRedBlack(n);
        System.out.println("ARVORE RED BLACK");
        System.out.println(bs);
        
        System.out.println("INSERT\n----");
        
        key = 12; bs.insert(key);
        key = 5; bs.insert(key);
        key = 18; bs.insert(key);
        key = 2; bs.insert(key);
        key = 9; bs.insert(key);
        key = 15; bs.insert(key);
        key = 19; bs.insert(key);
        key = 13; bs.insert(key);
        key = 17; bs.insert(key);
        
        System.out.println("\nAFTER INSERT\n----");
        System.out.println(bs);
        int r = bs.getRoot();
        bs.rb_tree_delete(r);
        System.out.println("AFTER DELETE\n----");
        System.out.println(bs);
    }    
}
