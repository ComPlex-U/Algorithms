from time import clock
from random import randrange
import statistics

def bubbleSort(A):
    for i in range(0, len(A)):
        for j in range(len(A)-1, i, -1):
            if A[j] < A[j-1]:
                A[j], A[j-1] = A[j-1], A[j]

def teste(numeroListas, tamanho, iteracoes):
    fMedia = open('BubbleSort_Media_Python.txt','w')
    fDesvio = open('BubbleSort_Desvio_Python.txt','w')
    for z in range(iteracoes):
        tempos = []
        print ("ITERAÇÃO N.º", z + 1)
        for k in range(numeroListas):
            n = []
            for i in range(tamanho):
                num = randrange(1000)
                n.append(num)
            print ("Lista N.º{} de tamanho: {}".format(k + 1,tamanho))
            tI = clock()
            bubbleSort(n)
            tF = clock()
            tempos.append(tF - tI)
            print ("Tempo de organização :: {}s".format(tF - tI))

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

numeroListas = 50
tamanhoLista = 0
iteracoes = 21
teste(numeroListas, tamanhoLista, iteracoes)
