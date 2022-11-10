#!/usr/bin/env python3

import pandas

path = 'data/AB_NYC_2019.csv'
data = pandas.read_csv(path)

mean_val = data['price'].mean()
variation = data['price'].var()

print(f'{mean_val} {variation}\n')