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

def writeTestCase(n, lis):
    global tc
    tc += 1
    print(tc)
    filename = 'input/input%02d.txt' % tc
    with open(filename, 'w') as fout:
        print(n, file=fout)
        for line in lis:
            print(''.join(str(i) for i in line), file=fout)

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
    n = random.randint(900, 1000)
    lis = []
    while len(lis) < n:
        s = []
        while len(s) < n:
            s.append('.')
        lis.append(s)
        
    m = 100
    for _ in range(m):
        i = random.randint(0, n-1)
        j = random.randint(0, n-1)
        lis[i][j] = '#'
    
    return (n, lis)


def randGenMid():
    n = random.randint(900, 1000)
    lis = []
    while len(lis) < n:
        s = []
        while len(s) < n:
            s.append('.')
        lis.append(s)
        
    m = 100
    k = random.randint(0, 600)
    for _ in range(m):

        i = random.randint(k, k + 100)
        j = random.randint(k, k + 100)
        lis[i][j] = '#'
    
    return (n, lis)

def randGenSmall():
    n = random.randint(900, 1000)
    lis = []
    while len(lis) < n:
        s = []
        while len(s) < n:
            s.append('.')
        lis.append(s)
        
    m = 100
    k = random.randint(0, 800)
    for _ in range(m):
        i = random.randint(k, k + 12)
        j = random.randint(k, k + 12)
        lis[i][j] = '#'
    
    return (n, lis)

def edge():
    n = random.randint(900, 1000)
    lis = []
    while len(lis) < n:
        s = []
        while len(s) < n:
            s.append('.')
        lis.append(s)

    for i in range(10):
        for j in range(10):
            m = random.randint(1, 10)
            if m > 2:
                lis[i+200][j+200] = '#'
    
    return (n, lis)

def main():
    global tc
    prep()
    
    l = []

    for _ in range(1, 6):
        l.append(randGenLarge())
    for _ in range(1, 6):
        l.append(randGenMid())
    for _ in range(1, 6):
        l.append(randGenSmall())

    l.append(edge())

    for x in l:
        writeTestCase(*x)

    zipper()


if __name__ == '__main__':
    main()
