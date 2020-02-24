package heapsort;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MainHeapSort {
    
    public static void PrintArray(int[] A){
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
                      "C:\\Algoritmos\\Java\\HeapSort.txt"),"utf-8"))) {
            String newline = System.getProperty("line.separator");
            
            int numListas = 50;
            int tamanho = 0;
            int iteracoes = 31;        
            int incremento = 20000;        
            
            for (int z = 1; z <= iteracoes; z++){
                double[] tempos;
                tempos = new double[numListas];
                float somaTempos = 0;
                int contador = 1;     
                
                System.out.println("ITERACAO N. "+ z);           

                for (int k = 0; k < numListas; k++){
                    int[] lista;
                    lista = new int[tamanho+1];

                    Random rand = new Random(); 

                    for (int i = 0; i <= tamanho; ++i) {      
                        lista[i] = rand.nextInt(1000);
                    }
                    /*
                    System.out.println("Lista "+ contador +" de tamanho "+ 
                            tamanho);
                    PrintArray(lista);
                    */
                    long tempoInicial = System.nanoTime(); 
                    HeapSort hs = new HeapSort(lista);
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