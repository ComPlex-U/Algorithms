from bf import BF
from dk import DK

print("BELLMAN FORD\n----")
n = BF(5)
n.addEdge(0, 1, 6)
n.addEdge(0, 1, 7)
n.addEdge(1, 2, 5)
n.addEdge(2, 1, 2)
n.addEdge(1, 3, 8)
n.addEdge(1, 4, 4)
n.addEdge(3, 4, 9)
n.addEdge(4, 2, 7)
n.addEdge(4, 0, 2)

pv = 0
print("Caminhos do vertex {}".format(pv))
n.BellmanFord(pv)

print("\nDIJKSTRA\n----")
G = DK()
G.add_vertex('a')
G.add_vertex('b')
G.add_vertex('c')
G.add_vertex('d')
G.add_vertex('e')

G.add_edge('a', 'b', 2)
G.add_edge('a', 'c', 8)
G.add_edge('a', 'd', 5)
G.add_edge('b', 'c', 1)
G.add_edge('c', 'e', 3)
G.add_edge('d', 'e', 4)

print(G) 

print(G.dijkstra('a'))
print("Caminho mais curto entre 'a' e 'e'::")
print(G.shortest_path('a', 'e'))

