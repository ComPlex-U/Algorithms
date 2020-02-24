from time import clock

def fiboRec(n):
    if n == 0:
        return 0
    if n == 1:
        return 1
    return fiboRec(n - 1) + fiboRec(n - 2)

def nums(n):
    f = open('fiboRec_py.txt','w')
    for i in range(n):
        tS = clock()
        num = fiboRec(i)
        tE = clock()
        s = '{0} {2}\n'.format(i, num, tE - tS)
        print (i, num, tE - tS)
        f.write(s)
    f.close()

nums(30)
