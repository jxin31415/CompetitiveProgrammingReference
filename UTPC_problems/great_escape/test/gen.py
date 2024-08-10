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

def writeTestCase(n, m, k, lis):
    global tc
    tc += 1
    print(tc)
    filename = 'input/input%02d.txt' % tc
    with open(filename, 'w') as fout:
        print(str(n) + " " + str(m) + " " + str(k), file=fout)
        for (x, y, r) in lis:
            print(str(x) + " " + str(y) + " " + str(r), file=fout)

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
    n = random.randint(900000, 1000000)
    m = random.randint(900000, 1000000)
    k = random.randint(900, 1000)

    lis = []
    for ind in range(k):
        x = random.randint(100000, n - 100000)
        y = random.randint(100000, m - 100000)
        r = random.randint(1, 55000)

        if random.randint(0, 10000) > 1 and ind > 0:
            x = lis[ind-1][0] + (round(2 * r/3) if random.randint(0, 1) == 0 else -round(2 * r/3)) 
            y = lis[ind-1][1] + (round(2 * r/3) if random.randint(0, 1) == 0 else -round(2 * r/3)) 
            x = min(max(x, 0), n)
            y = min(max(y, 0), m)
        lis.append((x, y, r))

    return n, m, k, lis

def stress():
    n = 1000000000
    m = 1000000000
    k = random.randint(900, 1000)

    lis = []
    for _ in range(k):
        x = random.randint(0, 1000000000)
        y = random.randint(0, 1000000000)
        r = random.randint(1, 10000000)

        lis.append((x, y, r))

    return n, m, k, lis

def stress2():
    n = 1000
    m = 1000
    k = 1000

    lis = []
    for ind in range(k):
        x = 0 + ind
        y = 500
        r = 1

        lis.append((x, y, r))

    return n, m, k, lis


def main():
    global tc
    prep()
    
    l = []

    for _ in range(1, 20):
        l.append(randGen())

    l.append(stress())
    l.append(stress2())

    for x in l:
        writeTestCase(*x)

    zipper()


if __name__ == '__main__':
    main()
