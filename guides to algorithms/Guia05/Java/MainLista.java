/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilha;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MainLista {
    
    public static double calculoDesvio(double tempos[], int tamanho)
    {
        double soma = 0.0, desvioPadrao = 0.0;
        for(double num : tempos) soma += num;
        double media = soma / tamanho;
        for(double num: tempos) desvioPadrao += Math.pow(num - media, 2);
        return Math.sqrt(desvioPadrao / tamanho);
    }
    
    public static void testePilhas(int numListas, int tam, int it, int inc)
            throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(
                      "C:\\Algoritmos\\Java\\Pilhas.txt"),"utf-8"))) {
            String newline = System.getProperty("line.separator");
            
            System.out.println("\n*** Pilhas ***\n");
            
            for (int z = 1; z <= it; z++){
                double[] tempos;
                tempos = new double[numListas];
                float somaTempos = 0;
                int contador = 1;     
                
                System.out.println("ITERACAO N. "+ z);           

                for (int k = 0; k < numListas; k++){   
                    
                    Random rand = new Random(); 
                    
                    long tempoInicial = System.nanoTime(); 
                    
                    Pilha s = new Pilha(tam);
                    for (int i = 0; i < tam; ++i) { 
                        int num = rand.nextInt(1000);
                        s.push(num);
                    }
                    
                    long tempoFinal = System.nanoTime() - tempoInicial;
                    /*
                    System.out.println("Lista "+ contador +" de tamanho "+ tam);
                    System.out.println(s);                   
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
                double desvio = calculoDesvio(tempos, tam);
                
                System.out.println("Media para "+ numListas +" listas de "+ 
                        tam + " elementos :: " + media + "s");
                System.out.println("Desvio para "+ numListas +" listas de "+
                        tam + " elementos :: " + 
                        desvio + "s");
                System.out.println();
                
                String s = (tam + " " + media + " " + desvio + newline);
                writer.write(s);
                tam += inc;
            }

            writer.close();
            
        }
    }
    
    public static void testeLista(int numListas, int tam, int it, int inc)
            throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(
                      "C:\\Algoritmos\\Java\\Lista.txt"),"utf-8"))) {
            String newline = System.getProperty("line.separator");
            
            System.out.println("\n*** Lista Ligada ***\n");
            
            for (int z = 1; z <= it; z++){
                double[] tempos;
                tempos = new double[numListas];
                float somaTempos = 0;
                int contador = 1;     
                
                System.out.println("ITERACAO N. "+ z);           

                for (int k = 0; k < numListas; k++){   
                    
                    Random rand = new Random(); 
                    
                    long tempoInicial = System.nanoTime(); 
                    
                    ListaLigada lista = new ListaLigada(tam);
                    for (int i = 0; i < tam; ++i) { 
                        int num = rand.nextInt(100);
                        lista.insert(num);
                    }
                    
                    long tempoFinal = System.nanoTime() - tempoInicial;
                    /*
                    System.out.println("Lista "+ contador +" de tamanho "+ tam);
                    System.out.println(lista);                   
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
                double desvio = calculoDesvio(tempos, tam);
                
                System.out.println("Media para "+ numListas +" listas de "+ 
                        tam + " elementos :: " + media + "s");
                System.out.println("Desvio para "+ numListas +" listas de "+
                        tam + " elementos :: " + 
                        desvio + "s");
                System.out.println();
                
                String s = (tam + " " + media + " " + desvio + newline);
                writer.write(s);
                tam += inc;
            }

            writer.close();
            
        }
    }
    
    public static void main(String[] args) throws IOException  {
        /*
        int numListas = 50;
        int tamanho = 0;
        int iteracoes = 31;        
        int incremento = 250000;

        testePilhas(numListas, tamanho, iteracoes, incremento);
        testeLista(numListas, tamanho, iteracoes, incremento);
        */
        FilaEspera f = new FilaEspera(2);
        f.enqueue(22);
        f.enqueue(14);
        //f.enqueue(13);
        int n = f.dequeue();
        System.out.println(n);
        /*        
        Pilha s = new Pilha(10);
        s.push(99);
        s.push(10);
        s.push(23);
        System.out.println(s);
        int x = s.pop();
        System.out.println(x);
        System.out.println(s);
        /*
        ListaLigada lista = new ListaLigada(10);
        
        System.out.println(lista);
        lista.insert(10);
        lista.insert(9);
        lista.insert(88);
        lista.insert(77);
        lista.insert(567);
        System.out.println(lista);
        lista.delete(8);
        System.out.println(lista);
        lista.insert(666);
        System.out.println(lista);

        System.out.println("\n Pesquisa = " + lista.search(88));
        */
    }
}

