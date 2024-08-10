#! /usr/bin/python3
import random
import os
from re import X
import shutil
import subprocess
import time
import zipfile
import math
import networkx

random.seed(19832)
tc = 0

def writeTestCase(n, t, adj, tasks):
    global tc
    tc += 1
    print(tc)
    filename = 'input/input%02d.txt' % tc
    with open(filename, 'w') as fout:
        print(str(n) + " " + str(t), file=fout)
        for (u, v) in adj:
            print(str(u) + " " + str(v), file=fout)
        for (a, b) in tasks:
            print(str(a) + " " + str(b), file=fout)

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

def randGen(iter):
    n = random.randint(90000, 100000)

    if iter < 5:
        t = random.randint(1, 10)
    elif iter < 10:
        t = random.randint(10, 100)
    elif iter < 15:
        t = random.randint(100, 1000)
    elif iter < 20:
        t = random.randint(1000, 10000)
    elif iter < 25:
        t = random.randint(10000, 100000)

    adj = set()
    tasks = set()
    
    tree = networkx.random_tree(n=n)
    for edge in tree.edges:
        adj.add((edge[0] + 1, edge[1] + 1))

    while len(tasks) < t:
        a = random.randint(1, n)
        b = random.randint(1, n)

        tasks.add((a, b))
    
    return (n, t, adj, tasks)

def easy():
    n = random.randint(5, 20)
    t = random.randint(1, n)

    adj = set()
    tasks = set()
    
    tree = networkx.random_tree(n=n)
    for edge in tree.edges:
        adj.add((edge[0] + 1, edge[1] + 1))

    while len(tasks) < t:
        a = random.randint(1, n)
        b = random.randint(1, n)

        tasks.add((a, b))
    
    return (n, t, adj, tasks)

def main():
    global tc
    prep()
    
    l = []

    for _ in range(1, 5):
        l.append(easy())
    
    for iter in range(1, 25):
        l.append(randGen(iter))

    for x in l:
        writeTestCase(*x)

    zipper()


if __name__ == '__main__':
    main()
