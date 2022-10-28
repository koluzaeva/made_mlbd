#!/usr/bin/env python3

import sys
import csv

data = sys.stdin.readlines()
input = csv.reader(data)
head = next(input)

cnt = 0
mean_val = 0
variation = 0

for line in input:
	mean_val += float(line[9])
	variation += float(line[9]) ** 2
	cnt += 1

mean_val /= cnt
variation = variation / cnt - mean_val ** 2

sys.stdout.write(f'{cnt} {mean_val} {variation}\n')
