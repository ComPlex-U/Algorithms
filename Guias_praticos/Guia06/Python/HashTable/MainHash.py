from time import clock
from random import randrange
import statistics
from HashTable import HashTable

def main():
    m = 5
    n = 10
    ht = HashTable(m, n)
    print(ht.toString()+"\n")
    print("INSERT\n----")
    for key in range(0, 34):
        ht.insert(key)
    print("\nAFTER INSERT\n----")
    print(ht.toString())
    print("\nSEARCH")
    res = ht.search(7)
    print(res)

#main()

def teste(numListas, tamanho, iteracoes, incremento):
    fMedia = open('HashTable-Media-Python.txt','w')
    fDesvio = open('HashTable-Desvio-Python.txt','w')

    for z in range(iteracoes):
        print ("----\nITERACAO N.", z + 1)
        tempos_insert = []
        tempos_search = []
        for k in range(numListas):
            nums = []
            pos = randrange(1, tamanho)
            for i in range(tamanho):
                nums.append(randrange(500) + 1)
                
            ht = HashTable(numListas, tamanho)
            tI = clock()
            for i in range(tamanho):
                #num = randrange(1000)
                ht.insert(nums[i])
            tF = clock()
            #print("INSERT\n----")
            #print(ht.toString()+"\n")
            #print("\nAFTER INSERT\n----")
            #print(ht.toString())
            tempos_insert.append(tF - tI)

            for t in range(iteracoes):
                tI = clock()
                ht.search(nums[pos])
                tF = clock()
                tempos_search.append(tF - tI)

            
        print ("\nMedias para {} elementos ::\n\tInsert - {}s\n\t"
               "Search - {}s".format(tamanho,
                                     statistics.mean(tempos_insert),
                                     statistics.mean(tempos_search)))
        print ("Desvio Padrao para {} elementos ::\n\tInsert - {}s\n\t"
               "Search - {}s".format(tamanho,
                                     statistics.stdev(tempos_insert),
                                     statistics.stdev(tempos_search)))
        sM = '{} {} {}\n'.format(tamanho,
                                 statistics.mean(tempos_insert),
                                 statistics.mean(tempos_search))
        sD = '{} {} {}\n'.format(tamanho,
                                 statistics.stdev(tempos_insert),
                                 statistics.stdev(tempos_search))
        fMedia.write(sM)
        fDesvio.write(sD)
        tamanho += incremento
        
    fMedia.close()
    fDesvio.close()

numeroListas = 50
tamanho = 25
iteracoes = 30
incremento = 125
teste(numeroListas, tamanho, iteracoes, incremento)
