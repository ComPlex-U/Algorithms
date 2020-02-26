from io import StringIO
from Pilha import Pilha

class Memoria:

    p = []
    key = []
    left = []
    right = []
    NIL = 0
    
    def __getitem__(self, index):
        return self.s[index]

    def __init__(self, n):
        self.p = [0] * n
        self.key = [0] * n
        self.left = [0] * n
        self.right = [0] * n
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
        for k in range((len(self.p)) - 1, -1, -1):
            res.write("{}".format(k))
            res.write("-> [p = ")
            res.write("nil") if self.p[k] == 0 else res.write("{}".format(self.p[k]))
            res.write(", key = ")
            res.write("{}".format(self.key[k]))
            res.write(", left = ")
            res.write("nil") if self.left[k] == 0 else res.write("{}".format(self.left[k]))
            res.write(", right = ")
            res.write("nil") if self.right[k] == 0 else res.write("{}".format(self.right[k]))
            res.write("]\n")
        return res.getvalue()       
