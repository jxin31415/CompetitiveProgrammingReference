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
        for (x, y) in lis:
            print(f'{x:.10f} {y:.10f}', file=fout)

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

def append(lis, x, y):
    for (a, b) in lis:
        if abs(a - x) < 0.1 and abs(b - y) < 0.1:
            return lis
    return lis + [(x, y)]

def randGen():
    l = []

    flip = random.randint(0, 1)
    if flip == 0:
        r = random.randint(1, 50)
        x_cent = random.randint(-50, 50)
        y_cent = random.randint(-50, 50)

        while len(l) < 10:
            angle = random.random() * math.pi * 2
            x = math.cos(angle) * r + x_cent
            y = math.sin(angle) * r + y_cent

            l = append(l, x, y)

    else:
        x1 = random.randint(-90, 75)
        y1 = random.randint(-90, 75)
        x2 = random.randint(x1, 90)
        y2 = random.randint(y1, 90)

        while len(l) < 10:
            s = random.randint(0, 3)

            if s == 0:
                l = append(l, x1, random.randint(y1, y2))
            elif s == 1:
                l = append(l, x2, random.randint(y1, y2))
            elif s == 2:
                l = append(l, random.randint(x1, x2), y1)
            elif s == 3:
                l = append(l, random.randint(x1, x2), y2)

    return l

def main():
    global tc
    prep()
    
    l = []

    for _ in range(1, 50):
        l.append(randGen())

    for x in l:
        writeTestCase(x)

    zipper()


if __name__ == '__main__':
    main()
