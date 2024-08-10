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
        print(len(lis), file=fout)
        for l in lis:
            print(len(l), end=" ", file=fout)
            print(" ".join(str(x) for x in l), file=fout)

    with open('output/output%02d.txt' % tc, 'w') as outf:
        with open(filename, 'r') as inf:
            subprocess.call(['../ac/jimmy'],
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

primes = [2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179,181,191,193,197,199,211,223,227,229,233,239,241,251,257,263,269,271,277,281,283,293,307,311,313,317,331,337,347,349,353,359,367,373,379,383,389,397,401,409,419,421,431,433,439,443,449,457,461,463,467,479,487,491,499,503,509,521,523,541,547,557,563,569,571,577,587,593,599,601,607,613,617,619,631,641,643,647,653,659,661,673,677,683,691,701,709,719,727,733,739,743,751,757,761,769,773,787,797,809,811,821,823,827,829,839,853,857,859,863,877,881,883,887]
def randGen(prime=False):
    n = 100
    
    invalid = random.randint(0, n//20)
    lis = []

    for i in range(invalid):
        s = random.randint(2, 1000)
        l = []
        for j in range(1, s+1):
            l.append(j)
        random.shuffle(l)
        lis.append(l)

    for i in range(n - invalid):
        s = random.randint(2, 1000)
        l = []
        rand = random.randint(max(s-20, 0), s-1)
        if prime:
            rand = primes[random.randint(0, len(primes) - 1)] % s
        for j in range(rand, s+1):
            l.append(j)
        for j in range(1, rand):
            l.append(j)
        lis.append(l)

    random.shuffle(lis)
    return lis

def randGen2(prime=False):
    n = 100
    
    invalid = random.randint(0, n//20)
    lis = []

    for i in range(invalid):
        s = random.randint(2, 1000)
        l = []
        for j in range(1, s+1):
            l.append(j)
        random.shuffle(l)
        lis.append(l)

    for i in range(n - invalid):
        s = random.randint(10, 30)
        l = []
        rand = random.randint(20, 30)
        if prime:
            rand = primes[random.randint(0, len(primes) - 1)] % s
        for j in range(rand, s+1):
            l.append(j)
        for j in range(1, rand):
            l.append(j)
        lis.append(l)

    random.shuffle(lis)
    return lis

def randGen3(prime=False):
    n = 100
    
    invalid = random.randint(0, n//20)
    lis = []

    for i in range(invalid):
        s = random.randint(2, 1000)
        l = []
        for j in range(1, s+1):
            l.append(j)
        random.shuffle(l)
        lis.append(l)

    for i in range(n - invalid):
        s = random.randint(900, 920)
        l = []
        rand = random.randint(max(s-20, 0), s-1)
        if prime:
            rand = primes[random.randint(0, len(primes) - 1)] % s
        for j in range(rand, s+1):
            l.append(j)
        for j in range(1, rand):
            l.append(j)
        lis.append(l)

    random.shuffle(lis)
    return lis

highly_comp = [12, 24, 36, 48, 60, 120, 180, 240, 360, 720, 840]
def randGen4(prime=False):
    n = 100
    
    invalid = random.randint(0, n//20)
    lis = []

    for i in range(invalid):
        s = random.randint(2, 1000)
        l = []
        for j in range(1, s+1):
            l.append(j)
        random.shuffle(l)
        lis.append(l)

    for i in range(n - invalid):
        s = highly_comp[random.randint(0, len(highly_comp) - 1)]
        l = []
        rand = random.randint(max(s-20, 0), s-1)
        if prime:
            rand = primes[random.randint(0, len(primes) - 1)] % s
        for j in range(rand, s+1):
            l.append(j)
        for j in range(1, rand):
            l.append(j)
        lis.append(l)

    random.shuffle(lis)
    return lis

def randGen5(prime=False):
    n = 100
    
    invalid = random.randint(0, n//20)
    lis = []

    for i in range(invalid):
        s = random.randint(2, 1000)
        l = []
        for j in range(1, s+1):
            l.append(j)
        random.shuffle(l)
        lis.append(l)

    for i in range(n - invalid):
        s = highly_comp[random.randint(0, len(highly_comp) - 1)]
        l = []
        rand = random.randint(max(s-10, 0), s-1)
        for j in range(rand, s+1):
            l.append(j)
        for j in range(1, rand):
            l.append(j)
        lis.append(l)

    random.shuffle(lis)
    return lis

def stress(prime=False):
    n = 100
    
    invalid = random.randint(0, n//20)
    lis = []

    for i in range(invalid):
        s = random.randint(2, 1000)
        l = []
        for j in range(1, s+1):
            l.append(j)
        random.shuffle(l)
        lis.append(l)

    for i in range(n - invalid):
        s = 1000
        l = []
        rand = random.randint(1, s)
        if prime:
            rand = primes[random.randint(0, len(primes) - 1)] % s
        for j in range(rand, s+1):
            l.append(j)
        for j in range(1, rand):
            l.append(j)
        lis.append(l)

    random.shuffle(lis)
    return lis

def stress2(prime=False):
    n = 100
    
    invalid = random.randint(0, n//20)
    lis = []

    for i in range(invalid):
        s = random.randint(2, 1000)
        l = []
        for j in range(1, s+1):
            l.append(j)
        random.shuffle(l)
        lis.append(l)

    for i in range(n - invalid):
        s = 1000
        l = []
        rand = 232
        for j in range(rand, s+1):
            l.append(j)
        for j in range(1, rand):
            l.append(j)
        lis.append(l)

    random.shuffle(lis)
    return lis

def stress3(prime=False):
    n = 100
    
    invalid = random.randint(0, n//20)
    lis = []

    for i in range(invalid):
        s = random.randint(2, 1000)
        l = []
        for j in range(1, s+1):
            l.append(j)
        random.shuffle(l)
        lis.append(l)

    for i in range(n - invalid):
        s = 1000 - i
        l = []
        rand = random.randint(1, s)
        if prime:
            rand = primes[random.randint(0, len(primes) - 1)] % s
        for j in range(rand, s+1):
            l.append(j)
        for j in range(1, rand):
            l.append(j)
        lis.append(l)

    random.shuffle(lis)
    return lis

def main():
    global tc
    prep()
    
    l = []

    for _ in range(1, 5):
        l.append(randGen())
    for _ in range(1, 4):
        l.append(randGen2())
    for _ in range(1, 5):
        l.append(randGen3())
    for _ in range(1, 8):
        l.append(randGen4())
    for _ in range(1, 8):
        l.append(randGen5())

    l.append(stress())
    l.append(stress2())
    l.append(stress3())

    # for _ in range(1, 5):
    #     l.append(randGen(True))
    # for _ in range(1, 4):
    #     l.append(randGen2(True))
    # for _ in range(1, 5):
    #     l.append(randGen3(True))
    # for _ in range(1, 8):
    #     l.append(randGen4(True))
    # for _ in range(1, 8):
    #     l.append(randGen5(True))

    # l.append(stress(True))
    # l.append(stress2(True))
    # l.append(stress3(True))


    # random.shuffle(l)

    for x in l:
        writeTestCase(x)

    
    zipper()


if __name__ == '__main__':
    main()
