from time import clock
from random import randrange
import statistics

def qsort(A):
    if A == []: return []
    return qsort([x for x in A[1:] if x < A[0]]) + A[0:1] + \
           qsort([x for x in A[1:] if x >= A[0]])

def teste(numListas, tamanho, iteracoes, incremento):
   fMedia = open('QuickSort_Media_Python.txt','w')
   fDesvio = open('QuickSort_Desvio_Python.txt','w')

   for z in range(iteracoes):
      print ("ITERACAO N.", z + 1)
      tempos = []
      for k in range(numListas):
         lista = []
         for i in range(tamanho):
            num = randrange(1000)
            lista.append(num)

         #print("Lista nao-organizada :: {}".format(lista))
         #print("N.{} de tamanho {}".format(k+1, tamanho))
         tI = clock()
         lista = qsort(lista)
         tF = clock()

         tempos.append(tF - tI)
         
         #print("Lista organizada :: {}".format(lista))
         #print ("Tempo de organizacao :: {}s".format(tF-tI))
         
      print ("\nMedia para {1} elementos :: {0}s".format(
                statistics.mean(tempos),tamanho))
      print ("Desvio Padrao para {1} elementos:: {0}s\n".format(
                statistics.stdev(tempos),tamanho))
      sM = '{} {}\n'.format(tamanho, statistics.mean(tempos))
      sD = '{} {}\n'.format(tamanho, statistics.stdev(tempos))
      fMedia.write(sM)
      fDesvio.write(sD)
      tamanho += incremento
      
   fMedia.close()
   fDesvio.close()
   
teste(50, 0, 31, 900)
