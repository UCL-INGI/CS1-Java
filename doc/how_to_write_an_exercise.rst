Writing a Java exercise
=======================

To write a java exercise, the first file you need is config.json at the root of the exercise.

This file must have the following fields :

- ``'customscript': nameScript`` in order to use an additionnal correction script (to check if Math is imported for example).
     If there are no additionnal script needed (nothing else than the test to check), you should set it to custom.sh else the code will not work.
	
- ``'execcustom': 0 or 1`` in order to indicate if the additionnal script should be executed (1) or not (0).
     If there are no additionnal script, you should set it to 0.
     If set to 1, the program that runs before the tests will execute the script.
     If an error occurs in the custom script, the error message will be displayed but your script needs to set the message itself with::

     feedback -i id_question -r failed -f "Your error message"
     exit(1)
	
- ``'nexercices'`` which represents the number of exercices in a single task. This will be helpful to indicate which question does not work in a single task.
    In case of failure, the program that launches the tests will look for the one who failed and set the error message of the test as the global feedback message.
    If you don't put the right number, it will still show the error message but it will be displayed in a code box with an additional sentence saying that "the code did not work"
    rather than having just the error message displayed in the case you did put the right number of exercises.

Here is an example of the file ::

     {"customscript":"custom.sh","execcustom":0,"nexercices":1}

Then, you need a directory called 'Templates' which will have '.tmpl'. Those files will have the answers of students. This file should begin by 'package StudentCode;', it should contains a class with a lign '@    @id_problem@@'.

Here is an example with an exercise on an average::

    package StudentCode;

    public class Student {
        public static double average(double a, double b, double c){
	    double average=0;
	    @	    @q1@@
	    return average;
        }
    }

A second directory called 'src' should be created. This folder will have the tests of the exercise in 'Tests.java' and optionally the correction in 'Correction.java'.

Here is an example of the 'Tests.java'::

    package src;

    import org.junit.Test;
    import static org.junit.Assert.*;
    import student.Translations.Translator;
    import StudentCode.*;
    import java.util.Random;
    import java.text.MessageFormat;

    public class Tests {

        public void testAverage() {
            Random r = new Random();
            int a = r.nextInt(2000) - 1000;
            int b = r.nextInt(2000) - 1000;
            int c = r.nextInt(2000) - 1000;
            double resultStudent = Student.average(a, b, c);
            double result = (a + b + c) / 3;
            String form = Translator.translate("La moyenne entre {0,number,#}, {1,number,#} et {2,number,#} vaut {3,number,#}, or votre programme calcule {4,number,#}.\n");
            assertTrue(MessageFormat.format(form, a, b, c, result, resultStudent), result == resultStudent);
        }

        @Test
        public void testLauncher(){
            int nbTests = 4;
            try{
                for(int i = 0; i < nbTests; i++){
                    testAverage();
                }
            }catch (ArithmeticException e){
                fail(Translator.translate("Attention, il est interdit de diviser par zéro."));
            }catch(ClassCastException e){
                fail(Translator.translate("Attention, certaines variables ont été mal castées !"));
            }catch(StringIndexOutOfBoundsException e){
                fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)"));
            }catch(ArrayIndexOutOfBoundsException e){
                fail(Translator.translate("Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)"));
            }catch(NullPointerException e){
                fail(Translator.translate("Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas."));
            }catch(Exception e){
                fail(Translator.translate("Une erreur inattendue est survenue dans votre tâche : ") + e.toString());
            }
        }
    }

Lastly, we need a 'student' folder with files called 'MessagesBundle_en.properties' for example which will hold the translations of the feedback.
Each line should have the original feedback and the translated feedback.

An example of line is : ``Impossible\ de\ d\u00e9coder\ le\ config.json=Unable to decode config.json``

Lastly, we need a 'student' folder with files called 'MessagesBundle_en.properties' for example which will hold the translations of the feedback. Each line should have the original feedback and the translated feedback.

Here is the structure you should have ::

	../id_exercise
	├── config.json
	├── src
	│   ├── Correction.java
	│   └── Tests.java
	├── student
	│   ├── MessagesBundle_en.properties
	│   └── MessagesBundle_es.properties
	└── Templates
	    └── Student.tmpl

Environment
-----------

In the environment tab of the task, you should select Docker as the environment type if you plan to do a coding question.

If that is the case you should then select java8judge as the environment which is a special container with different global files useful for exercises (the program to launch the tests for example).
Those files will be used in your exercise, thus you do not need to think about those files.

You must also precise the timeout limit and the memory limit in case of a 'malicious code' from a student. The other parameters are optional.


Subproblem
----------

You should enter a problem id and the problem type (code). Once you added this, you must enter a name for the question (Average xith 3 numbers for example),
a context to precise a specific detail, the language and then you can save the changes.
