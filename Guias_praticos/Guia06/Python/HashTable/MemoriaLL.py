from io import StringIO
from Pilha import Pilha

class MemoriaLL:

    nxt = []
    key = []
    prv = []
    
    def __getitem__(self, index):
        return self.s[index]

    def __setitem__(self, key, item):
        self.s[key] = item

    def __init__(self, n):
        self.nxt = [0] * n
        self.key = [0] * n
        self.prv = [0] * n
        self.s = Pilha(n)
        for k in range(0, n):
            self.s.push(k)

    def allocate_object(self):
        x = self.s.pop()
        if x == 0:
            return 0
        else:
            return x

    def free_object(self, x):
        self.s.push(x)

    def toString(self):
        res = StringIO()
        for k in range(0,self.s.size()):
            res.write("{}".format(k))
            res.write("-> [")
            res.write("{}".format(self.prv[k]))
            res.write(", ")
            res.write("{}".format(self.key[k]))
            res.write(", ")
            res.write("{}".format(self.nxt[k]))
            res.write("]\n")
        return res.getvalue()       
