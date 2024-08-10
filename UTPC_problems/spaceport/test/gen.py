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

def writeTestCase(n, k, lis, adj):
    global tc
    tc += 1
    print(tc)
    filename = 'input/input%02d.txt' % tc
    with open(filename, 'w') as fout:
        print(' '.join([str(n), str(k)]), file=fout)
        print(' '.join((str(x)) for x in lis), file=fout)
        for i in range(n-1):
            print(' '.join([str(adj[i][0]), str(adj[i][1])]), file=fout)

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
    sum = 0
    for i in range(n):
        lis.append(random.randint(1, 1000))
        sum += lis[i]

    k = random.randint(1, sum)

    adj = []
    for i in range(2, n+1):
        adj.append((i, random.randint(1, i-1)))
    
    return (n, k, lis, adj)


def main():
    global tc
    prep()
    
    l = []

    for _ in range(1, 20):
        l.append(randGenLarge())

    for x in l:
        writeTestCase(*x)

    zipper()


if __name__ == '__main__':
    main()
