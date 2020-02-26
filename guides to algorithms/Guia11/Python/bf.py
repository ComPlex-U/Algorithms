from collections import defaultdict
 
class BF:
 
    def __init__(self, vertices):
        self.V = vertices
        self.graph = [] 
  
    def addEdge(self, u, v, w):
        self.graph.append([u, v, w])
         
    def printArr(self, dist):
        print("Vertex \t\t Distancia")
        for i in range(self.V):
            print("{} \t\t {}".format(i, dist[i]))
     
    def BellmanFord(self, src):
 
        # Inicializa distancias da fonte para os
        # restantes vertices como INFINITO
        dist = [float("Inf")] * self.V
        dist[src] = 0

        # O caminho mais curto a partir da fonte para qualquer
        # vertice deve ter, no máximo, |V| - 1
        for i in range(self.V - 1):
            # Actualiza o valor de dist e o index do parent dos vertices
            # adjacentes do vertice escolhido.
            # Apenas considera os vertices que estao na fila
            for u, v, w in self.graph:
                if dist[u] != float("Inf") and dist[u] + w < dist[v]:
                        dist[v] = dist[u] + w

        # Verificação de ciclo de peso negativo
        # O passo anterior garante a distancia mais curta se nao
        # houver um ciclo de peso negativo. Se houver um caminho
        # curto, então existe um ciclo.
        for u, v, w in self.graph:
                if dist[u] != float("Inf") and dist[u] + w < dist[v]:
                        print ("Graph contem ciclo de peso negativo")
                        return
                         
        # Mostrar a distancia
        self.printArr(dist)

