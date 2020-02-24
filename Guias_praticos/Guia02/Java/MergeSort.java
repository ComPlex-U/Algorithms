package mergesort;
import java.io.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

class MergeSort
{
    public void Merge(int[] A, int p, int q, int r){
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1+1];
        int[] R = new int[n2+1];
        for(int i = 0; i < n1; i++) L[i] = A[p + i];
        for(int j = 0; j < n2; j++) R[j] = A[q + j + 1];
        L[n1] = Integer.MAX_VALUE; R[n2] = Integer.MAX_VALUE;
        int i = 0, j = 0;
        for(int k = p; k <= r; k++)
            if(L[i] <= R[j])
                A[k] = L[i++];
            else
                A[k] = R[j++];
     }
    
    public void run(int[] A, int p, int r){
        if(p < r){
            int q = (int) Math.floor((p+r) / 2.0);
            run(A, p, q); run(A, q+1, r); Merge(A, p, q, r);
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
    {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(
                      "C:\\Algoritmos\\Java\\MergeSort\\MergeSort_Java.txt"),
                "utf-8"))) {
            String newline = System.getProperty("line.separator");
            
            int numListas = 50;
            int tamanho = 0;
            int iteracoes = 31;
			int incremento = 25000;

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

                    System.out.println("Lista "+ contador +" de tamanho "
                            + tamanho);
                    //printArray(lista);            

                    MergeSort ob = new MergeSort();
                    long tempoInicial = System.nanoTime(); 
                    ob.run(lista, 0, lista.length-1);
                    long tempoFinal = System.nanoTime() - tempoInicial;

                    /*
                    System.out.println("Lista "+ k +" de tamanho "+ tamanho +
                                        " organizada");
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