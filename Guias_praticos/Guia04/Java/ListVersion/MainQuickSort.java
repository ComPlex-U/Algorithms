/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksortlist;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MainQuickSort {
    
    public static void PrintArray(ArrayList<Integer> A){
        for(int i=1; i < A.size(); i++)  System.out.print(A.get(i) + " ");
        System.out.println();
    }
    
    public static double calculoDesvio(double tempos[], int tamanho)
    {
        double soma = 0.0, desvioPadrao = 0.0;
        for(double num : tempos) soma += num;
        double media = soma / tamanho;
        for(double num: tempos) desvioPadrao += Math.pow(num - media, 2);
        return Math.sqrt(desvioPadrao / tamanho);
    }
    
    public static void main(String[] args) throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(
                      "C:\\Algoritmos\\Java\\QuickSort_List.txt"),"utf-8"))) {
            String newline = System.getProperty("line.separator");
            
            int numListas = 50;
            int tamanho = 0;
            int iteracoes = 31;        
            int incremento = 2000;
            
            for (int z = 1; z <= iteracoes; z++){
                double[] tempos;
                tempos = new double[numListas];
                
                float somaTempos = 0;
                int contador = 1;    
                System.out.println("ITERACAO N. "+ z);        

                for (int k = 0; k < numListas; k++){
                    ArrayList<Integer> lista = new ArrayList<>(tamanho);

                    Random rand = new Random(); 

                    for (int i = 0; i <= tamanho; ++i) {      
                        lista.add(rand.nextInt(50));
                    }
                    /*
                    System.out.println("Lista "+ contador +" de tamanho "+ 
                            tamanho);
                    PrintArray(lista);
                    */
                    long tempoInicial = System.nanoTime(); 
                    QuickSortList qsl = new QuickSortList(lista);
                    long tempoFinal = System.nanoTime() - tempoInicial;
                    /*
                    System.out.format("Lista organizada de %d elementos", tamanho);
                    System.out.println();
                    PrintArray(lista); 
                    */
                    // Tempo em MiliSegundos                    
                    float tempoMS = TimeUnit.NANOSECONDS.toMillis(tempoFinal); 
                    // Tempo em Segundos
                    float tempoS = (float)tempoMS / 1000; 
                    
                    somaTempos += tempoS;
                    tempos[k] = tempoS;
                    contador++; 
                    /*
                    System.out.println("Tempo de execucao :: "+ tempoMS +"ms");
                    System.out.println("Tempo de execucao :: "+ tempoS +"s");
                    System.out.println();  
                    */
                }
                
                float media = somaTempos / numListas;
                double desvio = calculoDesvio(tempos, tamanho);
                
                System.out.println("Media para "+ numListas +" listas de "+ 
                        tamanho + " elementos :: " + media + "ms");
                System.out.println("Desvio para "+ numListas +" listas de "+
                        tamanho + " elementos :: " + 
                        desvio + "ms");
                System.out.println();
                
                String s = (tamanho + " " + media + " " + desvio + newline);
                writer.write(s);
                tamanho += incremento;
            }

            writer.close();
        }
    }
}