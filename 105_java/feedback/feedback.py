# -*- coding: utf-8 -*

import sys,os

errpath = 'output/files/q1.err'
if os.path.exists(errpath):
    with open(errpath, 'r', encoding = 'utf-8') as file:
        error = file.read()
        if error != '':
             with open('feedback.xml', 'w', encoding = 'utf-8') as file:
                file.write('<feedback>')
              
                file.write('<general><![CDATA[<p>Votre fichier n\'a pas compilé ou une erreur d\'exécution s\'est produite :</p><pre>{}</pre>]]> </general>'.format(error))                
                file.write('<verdict>KO</verdict>')
                file.write('</feedback>')
                sys.exit(0)

resultpath = 'output/files/resultat.txt'
if os.path.exists(resultpath):
    with open(resultpath, 'r', encoding = 'utf-8') as file:
        currLine = file.readline().strip()
        if currLine=="OK": 
            with open('feedback.xml', 'w', encoding = 'utf-8') as feedbackfile:
                feedbackfile.write('<feedback>')
                feedbackfile.write('<general><![CDATA[<p>Votre code a passé avec succès tous les tests pour cette mission. </p>]]></general>')
                feedbackfile.write('<verdict>OK</verdict>')
                feedbackfile.write('</feedback>')
            sys.exit(0)
        else :             
            errors={}
            general = []
            for curLine in file: 
                msg = ""  
                curLine = curLine.strip();
                if(curLine[0]=="F") : 
                    elems = curLine.split(':')
                    classe = elems[1]; 
                    method = elems[2];
                    msg = "Echec : la classe "+ classe + " n'a pas passé les tests"
                    if(method!="") : 
                        msg += " (méthode {}()) ".format(method)                    
                    if(classe in errors.keys()) : 
                         errors[classe].append(msg)
                    else : 
                         errors[classe] = [msg]
                elif(curLine[0])=="E" : 
                    elems = curLine.split(':')
                    classe = elems[1]; 
                    method = elems[2];
                    msg = "Exception : la classe "+ classe + " a lancé une exception "+elems[3]
                    if(method!="") : 
                        msg += " (méthode {}()) ".format(method)                    
                    if(classe in errors.keys()) : 
                         errors[classe].append(msg)
                    else : 
                         errors[classe] = [msg]                   
                else : 
                    general.append(curLine);
                                                                                                                                
            with open('feedback.xml', 'w', encoding = 'utf-8') as feedbackfile:
                feedbackfile.write('<feedback>\n')
                for method in errors.keys() : 
                    feedbackfile.write('\t<question id="{}" verdict="KO">\n\t\t<![CDATA[\n'.format(method))
                    for err_msg in errors[method] : 
                        feedbackfile.write('<p>{}</p>\n'.format(err_msg))
                    feedbackfile.write(']]>\n\t</question>\n')
                if len(general)>0 : 
                    feedbackfile.write('<general><![CDATA[')
                    for gen_err in general : 
                        feedbackfile.write('<p>{}</p>\n'.format(gen_err)); 
                    feedbackfile.write(']]></general>\n')#.format(errors))
                feedbackfile.write('\t<verdict>KO</verdict>\n')
                feedbackfile.write('</feedback>')                                
            sys.exit(0)
                    
            
        
        