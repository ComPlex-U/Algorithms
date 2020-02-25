/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibo;
import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * Programa Java para calcular e mostrar os números Fibonacci através
 * do método recursivo e do método iterativo
 */
public class Fibo {
    
    public static void main(String args[]) throws IOException {    
        int numero = 45;
      
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream("c:\\CythonTests\\fiboRec_Java.txt"), "utf-8"))) {
             
            String newline = System.getProperty("line.separator");
            
            for(int i = 1; i <= numero; i++){
				// Medição do tempo de execução para Fibonacci Iterativo
                long tempoInicial = System.nanoTime();
                double fibIT = fiboIt(i);
                long tempoFinal = System.nanoTime() - tempoInicial;
                long duracaoIT = TimeUnit.NANOSECONDS.toMillis(tempoFinal); 
                long tempoIT = tempoFinal / 1000;
				
				// Medição do tempo de execução para Fibonacci Recursivo
                long tempoInicial = System.nanoTime();
                double fibRE = fiboRec(i);
                long tempoFinal = System.nanoTime() - tempoInicial;
                long duracaoRE = TimeUnit.NANOSECONDS.toMillis(tempoFinal); 
                long tempoRE = tempoFinal / 1000;
                String s = (i + " " + duracaoIT + duracaoRE + newline);
                writer.write(s);
                System.out.println(i + " :: " + fib);
                System.out.println("Tempo de execucao Iterativo :: " + tempoIT + "ms");
                System.out.println("Tempo de execucao Iterativo :: " + duracaoIT + "s");
                System.out.println("Tempo de execucao Recursivo :: " + tempoRE + "ms");
                System.out.println("Tempo de execucao Recursivo :: " + duracaoRE + "s");
            }         
            writer.close();
            
        }
    } 
  

    /*
     * Sequência Fibonacci de modo Recursivo
     */
    public static double fiboRec(double numero){
        if(numero == 0){
            return 0;
        }
        else if(numero == 1 || numero == 2){
            return 1;
        }
      
        return fiboRec(numero-1) + fiboRec(numero -2);
    }

    /*
     * Sequência Fibonacci de modo Iterativo
     */
    public static double fiboIt(double numero){
        if(numero == 0){
            return 0;
        }
        else if(numero == 1 || numero == 2){
            return 1;
        }
        double fibo1=1, fibo2=1, fibonacci=1;
        for(int i= 3; i<= numero; i++){
           
            fibonacci = fibo1 + fibo2;             
            fibo1 = fibo2;
            fibo2 = fibonacci;
          
        }
        return fibonacci;      
    }     
}