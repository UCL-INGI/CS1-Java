# -*- coding: utf-8 -*-
# Test dataset script
# Author: Sébastien Combéfis
# Date: December 23, 2012
# Problem: Question de Bilan Final : Mission 3

from lib.pythia import *
import random
import string

class TestDataSetQ1(TestDataSet):
    def __init__(self):
        TestDataSet.__init__(self, 'q1', 100)
    
    def genTestData(self):
        n1 = random.randint(1, 2147483647)
        n2 = random.randint(1, 2147483647)
        if n2 == n1 : 
            n2++; 
        if n1 < n2 : 
            return [n1, n2]
        return [n2, n1]

TestDataSetQ1().generate()