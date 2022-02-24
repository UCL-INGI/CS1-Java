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

    @Test
    public void testSet()
    {
        str = "Question 4 :\n Le code semble comporter des erreurs : ";
        Mission10Stu m10s = new Mission10Stu();
        Mission10Stu.AList l=null;
        String msg,msge;
        String a,b,c,d,x;
        a="a";
        b="b";
        c="c";
        d="d";
        x="x";
        int index;
        Object r,val;
        Object[] ol,newol;
        l=m10s.new AList();
        ol=l.getTab();
        ol[0]=a;
        ol[1]=b;
        ol[2]=c;
        ol[3]=d;


        index=0;
        try {
            l.set(index,x);
            newol=l.getTab();
            Object o=ol[index];
            ol[index]=x;
            msg=str+"Lors de l'appel à la méthode set avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre méthode ne modifie pas correctement le tableau";
            assertTrue(msg,Arrays.equals(ol,newol));
            ol[index]=o;
        }
        catch (Exception e) {
            msge=str+"Lors de l'appel à la méthode set avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre code a provoqué une exception "+e;
            fail(msge);
        }

        index=1;
        try {
            l.set(index,x);
            newol=l.getTab();
            Object o=ol[index];
            ol[index]=x;
            msg=str+"Lors de l'appel à la méthode set avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre méthode ne modifie pas correctement le tableau";
            assertTrue(msg,Arrays.equals(ol,newol));
            ol[index]=o;
        }
        catch (Exception e) {
            msge=str+"Lors de l'appel à la méthode set avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre code a provoqué une exception "+e;
            fail(msge);
        }

        index=3;
        try {
            l.set(index,x);
            newol=l.getTab();
            Object o=ol[index];
            ol[index]=x;
            msg=str+"Lors de l'appel à la méthode set avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre méthode ne modifie pas correctement le tableau";
            assertTrue(msg,Arrays.equals(ol,newol));
            ol[index]=o;
        }
        catch (Exception e) {
            msge=str+"Lors de l'appel à la méthode set avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre code a provoqué une exception "+e;
            fail(msge);
        }

        index=4;
        try {
            l.set(index,x);
            newol=l.getTab();
            Object o=ol[index];
            ol[index]=x;
            msg=str+"Lors de l'appel à la méthode set avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre méthode ne modifie pas correctement le tableau";
            assertTrue(msg,Arrays.equals(ol,newol));
            ol[index]=o;
        }
        catch (Exception e) {
            msge=str+"Lors de l'appel à la méthode set avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre code a provoqué une exception "+e;
            fail(msge);
        }

    }

    @Test
    public void testAdd()
    {
        str = "Question 3 :\n Le code semble comporter des erreurs : ";
        Mission10Stu m10s = new Mission10Stu();
        Mission10Stu.AList l=null;
        String msg,msge;
        String a,b,c,d,x;
        a="a";
        b="b";
        c="c";
        d="d";
        x="x";
        int index;
        Object r,val;
        Object arg;

        l=m10s.new AList();
        index=0;
        arg=a;
        try {
            val=a;
            msg=str+"Après exécution de votre méthode add avec comme arguments (index="+index+",Object="+arg+") sur la liste contenant "+Arrays.toString(l.getTab())+", votre méthode get a retourné l'objet ";
            l.add(index,arg);
            r=l.get(index);
            msg=msg+r+" alors que l'objet attendu était "+val;
            assertEquals(msg,arg,r);
        }
        catch (Exception e) {
                  msge=str+"L'exécution de votre méthode add avec comme arguments (index="+index+",Object="+arg+") sur la liste contenant "+Arrays.toString(l.getTab())+" a provoqué une exception "+e;
                  fail(msge);
        }

        l=m10s.new AList();
        index=0;
        arg=a;
        try {
            val=a;
            Object[] lBefore, lAfter;
            lBefore=l.getTab();
            msg=str+"L'exécution de votre méthode add avec comme arguments (index="+index+",Object="+arg+") sur la liste contenant "+Arrays.toString(l.getTab())+" a proovoqué la création d'un nouveau tableau alors qu'il y avait suffisamment de place dans la tableau existant pour stocker ce nouvel objet à la position demandée";
            l.add(index,arg);
            lAfter=l.getTab();
            assertTrue(msg,lBefore==lAfter);
        }
        catch (Exception e) {
                  msge=str+"L'exécution de votre méthode add avec comme arguments (index="+index+",Object="+arg+") sur la liste contenant "+Arrays.toString(l.getTab())+" a provoqué une exception "+e;
                  fail(msge);
        }

        l=m10s.new AList();
        l.add(0,a);
        msg=str+"Après exécution de la méthode add, votre list ne contient pas l'élément ajouté";
        assertEquals(msg,l.get(0),a);
        l.add(1,b);
        assertEquals(msg,l.get(1),b);
        l.add(2,c);
        assertEquals(msg,l.get(2),c);
        l.add(3,d);
        assertEquals(msg,l.get(3),d);
        index=4;
        arg=x;
        try {
            val=x;
            msg=str+"Après exécution de votre méthode add avec comme arguments (index="+index+",Object="+arg+") sur la liste contenant "+Arrays.toString(l.getTab())+", votre méthode get a retourné l'objet ";
            l.add(index,arg);
            r=l.get(index);
            msg=msg+r+" alors que l'objet attendu était "+val;
            assertEquals(msg,arg,r);
        }
        catch (Exception e) {
                  msge=str+"L'exécution de votre méthode add avec comme arguments (index="+index+",Object="+arg+") sur la liste contenant "+Arrays.toString(l.getTab())+" a provoqué une exception "+e;
                  fail(msge);
        }


        index=5;
        arg=x;
        try {
            val=x;
            msg=str+"Après exécution de votre méthode add avec comme arguments (index="+index+",Object="+arg+") sur la liste contenant "+Arrays.toString(l.getTab())+", votre méthode get a retourné l'objet ";
            l.add(index,arg);
            r=l.get(index);
            msg=msg+r+" alors que l'objet attendu était "+val;
            assertEquals(msg,arg,r);
        }
        catch (Exception e) {
                  msge=str+"L'exécution de votre méthode add avec comme arguments (index="+index+",Object="+arg+") sur la liste contenant "+Arrays.toString(l.getTab())+" a provoqué une exception "+e;
                  fail(msge);
        }


        l=m10s.new AList();
        l.add(0,a);
        l.add(1,b);
        l.add(2,c);
        l.add(3,d);
        index=5;
        arg=x;
        try {
            val=x;
            msg=str+"Après exécution de votre méthode add avec comme arguments (index="+index+",Object="+arg+") sur la liste contenant "+Arrays.toString(l.getTab())+", votre méthode get a retourné l'objet ";
            l.add(index,arg);
            r=l.get(index);
            msg=msg+r+" alors que l'objet attendu était "+val;
            assertEquals(msg,arg,r);
        }
        catch (Exception e) {
                  msge=str+"L'exécution de votre méthode add avec comme arguments (index="+index+",Object="+arg+") sur la liste contenant "+Arrays.toString(l.getTab())+" a provoqué une exception "+e;
                  fail(msge);
        }

        l=m10s.new AList();
        l.add(0,a);
        l.add(1,b);
        l.add(2,c);
        l.add(3,d);
        index=5;
        arg=x;
        try {
            val=a;
            Object[] lBefore, lAfter;
            lBefore=l.getTab();
            msg=str+"L'exécution de votre méthode add avec comme arguments (index="+index+",Object="+arg+") sur la liste contenant "+Arrays.toString(l.getTab())+" n'a pas provoqué la création d'un nouveau tableau alors qu'il n'y avait pas suffisamment de place dans la tableau existant pour stocker ce nouvel objet à la position demandée";
            l.add(index,arg);
            lAfter=l.getTab();
            assertTrue(msg,lBefore!=lAfter);

        }
        catch (Exception e) {
                  msge=str+"L'exécution de votre méthode add avec comme arguments (index="+index+",Object="+arg+") sur la liste contenant "+Arrays.toString(l.getTab())+" a provoqué une exception "+e;
                  fail(msge);
        }


    }

    @Test
    public void testGet()
    {
        str = "Question 2 :\n Le code semble comporter des erreurs : ";
        Mission10Stu m10s = new Mission10Stu();
        Mission10Stu.AList l=null;
        String msg,msge;
        String a,b,c,d,ee,f;
        a="a";
        b="b";
        c="c";
        d="d";
        ee="e";
        int index;
        Object r,val;
        Object[] ol;
        l=m10s.new AList();
        ol=l.getTab();
        ol[0]=a;
        ol[1]=b;
        ol[2]=c;
        ol[3]=d;


        index=0;
        try {

            r=l.get(index);
            val=ol[index];
            msg=str+"Lors de l'appel à la méthode get avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre méthode retourne l'objet "+r+" alors que la valeur attendue est "+val;
            assertEquals(msg,val,r);
        }
        catch (Exception e) {
            msge=str+"Lors de l'appel à la méthode get avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre code a provoqué une exception "+e;
            fail(msge);
        }

        index=1;
        try {

            r=l.get(index);
            val=ol[index];
            msg=str+"Lors de l'appel à la méthode get avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre méthode retourne l'objet "+r+" alors que la valeur attendue est "+val;
            assertEquals(msg,val,r);
        }
        catch (Exception e) {
            msge=str+"Lors de l'appel à la méthode get avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre code a provoqué une exception "+e;
            fail(msge);
        }


        index=3;
        try {

            r=l.get(index);
            val=ol[index];
            msg=str+"Lors de l'appel à la méthode get avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre méthode retourne l'objet "+r+" alors que la valeur attendue est "+val;
            assertEquals(msg,val,r);
        }
        catch (Exception e) {
            msge=str+"Lors de l'appel à la méthode get avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre code a provoqué une exception "+e;
            fail(msge);
        }

        index=6;
        try {

            r=l.get(index);
            val=null;
            msg=str+"Lors de l'appel à la méthode get avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre méthode retourne l'objet "+r+" alors que la valeur attendue est "+val;
            assertTrue(msg,val==r);
        }
        catch (Exception e) {
            msge=str+"Lors de l'appel à la méthode get avec "+index+" comme argument et sur une liste contenant les Strings suivants "+Arrays.toString(ol)+", votre code a provoqué une exception "+e;
            fail(msge);
        }
    }


    @Test
    public void testContains()
    {
        str = "Question 1 :\n Le code semble comporter des erreurs : ";
        Mission10Stu m10s = new Mission10Stu();
        Mission10Stu.AList l=null;
        String msg,msge;
        String a,b,c,d,ee,f;
        a="a";
        b="b";
        c="c";
        d="d";
        ee="e";
        Object arg;
        boolean val,r;

        Object[] ol=null;
        msg=str+"Lors de l'appel à votre constructeur,Java a lancé une exception ";
        try {
           l=m10s.new AList();
           ol=l.getTab();
           ol[0]=a;
           ol[1]=b;
           ol[2]=c;
           ol[3]=d;
        }
        catch (Exception e) {
            fail(msg+e);
        }


        arg=a;
        val=true;
        msge=str+"Votre méthode contains a lancé une exception lors de l'appel à la méthode sur une AList contenant :"+Arrays.toString(ol)+" avec comme argument "+arg;

        try {
            r=l.contains(arg);
            msg=str+"Votre méthode contains, appelée avec comme argument le String contenant "+arg+" sur une AList contenant :"+Arrays.toString(ol)+" a retrouné "+r+" alors que la valeur de retour attendue était "+val;
            assertEquals(msg,val,r);
        }
        catch (Exception e) {
            fail(msge+e);
        }

        arg=c;
        val=true;
        msge=str+"Votre méthode contains a lancé une exception lors de l'appel à la méthode sur une AList contenant :"+Arrays.toString(ol)+" avec comme argument "+arg;
        try {
            r=l.contains(arg);
            msg=str+"Votre méthode contains, appelée avec comme argument le String contenant "+arg+" sur une AList contenant :"+Arrays.toString(ol)+" a retrouné "+r+" alors que la valeur de retour attendue était "+val;
            assertEquals(msg,val,r);
        }
        catch (Exception e) {
            fail(msge+e);
        }

        arg=d;
        val=true;
        msge=str+"Votre méthode contains a lancé une exception lors de l'appel à la méthode sur une AList contenant :"+Arrays.toString(ol)+" avec comme argument "+arg;

        try {
            r=l.contains(arg);
            msg=str+"Votre méthode contains, appelée avec comme argument le String contenant "+arg+" sur une AList contenant :"+Arrays.toString(ol)+" a retrouné "+r+" alors que la valeur de retour attendue était "+val;
            assertEquals(msg,val,r);
        }
        catch (Exception e) {
            fail(msge+e);
        }

        arg=ee;
        val=false;
        msge=str+"Votre méthode contains a lancé une exception lors de l'appel à la méthode sur une AList contenant :"+Arrays.toString(ol)+" avec comme argument "+arg;

        try {
            r=l.contains(arg);
            msg=str+"Votre méthode contains, appelée avec comme argument le String contenant "+arg+" sur une AList contenant :"+Arrays.toString(ol)+" a retrouné "+r+" alors que la valeur de retour attendue était "+val;
            assertEquals(msg,val,r);
        }
        catch (Exception e) {
            fail(msge+e);
        }

        arg=new Integer(1);
        val=false;
        msge=str+"Votre méthode contains a lancé une exception lors de l'appel à la méthode sur une AList contenant :"+Arrays.toString(ol)+" avec comme argument "+arg;

        try {
            r=l.contains(arg);
            msg=str+"Votre méthode contains, appelée avec comme argument le String contenant "+arg+" sur une AList contenant :"+Arrays.toString(ol)+" a retrouné "+r+" alors que la valeur de retour attendue était "+val;
            assertEquals(msg,val,r);
        }
        catch (Exception e) {
            fail(msge+e);
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
