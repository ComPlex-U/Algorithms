from time import clock

cdef double fiboRec(double n):
    if n == 0:
        return 0
    if n == 1:
        return 1
    return fiboRec(n - 1) + fiboRec(n - 2)

def nums(int n):
    f = open('fiboRec_cy.txt','w')
    cdef int i = 0
    cdef double num
    for i in range(n):
        tS = clock()
        num = fiboRec(i)
        tE = clock()
        s = '{0} {2}\n'.format(i, num, tE - tS)
        f.write(s)
    f.close()

