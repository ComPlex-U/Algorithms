from io import StringIO
import sys
from MemoriaLL import MemoriaLL

class ListaLigada:

    NIL = sys.maxsize
    m = None
    head = NIL

    def __getitem__(self, index):
        return self.m[index]

    def __setitem__(self, key, item):
        self.m[key] = item

    def __init__(self, n):
        self.m = MemoriaLL(n)
        self.head = self.NIL

    def insert(self, chave):
        x = self.m.allocate_object()
        self.m.nxt[x] = self.head
        self.m.key[x] = chave
        if self.head != self.NIL:
            self.m.prv[self.head] = x
        self.head = x
        self.m.prv[x] = self.NIL

    def delete(self, x):
        if self.m.prv[x] != self.NIL:
            self.m.nxt[self.m.prv[x]] = self.m.nxt[x]
        else:
            self.head = self.m.nxt[x]
        if self.m.nxt[x] != self.NIL:
            self.m.prv[self.m.nxt[x]] = self.m.prv[x]
        self.m.free_object(x)

    def search(self, k):
        x = self.head
        while x != self.NIL and self.m.key[x] != k:
            x = self.m.nxt[x]
        return x
            
    def toString(self):
        s = self.m.toString() + "head -> {}".format(self.head)
        return s
