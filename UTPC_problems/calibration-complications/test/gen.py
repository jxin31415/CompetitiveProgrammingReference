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

def writeTestCase(n, lis, lisB):
    global tc
    tc += 1
    print(tc)
    filename = 'input/input%02d.txt' % tc
    with open(filename, 'w') as fout:
        print(n, file=fout)
        print(''.join((str(x) + ' ') for x in lis), file=fout)
        print(''.join((str(x) + ' ') for x in lisB), file=fout)

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

def impossible():
    n = random.randint(90000, 100000)
    lis = []
    for _ in range(n):
        lis.append(random.randint(1, 1000000000))

    lisB = []
    for _ in range(n):
        lisB.append(random.randint(1, 1000000000))

    return (n, lis, lisB)

def randGen():
    n = random.randint(90000, 100000)

    targetA = random.randint(1, 1000000000)
    targetB = random.randint(targetA, 1000000000)

    lis = []
    for _ in range(n):
        lis.append(random.randint(1, targetA))

    lisB = []
    for _ in range(n):
        lisB.append(random.randint(targetB, 1000000000))

    return (n, lis, lisB)

def main():
    global tc
    prep()
    
    l = []

    for _ in range(1, 15):
        l.append(randGen())
    l.append(impossible())
    l.append(impossible())

    for x in l:
        writeTestCase(*x)

    zipper()


if __name__ == '__main__':
    main()
