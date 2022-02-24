/**
 *  Copyright (c)  2016 Ludovic Taffin
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package student;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.Test;
import java.util.Random;
import org.junit.runner.notification.Failure;
import java.util.Arrays;

public class Mission10
{
    private static String str = "Le code semble comporter des erreurs : ";
    /**
     * Default constructor for test class Employe
     */
    public Mission10()
    {
    }

    public String studentToString(Mission10Stu.Student s)
    {
        String r="";
        r+=s.nom+";";
        for(int i=0;i<s.cotes.length;i++) {
            r+=s.cotes[i];
            if(i!=s.cotes.length-1)
                r+=",";
        }
        return r;
    }

    @Test
    public void testConstructeur()
    {
        try {

            str = "Question 1 :\n Le code semble comporter des erreurs : ";
            Mission10Stu m10s = new Mission10Stu();
            Mission10Stu.Student s;
            Mission10Stu.Student r;
            String msg, msge, msge2;
            String arg;
            String nom;



            double [] cotes;
            nom="Tintin";
            cotes=new double[] {1, 2, 3};

            r=m10s.new Student(nom,cotes);
            arg=studentToString(r);

            msg="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, vous avez créé une instance de la classe Student qui ne contient pas le bon nom ou les bonnes cotes";
            msge="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, votre code a lancé une exception de type StudentFormatException alors que la chaîne est complètement valide";
            msge2="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, votre code a lancé une exception ";
            try {

               s=m10s.new Student( arg);
               assertTrue(msg,r.same(s));
            }
            catch (StudentFormatException e) {
                fail(msge);
            }
            catch (Exception e) {
                fail(msge2);
            }

            cotes=new double[] {1, 2, 3, 2, 1, 3};

            r=m10s.new Student(nom,cotes);
            arg=studentToString(r);

            msg="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, vous avez créé une instance de la classe Student qui ne contient pas le bon nom ou les bonnes cotes";
            msge="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, votre code a lancé une exception de type StudentFormatException alors que la chaîne est complètement valide";
            msge2="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, votre code a lancé une exception ";
            try {

               s=m10s.new Student( arg);
               assertTrue(msg,r.same(s));
            }
            catch (StudentFormatException e) {
                fail(msge);
            }
            catch (Exception e) {
                fail(msge2);
            }

            cotes=new double[] {1, 2.2, 3, 2.9, 1, 3.98};

            r=m10s.new Student(nom,cotes);
            arg=studentToString(r);

            msg="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, vous avez créé une instance de la classe Student qui ne contient pas le bon nom ou les bonnes cotes";
            msge="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, votre code a lancé une exception de type StudentFormatException alors que la chaîne est complètement valide";
            msge2="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, votre code a lancé une exception ";
            try {

               s=m10s.new Student( arg);
               assertTrue(msg,r.same(s));
            }
            catch (StudentFormatException e) {
                fail(msge);
            }
            catch (Exception e) {
                fail(msge2);
            }

            arg="Tintin et Milou";
            msg="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, vous n'avez pas l'ancé d'exception de type StudentFormatException alors que la chaîne est invalide";
            msge="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, votre code a lancé une exception de type StudentFormatException alors que la chaîne est complètement valide";
            msge2="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, votre code a lancé une exception d'un autre type que StudentFormatException";
            try {

               s=m10s.new Student( arg);
               fail(msg);

            }
            catch (StudentFormatException e) {
               // correct
            }
            catch (Exception e) {
                fail(msge2);
            }

            arg="Tintin et Milou;";
            msg="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, vous n'avez pas l'ancé d'exception de type StudentFormatException alors que la chaîne est invalide";
            msge="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, votre code a lancé une exception de type StudentFormatException alors que la chaîne est complètement valide";
            msge2="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, votre code a lancé une exception d'un autre type que StudentFormatException";
            try {

               s=m10s.new Student( arg);
               fail(msg);

            }
            catch (StudentFormatException e) {
               // correct
            }
            catch (Exception e) {
                fail(msge2);
            }

            arg="Tintin et Milou;-1,2.0";
            msg="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, vous n'avez pas l'ancé d'exception de type StudentFormatException alors que la chaîne est invalide";
            msge="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, votre code a lancé une exception de type StudentFormatException alors que la chaîne est complètement valide";
            msge2="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, votre code a lancé une exception d'un autre type que StudentFormatException";
            try {

               s=m10s.new Student( arg);
               fail(msg);

            }
            catch (StudentFormatException e) {
               // correct
            }
            catch (Exception e) {
                fail(msge2);
            }

            arg="Tintin et Milou;1.2,21";
            msg="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, vous n'avez pas l'ancé d'exception de type StudentFormatException alors que la chaîne est invalide";
            msge="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, votre code a lancé une exception de type StudentFormatException alors que la chaîne est complètement valide";
            msge2="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, votre code a lancé une exception d'un autre type que StudentFormatException";
            try {

               s=m10s.new Student( arg);
               fail(msg);

            }
            catch (StudentFormatException e) {
               // correct
            }
            catch (Exception e) {
                fail(msge2);
            }

            arg="Tintin et Milou;1.2,test";
            msg="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, vous n'avez pas l'ancé d'exception de type StudentFormatException alors que la chaîne est invalide";
            msge2="Lors de l'appel à votre constructeur avec le String "+arg+" comme argument, votre code a lancé une exception d'un autre type que StudentFormatException";
            try {

               s=m10s.new Student( arg);
               fail(msg);

            }
            catch (StudentFormatException e) {
               // correct
            }
            catch (Exception e) {
                fail(msge2);
            }

        }
        catch (StudentFormatException e) {
            // ignored caught before
            fail("exception imprévue");
        }
    }

    // Code verificateur
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Mission10.class);
        for (Failure failure: result.getFailures()) {
            System.err.println(failure.getTestHeader() + " : " + failure.getException().toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("Tous les tests se sont passés sans encombre");
            System.exit(127);
        }
    }

}
