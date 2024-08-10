#! /usr/bin/python3
import random
import os
from re import X
import shutil
import subprocess
import time
import zipfile
import math

random.seed(19832)
tc = 0

def writeTestCase(n, arr, m, arr2, arrK):
    global tc
    tc += 1
    print(tc)
    filename = 'input/input%02d.txt' % tc
    with open(filename, 'w') as fout:
        print(n, end = ' ', file=fout)
        print(m, file=fout)
        for i in range(n-1):
            print(arr[i], end = ' ', file=fout)
        print(arr[n-1], file=fout)
        for i in range(m-1):
            print(arr2[i], end = ' ', file=fout)
        print(arr2[m-1], file=fout)
        for i in range(m-1):
            print(arrK[i], end = ' ', file=fout)
        print(arrK[m-1], file=fout)
    with open('output/output%02d.txt' % tc, 'w') as outf:
        with open(filename, 'r') as inf:
            subprocess.call(['java', '../ac/jimmy.java'],
                            stdin=inf, stdout=outf)


def prep():
    inpath = os.getcwd() + '/input'
    outpath = os.getcwd() + '/output'
    shutil.rmtree(inpath, True)
    os.mkdir(inpath)
    shutil.rmtree(outpath, True)
    os.mkdir(outpath)
    time.sleep(1)


def zipper():
    zipf = zipfile.ZipFile('test.zip', 'w')
    for _, _, files in os.walk('input'):
        for filename in files:
            zipf.write(os.path.join('input', filename))
    for _, _, files in os.walk('output'):
        for filename in files:
            zipf.write(os.path.join('output', filename))
    zipf.close()

def randGenLarge():
    n = random.randint(90000, 100000)
    lis = []
    while len(lis) < n:
        lis.append(random.randint(1, 1000000000))
    
    m = random.randint(90000, 100000)
    b = []
    while len(b) < m:
        b.append(random.randint(1, n))

    k = []
    while len(k) < m:
        k.append(random.randint(100000000, 1000000000))
    
    return (n, lis[0:n], m, b[0:m], k[0:m])

def randGenMid():
    n = random.randint(90000, 100000)
    lis = []
    while len(lis) < n:
        lis.append(random.randint(1, 1000000))
    
    m = random.randint(90000, 100000)
    b = []
    while len(b) < m:
        b.append(random.randint(1, n))

    k = []
    while len(k) < m:
        k.append(random.randint(1000000, 9000000))
    
    return (n, lis[0:n], m, b[0:m], k[0:m])

def randGenSmall():
    n = 10
    lis = []
    while len(lis) < n:
        lis.append(random.randint(1, 100))
    
    m = 10
    b = []
    while len(b) < m:
        b.append(random.randint(1, n))

    k = []
    while len(k) < m:
        k.append(random.randint(40, 60))
    
    return (n, lis[0:n], m, b[0:m], k[0:m])

def edge_case():
    n = 100000
    lis = []
    while len(lis) < n:
        lis.append(random.randint(1, 2))
    
    m = 100000
    b = []
    while len(b) < m:
        b.append(random.randint(1, n))

    random.shuffle(lis)
    random.shuffle(b)

    k = []
    while len(k) < m:
        k.append(50000)
    
    return (n, lis[0:n], m, b[0:m], k[0:m])

def edge_case2():
    n = 100000
    lis = []
    while len(lis) < n:
        lis.append(random.randint(40000, 80000))
    
    m = 100000
    b = []
    while len(b) < m:
        b.append(random.randint(1, n))

    k = []
    while len(k) < m:
        k.append(50000)
    
    return (n, lis[0:n], m, b[0:m], k[0:m])

def main():
    global tc
    prep()
    
    l = []

    for i in range(1, 3):
        l.append(randGenSmall())

    for x in l:
        writeTestCase(*x)
    
    l.clear()

    for i in range(1, 7):
        l.append(randGenLarge())
    for i in range(1, 7):
        l.append(randGenMid())

    random.shuffle(l)

    l.append(edge_case())
    l.append(edge_case2())
    
    for x in l:
        writeTestCase(*x)

    zipper()


if __name__ == '__main__':
    main()
