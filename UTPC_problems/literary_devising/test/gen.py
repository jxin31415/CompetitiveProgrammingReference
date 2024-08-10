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

def writeTestCase(n, m, q, m_lis, q_lis):
    global tc
    tc += 1
    print(tc)
    filename = 'input/input%02d.txt' % tc
    with open(filename, 'w') as fout:
        print(str(n) + " " + str(m) + " " + str(q), file=fout)
        for (u, v) in m_lis:
            print(str(u) + " " + str(v), file=fout)
        for (u, v) in q_lis:
            print(str(u) + " " + str(v), file=fout)

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

def randGen(m):
    n = random.randint(950, 1000)
    q = random.randint(950, 1000)
    m_lis = []
    q_lis = []

    for _ in range(m):
        u = random.randint(1, n-1)
        v = random.randint(u+1, n)
        while u == v:
            u = random.randint(1, n-1)
            v = random.randint(u+1, n)
        
        m_lis.append((u, v))

    for _ in range(q):
        u = random.randint(1, n-1)
        v = random.randint(1, n)
        while u == v:
            u = random.randint(1, n-1)
            v = random.randint(1, n)
        
        q_lis.append((u, v))

    return n, m, q, m_lis, q_lis

def line_no(): # All NO
    n = 1000
    m = n-1
    q = 1000
    m_lis = []
    q_lis = []

    for i in range(m):
        u = i+1
        v = i+2
        
        m_lis.append((u, v))

    for _ in range(q):
        u = random.randint(1, n-1)
        v = random.randint(u+1, n)
        while u == v:
            u = random.randint(1, n-1)
            v = random.randint(u+1, n)
        
        q_lis.append((u, v))

    return n, m, q, m_lis, q_lis

def line_yes(): # All YES
    n = 1000
    m = n-1
    q = 1000
    m_lis = []
    q_lis = []

    for i in range(m):
        u = i+1
        v = i+2
        
        m_lis.append((u, v))

    for _ in range(q):
        u = random.randint(1, n-1)
        v = random.randint(u+1, n)
        while u == v:
            u = random.randint(1, n-1)
            v = random.randint(u+1, n)
        
        q_lis.append((v, u))

    return n, m, q, m_lis, q_lis

def randGen2():
    n = random.randint(950, 1000)
    m = 500000
    q = random.randint(950, 1000)
    m_lis = []
    q_lis = []

    for _ in range(m):
        u = random.randint(1, n-1)
        v = random.randint(u+1, n)
        while u == v:
            u = random.randint(1, n-1)
            v = random.randint(u+1, n)
        
        m_lis.append((u, v))

    for _ in range(q):
        u = random.randint(1, n-1)
        v = random.randint(1, n)
        while u == v:
            u = random.randint(1, n-1)
            v = random.randint(1, n)
        
        q_lis.append((u, v))

    return n, m, q, m_lis, q_lis

def main():
    global tc
    prep()
    
    l = []

    for m in range(1, 19):
        l.append(randGen(2**(m-1)))

    l.append(line_no())
    l.append(line_yes())

    for _ in range(1, 5):
        l.append(randGen2())

    for x in l:
        writeTestCase(*x)

    zipper()


if __name__ == '__main__':
    main()
