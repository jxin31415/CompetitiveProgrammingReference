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

def writeTestCase(lis):
    global tc
    tc += 1
    print(tc)
    filename = 'input/input%02d.txt' % tc
    with open(filename, 'w') as fout:
        print(len(lis), file=fout)
        for l in lis:
            print(len(l), end=" ", file=fout)
            print(" ".join(str(x) for x in l), file=fout)

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


def randGen(invalid_lim):
    n = random.randint(900, 1000)
    if n % 2 == 1:
        n = n - 1
    
    invalid = random.randint(0, n//invalid_lim)
    if invalid_lim == 1:
        invalid = random.randint(0, 2)
    s = random.randint(10, 1000)

    lis = []

    for i in range(invalid):
        l = []
        for j in range(1, s+1):
            l.append(j)
        random.shuffle(l)
        lis.append(l)

    for i in range(n - invalid):
        l = []
        rand = random.randint(1, s)
        for j in range(rand, s+1):
            l.append(j)
        for j in range(1, rand):
            l.append(j)
        lis.append(l)

    random.shuffle(lis)
    return lis

def stress():
    n = 1000000
    str = ""
    for _ in range(n):
        str += "C"

    return str


def main():
    global tc
    prep()
    
    l = []

    for _ in range(1, 15):
        l.append(randGen(10))
    for _ in range(1, 15):
        l.append(randGen(1))

    for x in l:
        writeTestCase(x)

    zipper()


if __name__ == '__main__':
    main()
