/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quicksort;

import java.io.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;


class Main {
    
    public static void printArray(int[] A){
        for(int i=1; i < A.length; i++)  System.out.print(A[i] + " ");
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
                      "C:\\Algoritmos\\Java\\QuickSort-Iterativo.txt"),
                "utf-8"))) {
            String newline = System.getProperty("line.separator");
            
            int numListas = 50;
            int tamanho = 0;
            int iteracoes = 31;            
            
            for (int z = 1; z <= iteracoes; z++){
                System.out.println("Iteracao n."+z);
                double[] tempos;
                tempos = new double[numListas];
                float somaTempos = 0;
                int contador = 1;            

                for (int k = 0; k < numListas; k++){
                    int[] lista;
                    lista = new int[tamanho+1];

                    Random rand = new Random(); 

                    for (int i = 0; i <= tamanho; ++i) {      
                        lista[i] = rand.nextInt(1000);
                    }
                    
                    System.out.println("Lista "+ contador +" de tamanho "+ 
                            tamanho);
                    //printArray(lista);

                    long tempoInicial = System.nanoTime(); 
                    QuickSort qs = new QuickSort(lista);
                    //QuickSortIterativo qs = new QuickSortIterativo(lista);
                    long tempoFinal = System.nanoTime() - tempoInicial;
                    /*
                    System.out.format("Lista organizada de %d elementos", tamanho);
                    System.out.println();
                    printArray(lista); 
                    */
                    // Tempo em MiliSegundos                    
                    float tempoMS = TimeUnit.NANOSECONDS.toMillis(tempoFinal); 
                    // Tempo em Segundos
                    float tempoS = (float)tempoMS / 1000; 
                    
                    somaTempos += tempoS;
                    tempos[k] = tempoS;
                    contador++; 

                    System.out.println("Tempo de execucao :: "+ tempoMS +"ms");
                    System.out.println("Tempo de execucao :: "+ tempoS +"s");
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
                tamanho += 10;
            }

            writer.close();
        }
    }
}
