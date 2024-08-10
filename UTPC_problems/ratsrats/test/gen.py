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

def writeTestCase(n, arr):
    global tc
    tc += 1
    print(tc)
    filename = 'input/input%02d.txt' % tc
    with open(filename, 'w') as fout:
        print(n, file=fout)
        for i in range(n-1):
            print(arr[i], end = ' ', file=fout)
        print(arr[n-1], file=fout)
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
        x = random.randint(1, 31622)
        iter = random.randint(1, 2)
        b = 3
        if x != 1:
            b = math.floor(math.log(1000000000, x))
        for _ in range(iter):
            k = random.randint(2, b)
            lis.append(pow(x, k))

    random.shuffle(lis)
    return (n, lis[0:n])

def randGenSmall():
    n = random.randint(90000, 100000)
    lis = []
    while len(lis) < n:
        x = random.randint(1, 20)
        iter = random.randint(1, math.floor(n / 10))
        b = 3
        if x != 1:
            b = math.floor(math.log(1000000000, x))
        for _ in range(iter):
            k = random.randint(2, b)
            lis.append(pow(x, k))

    random.shuffle(lis)
    return (n, lis[0:n])

def randGenMid():
    n = random.randint(90000, 100000)
    lis = []
    while len(lis) < n:
        x = random.randint(1, 100)
        iter = random.randint(1, math.floor(n / 100))
        b = 3
        if x != 1:
            b = math.floor(math.log(1000000000, x))
        for _ in range(iter):
            k = random.randint(2, b)
            lis.append(pow(x, k))

    random.shuffle(lis)
    return (n, lis[0:n])

def main():
    global tc
    prep()
    
    l = []

    for i in range(1, 10):
        l.append(randGenLarge())

    for i in range(1, 10):
        l.append(randGenMid())
    
    for i in range(1, 10):
        l.append(randGenSmall())
    
    random.shuffle(l)
    for x in l:
        writeTestCase(*x)
    zipper()


if __name__ == '__main__':
    main()
