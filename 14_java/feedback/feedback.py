# -*- coding: utf-8 -*

import sys,os

errpath = 'output/files/q1.err'
if os.path.exists(errpath):
    with open(errpath, 'r', encoding = 'utf-8') as file:
        error = file.read()
        if error != '':
             with open('feedback.xml', 'w', encoding = 'utf-8') as file:
                file.write('<feedback><question id="q1" verdict="KO"><![CDATA[<p>Votre fichier n\'a pas compilé ou une erreur d\'exécution s\'est produite :</p><pre>{}</pre>]]></question><verdict>KO</verdict></feedback>'.format(error))
                sys.exit(0)

resultpath = 'output/files/resultat.txt'
if os.path.exists(resultpath):
    with open(resultpath, 'r', encoding = 'utf-8') as file:
        for currLine in file:
            linePart = currLine.split(":")
            if linePart[0] == "KO":
                with open('feedback.xml', 'w', encoding = 'utf-8') as feedbackfile:
                    feedbackfile.write('<feedback><question id="q1" verdict="KO"><![CDATA[<p>{}</p>]]></question><verdict>KO</verdict></feedback>'.format(linePart[1]))
                    sys.exit(0)
        
        with open('feedback.xml', 'w', encoding = 'utf-8') as feedbackfile:
            feedbackfile.write('<feedback><question id="q1" verdict="OK"><![CDATA[<p>Votre classe stack semble fonctionner correctement.</p>]]></question><verdict>OK</verdict></feedback>')