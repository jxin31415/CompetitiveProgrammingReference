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
        print(''.join((str(x) + ' ') for x in lis), file=fout)

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
    n = random.randint(900000, 1000000)
    lis = []
    for _ in range(n):
        lis.append(random.randint(1, 5))
    
    return (n, lis)

def stress():
    n = 1000000
    lis = []
    for _ in range(500000):
        lis.append(1)
    for _ in range(500000):
        lis.append(5)
    return (n, lis)


def main():
    global tc
    prep()
    
    l = []

    for _ in range(1, 15):
        l.append(randGenLarge())

    l.append(stress())

    for x in l:
        writeTestCase(*x)

    zipper()


if __name__ == '__main__':
    main()
