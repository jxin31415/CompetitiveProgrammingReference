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

def writeTestCase(stro):
    global tc
    tc += 1
    print(tc)
    filename = 'input/input%02d.txt' % tc
    with open(filename, 'w') as fout:
        print(stro[0], file=fout)
        print(stro[1], file=fout)
        for s in stro[2]:
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

def randGen():
    n1 = random.randint(900, 1000)
    stro = ""
    for _ in range(n1):
        r = random.randint(1, 4)
        if r == 1:
            stro += "A"
        elif r == 2:
            stro += "T"
        elif r == 3:
            stro += "C"
        elif r == 4:
            stro += "G"

    n = random.randint(300, 1000)
    lis = set()
    lis.add(stro[:10])
    lis.add(stro[-10:])

    while len(lis) < n:
        r1 = random.randint(0, len(stro)-1)
        r2 = random.randint(r1+1, len(stro))
        # print(len(stro[r1:r2]))
        lis.add(stro[r1:r2])

    return (n, stro, lis)

def randGen2():
    n1 = random.randint(900, 1000)
    stro = ""
    for _ in range(n1):
        r = random.randint(1, 4)
        if r == 1:
            stro += "A"
        elif r == 2:
            stro += "T"
        elif r == 3:
            stro += "C"
        elif r == 4:
            stro += "G"

    n = random.randint(300, 1000)
    lis = set()
    lis.add(stro[:10])
    lis.add(stro[-10:])

    while len(lis) < n:
        r1 = random.randint(0, len(stro)-1)
        r2 = random.randint(r1+1, min(len(stro), r1 + 50))
        # print(len(stro[r1:r2]))
        lis.add(stro[r1:r2])

    return (n, stro, lis)


def stress():
    n1 = random.randint(900, 1000)
    stro = ""
    for _ in range(n1):
        stro += "A"

    n = random.randint(200, 1000)
    lis = set()

    while len(lis) < n:
        r1 = random.randint(0, len(stro) - 1)
        r2 = random.randint(r1+1, len(stro))
        lis.add(stro[r1:r2])

    return (n, stro, lis)


def main():
    global tc
    prep()
    
    l = []

    for _ in range(1, 10):
        l.append(randGen())
    for _ in range(1, 10):
        l.append(randGen2())

    l.append(stress())

    for x in l:
        writeTestCase(x)

    zipper()


if __name__ == '__main__':
    main()
