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

def writeTestCase(n, a, b):
    global tc
    tc += 1
    print(tc)
    filename = 'input/input%02d.txt' % tc
    with open(filename, 'w') as fout:
        print(n, file=fout)
        print(''.join((str(x) + ' ') for x in a), file=fout)
        print(''.join((str(x) + ' ') for x in b), file=fout)

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

def randGen():
    n = random.randint(100000, 200000)
    a = []
    for _ in range(n):
        r = random.randint(500000000, 1000000000)
        a.append(r)
    b = []
    for _ in range(n):
        r = random.randint(1, 500000000)
        b.append(r)

    return (n, a, b)

def randGen2():
    n = random.randint(100000, 200000)
    a = []
    for _ in range(n):
        r = random.randint(500000000, 1000000000)
        a.append(r)
    b = []
    for _ in range(n):
        r = random.randint(1, 10000000)
        b.append(r)

    return (n, a, b)


def main():
    global tc
    prep()
    
    l = []

    for _ in range(1, 10):
        l.append(randGen())

    for _ in range(1, 10):
        l.append(randGen2())

    for x in l:
        writeTestCase(*x)

    zipper()


if __name__ == '__main__':
    main()
