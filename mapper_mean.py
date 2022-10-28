#!/usr/bin/python3

import sys
import csv

cnt = 0
mean_val = 0

data = sys.stdin.readlines()
input = csv.reader(data)
head = next(input)

for line in input:
    mean_val += float(line[9])
    cnt += 1

mean_val /= cnt
sys.stdout.write('{}\t{}\n'.format(cnt, meanval))
