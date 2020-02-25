from time import clock
from random import randrange
import statistics

# MergeSort Python
 
def mergeSort(lista):
    #print("Split :: ",lista)
    
    if len(lista)>1:
        mid = len(lista) // 2
        metadeEsq = lista[:mid]
        metadeDir = lista[mid:]

        mergeSort(metadeEsq)
        mergeSort(metadeDir)

        i = 0
        j = 0
        k = 0
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
def teste(numeroListas, tamanho):
    tempos = []
    
    for k in range(numeroListas):
        lista = []
        
        for i in range(tamanho):
            num = randrange(1000)
            lista.append(num)
        
        tempo1 = clock()
        mergeSort(lista)        
        tempo2 = clock()
        
        tempos.append(tempo2 - tempo1)

        print ("Lista {} com {} elementos organizada".format(k+1, tamanho))
        print ("Tempo de organização :: {}s\n".format(tempo2 - tempo1))

    print ("Media Python :: {}s ".format(statistics.mean(tempos)))

numeroListas = 10
tamanhoLista = 100
teste(numeroListas, tamanhoLista)
