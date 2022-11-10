#!/usr/bin/env python3

import sys

m_j = 0.0
v_j = 0.0
c_j = 0
for line in sys.stdin:
    c_k, m_k, v_k = line.strip().split('\t')
    c_k = int(c_k)
    m_k = float(m_k)
    v_k = float(v_k)

    v_j = (v_j * c_j + v_k * c_k) / (c_j + c_k) + c_j * c_k * ((m_j - m_k) / (c_j + c_k)) ** 2
    m_j = (m_j * c_j + m_k * c_k) / (c_j + c_k)
    c_j += c_k

sys.stdout.write(f'variation {v_j}\n')