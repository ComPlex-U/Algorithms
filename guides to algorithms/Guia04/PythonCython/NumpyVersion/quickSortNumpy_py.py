from time import clock
from random import randrange
import statistics
import numpy as np

def troca(dados, i, j):
    dados[i], dados[j] = dados[j], dados[i]

def sorter(dados, esq, drt):

    if esq >= drt:
        return

    i = np.random.randint(esq, drt + 1)
    troca(dados, esq, i)
    pivot = dados[esq]

    i, j, k = esq, drt, esq + 1

    while k <= j:
        if dados[k] < pivot:
            troca(dados, i, k)
            i += 1
        elif dados[k] > pivot:
            troca(dados, j, k)
            j -= 1
            k -= 1
        k += 1

    sorter(dados, esq, i - 1)
    sorter(dados, j + 1, drt)

def qsort(A):
    sorter(A, 0, len(A) - 1)

def teste(numListas, tamanho, iteracoes, incremento):
   fMedia = open('QuickSort_Media_Python-Numpy.txt','w')
   fDesvio = open('QuickSort_Desvio_Python-Numpy.txt','w')

   for z in range(iteracoes):
      print ("ITERACAO N.", z + 1)
      tempos = []
      for k in range(numListas):
          lista = np.random.randint(0, 1000, tamanho)

          #print("Lista nao-organizada :: {}".format(lista))
          #print("N.{} de tamanho {}".format(k+1, tamanho))

          tI = clock()
          qsort(lista)
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
   
teste(50, 0, 31, 400)
