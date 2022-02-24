# -*- coding: utf-8 -*-
# Feedback script
# Author: Sébastien Combéfis
# Date: November 17, 2012
# Problem: Mission 4 : Question de bilan final

from lib.pythia import *
from lib.french import *

class Q1Feedback (BasicFeedbackSuite):
    
    def __init__(self):
        predefined = [("AKAYAK",), ("AVABCD",), ("A",), ("AA",), ("",), ("ABCDEF",)]
        BasicFeedbackSuite.__init__(self, 'q1', predefined, FrenchFeedbackMessage())
    
    def parseTestdata(self, data):
        return tuple(str(x) for x in data)
    
    def parseAnswer(self, answer):
        return int(answer)
    
    def badvalue(self, args, value, exp):
        return '<p>Pour la chaine de caractères <code>s</code> valant &#171;&#160;{}&#160;&#187;, votre méthode renvoie <code>{}</code> alors qu\'elle devrait renvoyer <code>{}</code>.</p>'.format(args[0],str(value).lower(), str(exp).lower())
    
    def teacherCode(self,s):
        n=len(s)
        for i in range(n) : 
            #Recherche de palindrome dans les sous-chaines de longueur n-i
            for j in range(i+1) :
                #print i, j, s[j:n-i+j]
                if self._isPalindrome(s[j:n-i+j]) :
                    return n-i
        return 0
             
    def _isPalindrome(self,s):
        for i in range(int(len(s)/2)):
            if s[i] != s[len(s)-i-1] : 
                return False
        return True
Feedback([Q1Feedback()], FrenchFeedbackMessage()).generate()