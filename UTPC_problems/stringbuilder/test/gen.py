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

def writeTestCase(n, orig, lis):
    global tc
    tc += 1
    print(tc)
    filename = 'input/input%02d.txt' % tc
    with open(filename, 'w') as fout:
        print(n, file=fout)
        print(orig, file=fout)
        for s in lis:
            print(s, file=fout)

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

def randGen(alpha):
    n = random.randint(900, 1000)
    orig = ""
    lenorig = random.randint(9000, 10000)
    for _ in range(lenorig):
        r = random.randint(0, len(alpha) - 1)
        orig += alpha[r]
    
    lis = []
    for _ in range(n):
        str = ""
        lens = random.randint(100, 1000)
        for _ in range(lens):
            r = random.randint(0, len(alpha) - 1)
            str += alpha[r]
        lis.append(str)

    return n, orig, lis


def main():
    global tc
    prep()
    
    l = []

    for _ in range(1, 3):
        l.append(randGen('abcdef'))

    l.append(randGen('a'))
    
    for x in l:
        writeTestCase(*x)

    zipper()


if __name__ == '__main__':
    main()
