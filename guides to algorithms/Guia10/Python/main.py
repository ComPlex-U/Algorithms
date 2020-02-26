'''
Este código requer a instalação da biblioteca networkx
Consultar:
https://networkx.github.io/documentation/stable/install.html
'''
import networkx as nx
from networkx.algorithms import tree

print("KRUSKAL\n----")
G = nx.cycle_graph(4)
G.add_edge(0, 3, weight = 2)
print("Minimum Spanning Edges")
mst = nx.minimum_spanning_edges(G, data=False)
edgelist = list(mst)
print(sorted(edgelist))

print("Minimum Spanning Tree")
T=nx.minimum_spanning_tree(G)
print(sorted(T.edges(data=True)))

print("PRIM\n----")
mst = tree.minimum_spanning_edges(G, algorithm='prim', data=False)
edgelist = list(mst)
print(sorted(edgelist))
