package bubblesort;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BubbleSort {

    void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }
 
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    
    public static double calculoDesvio(double tempos[], int tamanho)
    {
        double soma = 0.0, desvioPadrao = 0.0;

        for(double num : tempos) {
            soma += num;
        }

        double media = soma / tamanho;

        for(double num: tempos) {
            desvioPadrao += Math.pow(num - media, 2);
        }

        return Math.sqrt(desvioPadrao / tamanho);
    }
 
    public static void main(String args[]) throws IOException
    {try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(
                      "C:\\Algoritmos\\Java\\BubbleSort\\BubbleSort_Java.txt"),
                "utf-8"))) {
            String newline = System.getProperty("line.separator");
            
            int numListas = 50;
            int tamanho = 0;
            int iteracoes = 31;
			int incremento = 5000;

            for (int z = 1; z <= iteracoes; z++){
                double[] tempos;
                tempos = new double[numListas];
                float somaTempos = 0;
                int contador = 1;            

                for (int k = 0; k < numListas; k++){
        
                    int[] lista;
                    lista = new int[tamanho];

                    Random rand = new Random(); 

                    for (int i = 0; i < tamanho; ++i) {      
                        lista[i] = rand.nextInt(1000);
                    }
                    System.out.println("Lista "+ contador +" de tamanho "+ tamanho);
                    //printArray(lista);
                    BubbleSort ob = new BubbleSort();
                    long tempoInicial = System.nanoTime(); 
                    ob.bubbleSort(lista);
                    long tempoFinal = System.nanoTime() - tempoInicial;

                    /*
                    System.out.println("Lista "+ 1 +" de tamanho "+ tamanho +""
                                        + " organizada");
                    printArray(lista);
                    */
                    // Tempo em MiliSegundos
                    float tempoMS = TimeUnit.NANOSECONDS.toMillis(tempoFinal); 
                    // Tempo em Segundos
                    float tempoS = (float)tempoMS / 1000;  

                    System.out.println("Tempo de execucao :: "+ tempoMS +"ms");
                    System.out.println("Tempo de execucao :: "+ tempoS +"s");
                    System.out.println();
                    somaTempos += tempoS;
                    tempos[k] = tempoS;
                    contador++;
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
