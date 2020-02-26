from io import StringIO

class Pilha:

    n = 0
    top = -1
    S = []    

    def __init__(self, n):
        self.n = n
        self.top = -1
        self.S = [0] * n

    def stack_empty(self):
        if self.top == -1:
            return True
        else:
            return False

    def push(self, item):
        self.top = self.top + 1
        self.S[self.top] = item

    def pop(self):
        if self.stack_empty is False:
            print("underflow")
            return 0
        else:
            self.top = self.top - 1
            return self.S[self.top+1]

    def size(self):
        return len(self.S)

    def toString(self):
        txt = StringIO()
        for k in self.S:
            txt.write("{}".format(k))
            txt.write(" ")
        return txt.getvalue()
