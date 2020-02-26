from Memoria import Memoria
from io import StringIO

class ArvorePesquisaBinaria:

    NIL = 0
    root = NIL
    z = 0

    def __init__(self, n):
        self.m = Memoria(n)

    def getRoot(self):
        return self.root

    #
    #   INSERT
    #
    def insert(self, key):
        z = self.m.allocate_object()
        self.m.key[z] = key

        y = self.NIL
        x = self.root
        
        while x != self.NIL:
            y = x
            if self.m.key[z] < self.m.key[x]:
                x = self.m.left[x]
            else:
                x = self.m.right[x]

        self.m.p[z] = y
        if y == self.NIL:
            self.root = z
        elif self.m.key[z] < self.m.key[y]:
            self.m.left[y] = z
        else:
            self.m.right[y] = z

    #
    #   INORDER TREE WALK
    #
    def inorder_tree_walk(self, x):
        if x == self.NIL:
            return
        else:
            self.inorder_tree_walk(self.m.left[x])
            #print("{}".format(self.m.key[x]))
            self.inorder_tree_walk(self.m.right[x])

    def inorder_tree_walkEXT(self):
        self.inorder_tree_walk(self.root)

    #
    #   TREE SEARCH
    #
    def tree_search(self, x, k):
        if x == self.NIL or k == self.m.key[x]:
            return x
        if k < self.m.key[x]:
            return self.tree_search(self.m.left[x], k)
        else:
            return self.tree_search(self.m.right[x], k)

    def tree_searchEXT(self, chave):
        return self.tree_search(self.root, chave)
    
    #
    #   ITERATIVE TREE SEARCH
    #
    def iterative_tree_search(self, x, k):
        while x == self.NIL or k != self.m.key[x]:
            if k < self.m.key[x]:
                x = self.m.left[x]
            else:
                x = self.m.right[x]
        return x

    def iterative_tree_searchEXT(self, chave):
        return self.iterative_tree_search(self.root, chave)
    
    #
    #   TREE MINIMUM
    #
    def tree_minimum(self, x):
        while self.m.left[x] != self.NIL:
            x = self.m.left[x]
        return x

    def tree_minimumEXT(self):
        return self.tree_minimum(self.root)
    
    #
    #   TREE MAXIMUM
    #
    def tree_maximum(self, x):
        while self.m.right[x] != self.NIL:
            x = self.m.right[x]
        return x

    def tree_maximumEXT(self):
        return self.tree_maximum(self.root)
    
    #
    #   TREE SUCCESSOR
    #
    def tree_successor(self, x):
        if self.m.right[x] != self.NIL:
            return self.tree_minimum(self.m.right[x])
        y = self.m.p[x]
        while y != self.NIL and x == self.m.right[y]:
            x = y
            y = self.m.p[y]
        return y

    def tree_successorEXT(self):
        return self.tree_successor(self.root)
    
    #
    #   TRANSPLANT
    #
    def transplant(self, u, v):
        if self.m.p[u] == self.NIL:
           self.root = v
        elif u == self.m.left[self.m.p[u]]:
            self.m.left[self.m.p[u]] = v
        else:
            self.m.right[self.m.p[u]] = v
        if v != self.NIL:
            self.m.p[v] = self.m.p[u]
    
    #
    #   DELETE
    #
    def tree_delete(self, z):
        if self.m.left[z] == self.NIL:
            self.transplant(z, self.m.right[z])
        elif self.m.right[z] == self.NIL:
            self.transplant(z, self.m.left[z])
        else:
            y = self.tree_minimum(self.m.right[z])
            if self.m.p[y] != z:
                self.transplant(y, self.m.right[y])
                self.m.right[y] = self.m.right[z]
                self.m.p[self.m.right[y]] = y
            self.transplant(z, y)
            self.m.left[y] = self.m.left[z]
            self.m.p[self.m.left[y]] = y            

    def toString(self):
        s1 = self.m.toString() + "root -> "
        s2 = "nil" if self.root == 0 else "{}".format(self.root)
        return s1 + s2
    
