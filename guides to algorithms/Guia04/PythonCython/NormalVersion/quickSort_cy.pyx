from time import clock
from random import randrange
import statistics

cdef quickSort(list myList, int start, int end):
    if start < end:
        pivot = partition(myList, start, end)
        quickSort(myList, start, pivot-1)
        quickSort(myList, pivot+1, end)
    return myList

cdef partition(list myList, int start, int end):
    cdef int left
    cdef int right
    cdef int pivot
    cdef int temp
    
    pivot = myList[start]
    left = start + 1
    right = end
    done = False
    while not done:
        while left <= right and myList[left] <= pivot:
            left = left + 1
        while myList[right] >= pivot and right >=left:
            right = right -1
        if right < left:
            done = True
        else:
            temp = myList[left]
            myList[left] = myList[right]
            myList[right] = temp
            
    temp = myList[start]
    myList[start] = myList[right]
    myList[right] = temp
    return right

cpdef teste(int numListas, int tamanho, int iteracoes, int incremento):
   fMedia = open('QuickSort_Media_Cython.txt','w')
   fDesvio = open('QuickSort_Desvio_Cython.txt','w')
   cdef int z = 0
   cdef int k = 0
   cdef int i = 0
   cdef int num
   cdef list tempos = []
   cdef list lista = []

   for z in range(iteracoes):
      print ("ITERACAO N.", z + 1)
      for k in range(numListas):
         for i in range(tamanho):
            num = randrange(1000)
            lista.append(num)

         #print("Lista nao-organizada :: {}".format(lista))
         #print("N.{} de tamanho {}".format(k+1, tamanho))
         tI = clock()
         quickSort(lista, 0, len(lista)-1)
         tF = clock()
         tempos.append(tF - tI)         
         #print("Lista organizada :: {}".format(lista))
         #print ("Tempo de organizacao :: {}s".format(tF-tI))
         del lista[:]
      print ("\nMedia para {1} elementos :: {0}s".format(
                statistics.mean(tempos),tamanho))
      print ("Desvio Padrao para {1} elementos:: {0}s\n".format(
                statistics.stdev(tempos),tamanho))
      sM = '{} {}\n'.format(tamanho, statistics.mean(tempos))
      sD = '{} {}\n'.format(tamanho, statistics.stdev(tempos))
      fMedia.write(sM)
      fDesvio.write(sD)
      tamanho += incremento
      del tempos[:]
   fMedia.close()
   fDesvio.close()
   

