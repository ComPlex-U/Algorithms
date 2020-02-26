from io import StringIO
from ArvorePesquisaBinaria import ArvorePesquisaBinaria

class ArvoreRedBlack(ArvorePesquisaBinaria):
    color = []
    BLACK = 0
    RED = 1
    right = []
    left = []
    p = []

    def __init__(self, n):
        super().__init__(n)
        self.color = [0] * n
        self.right = self.m.right
        self.left = self.m.left
        self.p = self.m.p

    def left_rotate(self, x):
        y = right[x]
        right[x] = left[y]
        if left[y] != NIL:
            p[left[y]] = x

        p[y] = p[x]
        if p[x] == NIL:
            root = y
        elif x == p[left[x]]:
            p[left[x]] = y
        else:
            right[p[x]] = y
        left[y] = x
        p[x] = y

    def right_rotate(self, x):
        y = left[x]
        left[x] = right[y]
        if right[y] != NIL:
            p[right[y]] = x
        p[y] = p[x]
        if p[x] == NIL:
            root = y
        elif x == p[right[x]]:
            p[right[x]] = y
        else:
            left[p[x]] = y
        right[y] = x
        p[x] = y

    def insert_fixup(self, Z):
        y = 0
        while self.color[self.p[self.z]] == self.RED:
            if self.p[self.z] == self.left[self.p[self.p[self.z]]]:
                y = self.right[self.p[self.p[self.z]]]
                if self.color[y] == self.RED:
                    self.color[self.p[self.z]] = self.BLACK
                    self.color[y] = self.BLACK
                    self.color[self.p[self.p[self.z]]] = self.RED
                    self.z = self.p[self.p[self.z]]
                else:
                    if Z == self.right[self.p[self.z]]:
                        self.z = self.p[self.z]
                        self.left_rotate(self.z)
                    self.color[self.p[self.z]] = self.BLACK
                    self.color[self.p[self.p[self.z]]] = self.RED
                    self.right_rotate(self.p[self.p[self.z]])
            else:
                y = self.left[self.p[self.p[self.z]]]
                if self.color[y] == self.RED:
                    self.color[self.p[self.z]] = self.BLACK
                    self.color[y] = self.BLACK
                    self.color[self.p[self.p[self.z]]] = self.RED
                    self.z = self.p[self.p[self.z]]
                else:
                    if Z == self.left[self.p[self.z]]:
                        self.z = self.p[self.z]
                        self.right_rotate(self.z)
                    self.color[self.p[self.z]] = self.BLACK
                    self.color[self.p[self.p[self.z]]] = self.RED
                    self.left_rotate(self.p[self.p[self.z]])
        self.color[self.root] = self.BLACK

    def insert(self, key):
        super().insert(key)
        self.color[self.z] = self.RED
        print("z = {}".format(self.z))
        self.insert_fixup(self.z)

    def rb_transplant(self, u, v):
        if self.m.p[u] == self.NIL:
           self.root = v
        elif u == self.m.left[self.m.p[u]]:
            self.m.left[self.m.p[u]] = v
        else:
            self.m.right[self.m.p[u]] = v
        self.m.p[v] = self.m.p[u]

    def rb_tree_delete(self, Z):
        y = Z
        y_original_color = self.color[y]
        if self.m.left[Z] == self.NIL:
            x = self.m.right[Z]
            self.transplant(Z, self.m.right[Z])
        elif self.m.right[Z] == self.NIL:
            x = self.m.left[Z]
            self.transplant(Z, self.m.left[Z])
        else:
            y = self.tree_minimum(self.m.right[Z])
            y_original_color = self.color[y]
            x = self.m.right[y]
            if self.m.p[y] != Z:
                self.rb_transplant(y, self.m.right[y])
                self.m.right[y] = self.m.right[Z]
                self.m.p[self.m.right[y]] = y
            self.rb_transplant(Z, y)
            self.m.left[y] = self.m.left[Z]
            self.m.p[self.m.left[y]] = y
            self.color[y] = self.color[Z]
        if y_original_color == self.BLACK:
            self.rb_delete_fixup(x)

    def rb_delete_fixup(self, x):
        while x != self.root and self.color[x] == self.BLACK:
            if x == self.m.left[self.m.p[x]]:
                w = self.m.right[self.m.p[x]]
                if self.color[w] == self.RED:
                    self.color[w] = self.BLACK
                    self.color[self.m.p[x]] = self.RED
                    self.left_rotate(self.m.p[x])
                    w = self.m.right[self.m.p[x]]                
                if self.color[self.m.left[w]] == self.BLACK and self.color[self.m.right[w]] == self.BLACK:
                    self.color[w] = self.RED
                    x = self.m.p[x]
                elif self.color[self.m.right[w]] == self.BLACK:
                    self.color[self.m.left[w]] = self.BLACK
                    self.color[w] = self.RED
                    self.right_rotate(w)
                    w = self.m.right[self.m.p[x]]
                self.color[w] = self.color[self.m.p[x]]
                self.color[self.m.p[x]] = self.BLACK
                self.color[self.m.right[w]] = self.BLACK
                self.left_rotate(self.m.p[x])
                x = self.root
            else:
                w = self.m.right[self.m.p[x]]
                if self.color[w] == self.RED:
                    self.color[w] = self.BLACK
                    self.color[self.m.p[x]] = self.RED
                    self.right_rotate(self.m.p[x])
                    w = self.m.right[self.m.p[x]]
                if self.color[self.m.left[w]] == self.BLACK and self.color[self.m.right[w]] == self.BLACK:
                    self.color[w] = self.RED
                    x = self.m.p[x]
                elif self.color[self.m.right[w]] == self.BLACK:
                    self.color[self.m.left[w]] = self.BLACK
                    self.color[w] = self.RED
                    self.left_rotate(w)
                    w = self.m.right[self.m.p[x]]
                self.color[w] = self.color[self.m.p[x]]
                self.color[self.m.p[x]] = self.BLACK
                self.color[self.m.right[w]] = self.BLACK
                self.right_rotate(self.m.p[x])
                x = self.root
        self.color[x] = self.BLACK                             
        

    def toString(self):
        s = super().toString()
        res = StringIO()
        res.write("\n")
        for k in range((len(self.color)) - 1, -1, -1):
            res.write("{}".format(k))
            res.write("-> color = ")
            res.write("BLACK") if self.color[k] == 0 else res.write("RED")
            res.write("\n")
        return s + res.getvalue()
