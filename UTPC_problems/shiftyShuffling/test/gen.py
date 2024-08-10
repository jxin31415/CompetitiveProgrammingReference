#! /usr/bin/python3
import random
import os
import shutil
import subprocess
import time
import zipfile

random.seed(19832)
tc = 0

def writeTestCase(n, arr):
    global tc
    tc += 1
    print(tc)
    filename = 'input/input%02d.txt' % tc
    with open(filename, 'w') as fout:
        for i in range(4 * n - 1):
            print(arr[i], end = ' ', file=fout)
        print(arr[4 * n - 1], file=fout)
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
    n = 13
    lis = []
    for i in range(n):
        lis.append(i + 1)
        lis.append(i + 1)
        lis.append(i + 1)
        lis.append(i + 1)
    random.shuffle(lis)
    return (n, lis)

def main():
    global tc
    prep()

    l = []
    for i in range(1, 20):
        l.append(randGen())
    random.shuffle(l)
    for x in l:
        writeTestCase(*x)
    zipper()


if __name__ == '__main__':
    main()