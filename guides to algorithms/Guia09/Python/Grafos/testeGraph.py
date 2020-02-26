from Graph import Graph

g = { "a" : ["d"],
      "b" : ["c"],
      "c" : ["b", "c", "d", "e"],
      "d" : ["a", "c"],
      "e" : ["c"],
      "f" : []
    }

graph = Graph(g)

print("Vertices do Grafo ::")
print("\t{}".format(graph.vertices()))

print("Ramos do Grafo ::")
print("\t{}".format(graph.edges()))


print('Caminho do vertice "a" para vertice "b" ::')
path = graph.find_path("a", "b")
print("\t{}".format(path))

print('Caminho do vertice "a" para vertice "f" ::')
path = graph.find_path("a", "f")
print("\t{}".format(path))

print('Todos os caminhos do vertice "a" para vertice "b"::')
path = graph.find_all_paths("a", "b")
print("\t{}".format(path))

print('Todos os caminhos do vertice "a" para vertice "f" ::')
path = graph.find_all_paths("a", "f")
print("\t{}".format(path))

print('Grau de do vertice "a" ::')
grau = graph.vertex_degree("a")
print("\t{}".format(grau))

print('Vertices isolados ::')
print(graph.find_isolated_vertices())
