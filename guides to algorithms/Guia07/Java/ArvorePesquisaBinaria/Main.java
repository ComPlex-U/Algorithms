package arvorepesquisabinaria;

import java.io.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    
    public static double calculoDesvio(double tempos[], int tamanho)
    {
        double soma = 0.0, desvioPadrao = 0.0;
        for(double num : tempos) soma += num;
        double media = soma / tamanho;
        for(double num: tempos) desvioPadrao += Math.pow(num - media, 2);
        return Math.sqrt(desvioPadrao / tamanho);
    }
    
        
    public static void main(String[] args) 
            throws IOException {
        
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
              new FileOutputStream(
                      "C:\\Algoritmos\\Java\\ArvoresBinarias.txt"),"utf-8"))) {
            String newline = System.getProperty("line.separator");
        
            int numArvores = 50;
            int tamanho = 50;
            int iteracoes = 30;        
            int incremento = 2000;

            for (int z = 1; z <= iteracoes; z++){
                double[] tempoInsert, tempoTreeWalk, tempoTreeSearch, 
                        tempoITTreeSearch, tempoMin, tempoMax, tempoSuccessor, 
                        tempoDel;
                
                tempoInsert = new double[numArvores];
                tempoTreeWalk = new double[numArvores];
                tempoTreeSearch = new double[numArvores];
                tempoITTreeSearch = new double[numArvores];
                tempoMin = new double[numArvores];
                tempoMax = new double[numArvores];
                tempoSuccessor = new double[numArvores];
                tempoDel = new double[numArvores];
                
                float somaTemposInsert = 0, somaTemposTreeWalk = 0, 
                        somaTemposTreeSearch = 0, somaTemposITTreeSearch = 0,
                        somaTemposMin = 0, somaTemposMax = 0, 
                        somaTemposSuccessor = 0, somaTemposDel = 0;
                
                int contador = 1;     
                int mid = tamanho / 2;
                int numRan = 0;

                System.out.println("ITERACAO N. "+ z);     
                for (int k = 0; k < numArvores; k++){   
                    
                    Random rand = new Random(); 
                    // Criação da árvore
                    ArvorePesquisaBinaria bs = 
                            new ArvorePesquisaBinaria(tamanho);
                    
                    // Contagem de tempo para inserção de números
                    long tempoInicial = System.nanoTime();   
                    for (int i = 0; i < tamanho-1; ++i) { 
                        int num = rand.nextInt(100) + 1;                    
                        bs.insert(num);
                        //System.out.println(num);
                        if(i == mid) numRan = num; 
                    }                    
                    long tempoFinal = System.nanoTime() - tempoInicial;
                    
                    /*
                    System.out.println("Arvore "+contador+" de tamanho "+
                            tamanho);
                    System.out.println(bs);
                    */
                    // Tempo em MiliSegundos                    
                    float tempoMS = TimeUnit.NANOSECONDS.toMillis(tempoFinal); 
                    // Tempo em Segundos
                    float tempoS = (float)tempoMS / 1000; 
                    somaTemposInsert += tempoS;                    
                    tempoInsert[k] = tempoS;                    
                    /*
                    System.out.println("Tempo de execucao :: "+ tempoMS +"ms");
                    System.out.println("Tempo de execucao :: "+ tempoS +"s");
                    System.out.println();                       

                    System.out.println("INORDER TREE WALK");
                    */
                    // Contagem de tempo para Inorder Tree Walk
                    tempoInicial = System.nanoTime();
                    bs.inorder_tree_walk();
                    tempoFinal = System.nanoTime() - tempoInicial;
                    
                    
                    // Tempo em MiliSegundos                    
                    tempoMS = TimeUnit.NANOSECONDS.toMillis(tempoFinal); 
                    // Tempo em Segundos
                    tempoS = (float)tempoMS / 1000; 
                    somaTemposTreeWalk += tempoMS;
                    tempoTreeWalk[k] = tempoMS;
                    /*
                    System.out.println("Tempo de execucao :: "+ tempoMS +"ms");
                    System.out.println("Tempo de execucao :: "+ tempoS +"s");
                    System.out.println();   
                    
                    System.out.println("\nTREE SEARCH"); 
                    */
                    // Contagem de tempo para Tree Search
                    tempoInicial = System.nanoTime();
                    bs.tree_search(numRan);
                    tempoFinal = System.nanoTime() - tempoInicial;
                    
                    
                    // Tempo em MiliSegundos                    
                    tempoMS = TimeUnit.NANOSECONDS.toMillis(tempoFinal); 
                    // Tempo em Segundos
                    tempoS = (float)tempoMS / 1000; 
                    somaTemposTreeSearch += tempoMS;
                    tempoTreeSearch[k] = tempoMS;
                    /*
                    System.out.println("Tempo de execucao :: "+ tempoMS +"ms");
                    System.out.println("Tempo de execucao :: "+ tempoS +"s");
                    System.out.println(); 

                    System.out.println("ITERATIVE TREE SEARCH"); 
                    */
                    // Contagem de tempo para Iterative Tree Search
                    tempoInicial = System.nanoTime();
                    bs.iterative_tree_search(numRan);
                    tempoFinal = System.nanoTime() - tempoInicial;      
                   
                    
                    // Tempo em MiliSegundos                    
                    tempoMS = TimeUnit.NANOSECONDS.toMillis(tempoFinal); 
                    // Tempo em Segundos          
                    tempoS = (float)tempoMS / 1000;           
                    somaTemposITTreeSearch += tempoMS;
                    tempoITTreeSearch[k] = tempoMS;
                    /*
                    System.out.println("Tempo de execucao :: "+ tempoMS +"ms");
                    System.out.println("Tempo de execucao :: "+ tempoS +"s");
                    System.out.println(); 

                    System.out.println("TREE MINIMUM");
                    */
                     // Contagem de tempo para Tree Minimum
                    tempoInicial = System.nanoTime();
                    bs.tree_minimum();
                    tempoFinal = System.nanoTime() - tempoInicial;      
                    
                    
                    // Tempo em MiliSegundos                    
                    tempoMS = TimeUnit.NANOSECONDS.toMillis(tempoFinal); 
                    // Tempo em Segundos          
                    tempoS = (float)tempoMS / 1000;           
                    somaTemposMin += tempoMS;
                    tempoMin[k] = tempoMS;
                    /*
                    System.out.println("Tempo de execucao :: "+ tempoMS +"ms");
                    System.out.println("Tempo de execucao :: "+ tempoS +"s");
                    System.out.println(); 
                    
                    System.out.println("TREE MAXIMUM");
                    */
                     // Contagem de tempo para Tree Maximum
                    tempoInicial = System.nanoTime();
                    bs.tree_maximum();
                    tempoFinal = System.nanoTime() - tempoInicial;      
                    
                    
                    // Tempo em MiliSegundos                    
                    tempoMS = TimeUnit.NANOSECONDS.toMillis(tempoFinal); 
                    // Tempo em Segundos          
                    tempoS = (float)tempoMS / 1000;           
                    somaTemposMax += tempoMS;
                    tempoMax[k] = tempoMS;
                    /*
                    System.out.println("Tempo de execucao :: "+ tempoMS +"ms");
                    System.out.println("Tempo de execucao :: "+ tempoS +"s");
                    System.out.println(); 

                    System.out.println("TREE SUCCESSOR");
                    */
                     // Contagem de tempo para Tree Successor
                    tempoInicial = System.nanoTime();
                    bs.tree_successor();
                    tempoFinal = System.nanoTime() - tempoInicial;      
                    
                    
                    // Tempo em MiliSegundos                    
                    tempoMS = TimeUnit.NANOSECONDS.toMillis(tempoFinal); 
                    // Tempo em Segundos          
                    tempoS = (float)tempoMS / 1000;           
                    somaTemposSuccessor += tempoMS;
                    tempoSuccessor[k] = tempoMS;
                    /*
                    System.out.println("Tempo de execucao :: "+ tempoMS +"ms");
                    System.out.println("Tempo de execucao :: "+ tempoS +"s");
                    System.out.println(); 

                    System.out.println("TREE DELETE");
                    */
                     // Contagem de tempo para Tree Delete
                    tempoInicial = System.nanoTime();
                    bs.tree_delete(bs.getRoot());
                    tempoFinal = System.nanoTime() - tempoInicial;  
                    //System.out.println(bs);                    
                    
                    // Tempo em MiliSegundos                    
                    tempoMS = TimeUnit.NANOSECONDS.toMillis(tempoFinal); 
                    // Tempo em Segundos          
                    tempoS = (float)tempoMS / 1000;           
                    somaTemposSuccessor += tempoMS;
                    tempoSuccessor[k] = tempoMS;
                    /*
                    System.out.println("Tempo de execucao :: "+ tempoMS +"ms");
                    System.out.println("Tempo de execucao :: "+ tempoS +"s");
                    System.out.println(); 
                    */
                    contador++; 
                }                
                float mediaInsert = somaTemposInsert / numArvores;
                float mediaTreeWalk = somaTemposTreeWalk / numArvores;
                float mediaTreeSearch = somaTemposTreeSearch / numArvores;
                float mediaITTreeSearch = somaTemposITTreeSearch / numArvores;
                float mediaMin = somaTemposMin / numArvores;
                float mediaMax = somaTemposMax / numArvores;
                float mediaSuccessor = somaTemposSuccessor / numArvores;
                float mediaDel = somaTemposDel / numArvores;
                                
                double desvioInsert = calculoDesvio(tempoInsert, tamanho);   
                double desvioTreeWalk = calculoDesvio(tempoTreeWalk, tamanho);   
                double desvioTreeSearch = calculoDesvio(tempoTreeSearch, 
                        tamanho);
                double desvioITTreeSearch = calculoDesvio(tempoITTreeSearch, 
                        tamanho);
                double desvioMin = calculoDesvio(tempoMin, tamanho);   
                double desvioMax = calculoDesvio(tempoMax, tamanho);   
                double desvioSuccessor = calculoDesvio(tempoSuccessor, tamanho);   
                double desvioDel = calculoDesvio(tempoDel, tamanho);   

                System.out.println("Medias para "+ numArvores +" listas de "+ 
                        tamanho + " elementos :: \n "+
                        "Média de Inserção - "+ mediaInsert + "s\n"+
                        "Média de Inorder Tree Walk - "+ mediaTreeWalk + "ms\n"+
                        "Média de Tree Search - "+ mediaTreeSearch + "ms\n"+
                        "Média de IT Tree Search- "+ mediaITTreeSearch + "ms\n"+
                        "Média de Get Minimum- "+ mediaMin + "ms\n"+
                        "Média de Get Maximum- "+ mediaMax + "ms\n"+
                        "Média de Get Successor- "+ mediaSuccessor + "ms\n"+
                        "Média de Delete - "+ mediaDel + "ms\n");
                /*
                System.out.println("Desvios para "+ numArvores +" listas de "+
                        tamanho + " elementos :: " + 
                        desvioInsert + "s");
                */
                System.out.println();

                tamanho += incremento;
                String s = (tamanho +" "+ mediaInsert +" "+ mediaTreeWalk +" "
                        + mediaTreeSearch +" "+ mediaITTreeSearch +" "
                        + mediaMin +" "+ mediaMax +" "+ mediaSuccessor +" "
                        + mediaDel +" "+ desvioInsert +" "+ desvioTreeWalk +" "
                        + desvioTreeSearch +" "+ desvioITTreeSearch +" "
                        + desvioMin +" "+ desvioMax +" "+ desvioSuccessor +" "
                        + desvioDel +" "+ newline);
                writer.write(s);
                tamanho += incremento;
            }
            writer.close();
            
        }
    
        
        /*
        final int n = 10;
        int key = 0;
        ArvorePesquisaBinaria bs = 
                new ArvorePesquisaBinaria(n);
        System.out.println(bs);
        key = 12; bs.insert(key);
        key = 5; bs.insert(key);
        key = 18; bs.insert(key);
        key = 2; bs.insert(key);
        key = 9; bs.insert(key);
        key = 15; bs.insert(key);
        key = 19; bs.insert(key);
        key = 13; bs.insert(key);
        key = 17; bs.insert(key);
        
        System.out.println();
        System.out.println("AFTER INSERT");
        System.out.println(bs);
        
        
        System.out.println("INORDER TREE WALK");
        bs.inorder_tree_walk();
        
        System.out.println("\nTREE SEARCH");        
        bs.tree_search(15);
        
        System.out.println("ITERATIVE TREE SEARCH");        
        bs.iterative_tree_search(15);
        
        System.out.println("TREE MINIMUM");
        bs.tree_minimum();
        System.out.println("TREE MAXIMUM");
        bs.tree_maximum();
        
        System.out.println("TREE SUCCESSOR");
        bs.tree_successor();
        
        bs.tree_delete(9);
        System.out.println("AFTER DELETE");
        System.out.println(bs);
        
        System.out.println("INORDER TREE WALK");
        bs.inorder_tree_walk();
        
        System.out.println("\nTREE SEARCH");        
        bs.tree_search(15);
        
        System.out.println("ITERATIVE TREE SEARCH");        
        bs.tree_search(15);
        
        System.out.println("TREE MINIMUM");
        bs.tree_minimum();
        System.out.println("TREE MAXIMUM");
        bs.tree_maximum();
        
        System.out.println("TREE SUCCESSOR");
        bs.tree_successor();
        
        bs.tree_delete(2);
        System.out.println("AFTER DELETE");
        System.out.println(bs);
        
        System.out.println("TREE SUCCESSOR");
        bs.tree_successor();
        */
    }
}
