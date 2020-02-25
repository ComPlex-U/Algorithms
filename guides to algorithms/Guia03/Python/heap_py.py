from time import clock
from random import randrange
import statistics

def heapify(lista, n, i):
    maior = i
    esq = 2 * i + 1
    drt = 2 * i + 2

    if esq < n and lista[i] < lista[esq]:
        maior = esq

    if drt < n and lista[maior] < lista[drt]:
        maior = drt
 
    if maior != i:
        lista[i] ,lista[maior] = lista[maior], lista[i]

        heapify(lista, n, maior)
 
def heapSort(lista):
    n = len(lista)

    for i in range(n, -1, -1):
        heapify(lista, n, i)
 
    for i in range(n-1, 0, -1):
        lista[i], lista[0] = lista[0], lista[i] 
        heapify(lista, i, 0)
 
def teste(numListas, tamanho, iteracoes, incremento):
    fMedia = open('HeapSort_Media_Python.txt','w')
    fDesvio = open('HeapSort_Desvio_Python.txt','w')

    for z in range(iteracoes):
        print ("ITERACAO N.", z + 1)
        tempos = []
        for k in range(numListas):
            lista = []
            for i in range(tamanho):
                num = randrange(1000)
                lista.append(num)

            #print("Lista nao-organizada :: {}".format(lista))
            print("N.{} de tamanho {}".format(k+1, tamanho))
            tI = clock()
            heapSort(lista)
            tF = clock()
            tempos.append(tF - tI)
            #print("Lista organizada :: {}".format(lista))
            print ("Tempo de organizacao :: {}s".format(tF-tI))
            
        print ("\nMedia para {1} elementos :: {0}s".format(
            statistics.mean(tempos), tamanho))
        print ("Desvio Padrao para {1} elementos:: {0}s\n".format(
            statistics.stdev(tempos), tamanho))
        sM = '{} {}\n'.format(tamanho, statistics.mean(tempos))
        sD = '{} {}\n'.format(tamanho, statistics.stdev(tempos))
        fMedia.write(sM)
        fDesvio.write(sD)
        tamanho += incremento
        
    fMedia.close()
    fDesvio.close()

numeroListas = 50
tamanho = 0
iteracoes = 31
incremento = 500
teste(numeroListas, tamanho, iteracoes, incremento)
