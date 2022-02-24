# -*- coding: utf-8 -*-
# Feedback script
# Author: Sébastien Combéfis
# Date: December 23, 2012
# Problem: Question de Bilan Final : Mission 3

from lib.pythia import *
from lib.french import *

class Q1Feedback (BasicFeedbackSuite):
    
    def __init__(self):
        predefined = [(0,1), (0,1), (1,0), (-1,0), (0, -1), (0, 2), (-2, 0), (-2,2), (-2,-2), (0,-2), (2,-2)]
        #predefined = [(0,), (1,), (9,), (10,), (283,)]
        BasicFeedbackSuite.__init__(self, 'q1', predefined, FrenchFeedbackMessage())
    
    def parseTestdata(self, data):
        return tuple(str(x) for x in data)
    
    def parseAnswer(self, answer):        
        return int(answer)
    
    def badvalue(self, args, value, exp):        
        return '<p>Pour <code>a</code> valant &#171;&#160;{}&#160;&#187; et pour <code>b</code> valant &#171;&#160;{}&#160;&#187;, votre méthode renvoie <code>{}</code> alors qu\'elle devrait renvoyer <code>{}</code>.</p>'.format(args[0], args[1], str(value).lower(), str(exp).lower())
    
    
    def teacherCode(self, a, b):        
        a = max(int(a), -1)
        b = max(int(b), -1)
        return max(abs(b-a)-1,0)
Feedback([Q1Feedback()], FrenchFeedbackMessage()).generate()