from io import StringIO
import math
from ListaLigada import ListaLigada

class HashTable:

    m = 0
    n = 0

    def __init__(self, m, n):
        self.m = m
        self.n = n
        self.T = ListaLigada(m)
        for k in range(0, m):
            self.T[k] = ListaLigada(n)

    def hash_function_division(self, k):
        return k % self.m

    def hash_function_mult(self, k):
        A = (math.sqrt(5) - 1) / 2
        return math.floor(self.m * ((k * A) % 1))

    def insert(self, key):
        h = self.hash_function_mult(key)
        #print("chave = {} hash = {}".format(key, h))
        self.T[h].insert(key)

    def search(self, k):
        h = self.hash_function_mult(k)
        x = self.T[h].search(k)        
        res = StringIO()
        res.write("----\nh = ");
        res.write("{}".format(h));
        res.write(" x = ");
        res.write("{}".format(x));
        return res.getvalue()

    def toString(self):
        s = ""
        k = 0
        for x in range(0, self.m):
            l = self.T[x]
            s += "\nLista = {}\n".format(k)
            s += l.toString()
            k += 1
        return s
