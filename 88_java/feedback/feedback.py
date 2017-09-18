# -*- coding: utf-8 -*-
# Feedback script
# Author: Sébastien Combéfis
# Date: December 23, 2012
# Problem: Question de Bilan Final : Mission 3

from lib.pythia import *
from lib.french import *

class Q1Feedback (BasicFeedbackSuite):
    
    def __init__(self):
        predefined = [(0,0,0,0,0), (1,2,3,4,5), (0,2,1,0,10), (-1,0, 9 , 9,9)]
        #predefined = [(0,), (1,), (9,), (10,), (283,)]
        BasicFeedbackSuite.__init__(self, 'q1', predefined, FrenchFeedbackMessage())
    
    def parseTestdata(self, data):
        return tuple(str(x) for x in data)
    
    def parseAnswer(self, answer):
        return int(answer)
    
    def badvalue(self, args, value, exp):
        return '<p>Lorsque les arguments sont <code>{}</code>, votre méthode <code>CountNumIntBetween</code> renvoie <code>{}</code> alors qu\'elle devrait renvoyer <code>{}</code>.</p>'.format(args[0:5],str(value).lower(), str(exp).lower())
    
    def teacherCode(self, a, b, c, d, e):
        return len(set([a,b,c,d,e]))

Feedback([Q1Feedback()], FrenchFeedbackMessage()).generate()