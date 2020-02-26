from ArvorePesquisaBinaria import ArvorePesquisaBinaria
from time import clock
from random import randrange
import statistics

def teste(numArv, tam, itr, inc):
        fMedia = open('Arvore-Media.txt','w')
        fDesvio = open('Arvore-Desvio.txt','w')

        for z in range(itr):
                print ("\n### ITERACAO N.", z + 1," ###")
                temposInsert = []
                temposTreeWalk = []
                temposTreeSearch = []
                temposTreeSearchIT = []
                temposDelete = []
                for k in range(numArv):
                        arvore = ArvorePesquisaBinaria(tam)
                        pos = randrange(1, tam-1)
                        nums = []
                        for i in range(tam-1):
                                nums.append(randrange(500) + 1)
                        #print(nums)

                        #
                        # INSERT
                        #
                        tI = clock()
                        for i in range(tam-1):
                                arvore.insert(nums[i])
                        tF = clock()                        
                        temposInsert.append(tF - tI)
                        
                        #print("Arvore N.{} de tamanho {}".format(k+1, tam))
                        #print("{}".format(arvore.toString()))
                        #print ("Tempo de inserção :: {}s".format(tF-tI))

                        #
                        # INORDER TREE WALK
                        #
                        tI = clock()
                        arvore.inorder_tree_walkEXT()
                        tF = clock()      
                        temposTreeWalk.append(tF - tI)
                        
                        #print ("Tempo de TreeWalk :: {}s".format(tF-tI))
                        
                        #
                        # TREE SEARCH
                        #
                        for arv1 in range(itr):
                                tI = clock()
                                arvore.tree_searchEXT(nums[pos])
                                tF = clock()
                                temposTreeSearch.append(tF - tI)    

                        #
                        # ITERATIVE TREE SEARCH
                        #
                        for arv2 in range(itr):
                                tI = clock()
                                arvore.iterative_tree_searchEXT(nums[pos])
                                tF = clock()
                                temposTreeSearchIT.append(tF - tI)
                        
                        #
                        # DELETE
                        #
                        tI = clock()
                        for i in range(tam-1):
                                r = arvore.getRoot()
                                arvore.tree_delete(r)
                        tF = clock()                        
                        temposDelete.append(tF - tI)
                        #print ("Tempo de TreeDelete :: {}s".format(tF-tI))
                        

                print ("\nMedias para {} elementos ::\n\tInsert - {}s\n\t"
                       "TreeWalk - {}s\n\tTreeSearch - {}s\n\tTreeSearchIterative - {}s\n\t"
                       "TreeDelete - {}s".format(tam,
                        statistics.mean(temposInsert),
                        statistics.mean(temposTreeWalk),
                        statistics.mean(temposTreeSearch),
                        statistics.mean(temposTreeSearchIT),
                        statistics.mean(temposDelete)))
                print ("Desvio Padrao para {} elementos ::\n\tInsert - {}s\n\t"
                       "TreeWalk - {}s\n\tTreeSearch - {}s\n\tTreeSearchIterative - {}s\n\t"
                       "TreeDelete - {}s".format(tam,
                        statistics.stdev(temposInsert),
                        statistics.stdev(temposTreeWalk),
                        statistics.stdev(temposTreeSearch),
                        statistics.stdev(temposTreeSearchIT),
                        statistics.stdev(temposDelete)))
                sM = '{} {} {} {} {} {}\n'.format(tam,
                                                  statistics.mean(temposInsert),
                                                  statistics.mean(temposTreeWalk),
                                                  statistics.mean(temposTreeSearch),
                                                  statistics.mean(temposTreeSearchIT),
                                                  statistics.mean(temposDelete))
                sD = '{} {} {} {} {} {}\n'.format(tam,
                                                  statistics.stdev(temposInsert),
                                                  statistics.stdev(temposTreeWalk),
                                                  statistics.stdev(temposTreeSearch),
                                                  statistics.stdev(temposTreeSearchIT),
                                                  statistics.stdev(temposDelete))
                fMedia.write(sM)
                fDesvio.write(sD)
                tam += inc

        fMedia.close()
        fDesvio.close()

numArvores = 50
tamanho = 50
iteracoes = 30
incremento = 750
teste(numArvores, tamanho, iteracoes, incremento)
