#! /usr/bin/python3
import random
import os
import shutil
import subprocess
import time
import zipfile

random.seed(19832)
tc = 0

def writeTestCase(n, m, arr):
    global tc
    tc += 1
    print(tc)
    filename = 'input/input%02d.txt' % tc
    with open(filename, 'w') as fout:
        print(n, end = ' ', file=fout)
        print(m, file=fout)
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

def randGen():
    n = random.randint(900000, 1000000)
    m = random.randint(2, 100000)
    lis = []
    for _ in range(n):
        a = random.randint(1, 1000000000)
        lis.append(a)

    return (n, m, lis)

def primeCase():
    n = 1000000
    m = 95143
    lis = []
    for _ in range(n):
        a = random.randint(1, 1000000000)
        lis.append(a)

    return (n, m, lis)

def tc1():
    n = 3
    m = 6
    lis = [3, 7, 12]
    return (n, m, lis)

def tc2():
    n = 3
    m = 720
    lis = [100, 200, 300]
    return (n, m, lis)

def tc3():
    n = 10
    m = 15
    lis = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    return (n, m, lis)

def main():
    global tc
    prep()
    writeTestCase(*tc1())
    writeTestCase(*tc2())
    writeTestCase(*tc3())
    
    l = []
    for i in range(1, 20):
        l.append(randGen())
    l.append(primeCase())
    random.shuffle(l)
    for x in l:
        writeTestCase(*x)
    zipper()


if __name__ == '__main__':
    main()
