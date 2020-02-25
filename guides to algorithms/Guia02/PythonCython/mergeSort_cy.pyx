from time import clock
from random import randrange
import statistics

# MergeSort Cython
cdef mergeSort(list lista):
    #print("Split :: ",lista)
    cdef int mid
    cdef list metadeEsq
    cdef list metadeDir
    cdef int i = 0
    cdef int j = 0
    cdef int k = 0
    
    if len(lista) > 1:
        mid = len(lista) // 2
        metadeEsq = lista[:mid]
        metadeDir = lista[mid:]

        mergeSort(metadeEsq)
        mergeSort(metadeDir)

        while i < len(metadeEsq) and j < len(metadeDir):
            if metadeEsq[i] < metadeDir[j]:
                lista[k] = metadeEsq[i]
                i += 1
            else:
                lista[k] = metadeDir[j]
                j += 1
            k += 1

        while i < len(metadeEsq):
            lista[k] = metadeEsq[i]
            i += 1
            k += 1

        while j < len(metadeDir):
            lista[k] = metadeDir[j]
            j += 1
            k += 1
            
    #print("Merge :: ",lista)

# Código para testar
cpdef teste(int numeroListas, int tamanho, int iteracoes):
    fMedia = open('MergeSort_Media_Cython.txt','w')
    fDesvio = open('MergeSort_Desvio_Cython.txt','w')
    
    cdef int z
    cdef int k
    cdef int i
    cdef int num
    cdef list tempos = []
    cdef list lista = []
    
    for z in range(iteracoes):
        print ("ITERAÇÃO N.º", z + 1)
         
        for k in range(numeroListas):            
            for i in range(tamanho):
                num = randrange(1000)
                lista.append(num)

            #print ("Array {} não-organizada :: {}".format(k+1, lista))
            print ("Lista N.º{} de tamanho: {}".format(k + 1,tamanho))
            
            tempo1 = clock()
            mergeSort(lista)        
            tempo2 = clock()
            
            tempos.append(tempo2 - tempo1)

            #print ("Array {} organizada :: {}".format(k+1, lista))
            print ("Tempo de organização :: {}s\n".format(tempo2 - tempo1))
            del lista[:]


        print ("\nMedia para {1} elementos :: {0}s".format(
                statistics.mean(tempos),tamanho))
        print ("Desvio Padrão para {1} elementos:: {0}s\n".format(
                statistics.stdev(tempos),tamanho))
        sM = '{} {}\n'.format(tamanho, statistics.mean(tempos))
        sD = '{} {}\n'.format(tamanho, statistics.stdev(tempos))
        fMedia.write(sM)
        fDesvio.write(sD)
        tamanho += 50
    fMedia.close()
    fDesvio.close()

#numeroArrays = 10
#tamanhoLista = 100
#teste(numeroArrays, tamanhoLista)
