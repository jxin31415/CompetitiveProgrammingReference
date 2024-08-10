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
        print(lis, file=fout)

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
    lis = ""
    for _ in range(n):
        points = random.randint(97, 97+26-1)
        lis += chr(points)
    
    return lis

def midGen():
    n = random.randint(900000, 1000000)
    lis = ""
    for _ in range(n):
        points = random.randint(97, 102)
        lis += chr(points)
    
    return lis

def edge():
    n = random.randint(900000, 1000000)
    lis = ""
    for _ in range(n):
        lis += chr(100)
    
    return lis

def main():
    global tc
    prep()
    
    l = []

    for _ in range(1, 10):
        l.append(randGen())
    
    for _ in range(1, 3):
        l.append(midGen())

    for _ in range(1, 3):
        l.append(edge())

    
    for x in l:
        writeTestCase(x)

    zipper()


if __name__ == '__main__':
    main()
