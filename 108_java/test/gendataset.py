# -*- coding: utf-8 -*-
# Test dataset script
# Author: Virginie Van den Schrieck
# Date: October 30, 2013
# Problem: Question de Bilan Final : Mission 6

from lib.pythia import *
import random
import string

class TestDataSetQ1(TestDataSet):
    def __init__(self):
        TestDataSet.__init__(self, 'employe', 0)
    
    def genTestData(self):
        return []

TestDataSetQ1().generate()