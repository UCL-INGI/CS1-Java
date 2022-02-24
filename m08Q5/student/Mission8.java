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

public class Mission8
{
    private static String str = "Le code semble comporter des erreurs : ";
    /**
     * Default constructor for test class Employe
     */
    public Mission8()
    {
    }
    /* égalité de StringBuilder */
    public static boolean eq(StringBuilder b1, StringBuilder b2)
    {
        if(b1.length()!=b2.length())
            return false;
            
        for(int i=0;i<b1.length();i++) {
            if(b1.charAt(i)!=b2.charAt(i))
                return false;        
        }
        return true;
        
    }

    public static boolean eqb(boolean[] b1, boolean[] b2)
    {
        if(b1.length!=b2.length)
            return false;
            
        for(int i=0;i<b1.length;i++) {
            if(b1[i]!=b2[i])
                return false;        
        }
        return true;
        
    }
    
    /* constructueur */
    @Test
    public void testByteTab() 
    {
        str = "Question 1 :\n Le code semble comporter des erreurs : ";
        Mission8Stu m8s = new Mission8Stu();
        Mission8Stu.ByteTab b1, b2;
        boolean[] v;
        String msg;
        String msgE;
        String val;
        String init;
        String r;

        try {
            
            b1=m8s.new ByteTab();
            v=new boolean[] {false,false,false,false,false,false,false,false};
            msg=str+"Votre constructeur vide construit un tableau qui contient "+Arrays.toString(b1.getTab())+" alors que le contenu attendu est "+Arrays.toString(v)+"\n"; 
            assertTrue(msg,eqb(b1.getTab(),v));    
        }
        catch (Exception e) {
            msgE=str+"Votre constructeur vide a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        
        init="10000001";
        
        try {
            
            b1=m8s.new ByteTab(init);
            v=new boolean[] {true,false,false,false,false,false,false,true};
            msg=str+"Votre constructeur, lancé avec "+init+" comme argument construit un tableau qui contient "+Arrays.toString(b1.getTab())+" alors que le contenu attendu est "+Arrays.toString(v)+"\n"; 
            assertTrue(msg,eqb(b1.getTab(),v));    
        }
        catch (Exception e) {
            msgE=str+"Votre constructeur vide a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        
        init="11000001";
        
        try {
            
            b1=m8s.new ByteTab(init);
            v=new boolean[] {true,false,false,false,false,false,true,true};
            msg=str+"Votre constructeur, lancé avec "+init+" comme argument construit un tableau qui contient "+Arrays.toString(b1.getTab())+" alors que le contenu attendu est "+Arrays.toString(v)+"\n"; 
            assertTrue(msg,eqb(b1.getTab(),v));    
        }
        catch (Exception e) {
            msgE=str+"Votre constructeur a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        
    }
    
    @Test
    public void testSetBit()
    {
        str = "Question 1 :\n Le code semble comporter des erreurs : ";
        Mission8Stu m8s = new Mission8Stu();
        Mission8Stu.ByteTab b1, b2;
        String b;
        String v;
        
        String msg;
        String msgE;
        String val;
        int arg;
        
        b1=m8s.new ByteTab();
        try {
            arg=0;
            b1.setBit(arg);
            b=b1.toString();
            v="00000001";
            msg=str+"Votre méthode setbit ne semble pas fonctionner correctement lorsque son argument est "+arg+"\n"+b; 
            assertEquals(msg,b,v);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode setBit a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        
        b1=m8s.new ByteTab();
        try {
            arg=7;
            b1.setBit(arg);
            b=b1.toString();
            v="10000000";
            msg=str+"Votre méthode setBit ne semble pas fonctionner correctement lorsque son argument est "+arg+"\n"+b; 
            assertEquals(msg,b,v);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode setBit a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        
        b1=m8s.new ByteTab();
        try {
            arg=3;
            b1.setBit(arg);
            b=b1.toString();
            v="00001000";
            msg=str+"Votre méthode setBit ne semble pas fonctionner correctement lorsque son argument est "+arg+"\n"+b; 
            assertEquals(msg,b,v);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode setBit a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
    }
    
    @Test
    public void testResetBit()
    {
        str = "Question 1 :\n Le code semble comporter des erreurs : ";
        Mission8Stu m8s = new Mission8Stu();
        Mission8Stu.ByteTab b1, b2;
        String b;
        String v;
        
        String msg;
        String msgE;
        String val;
        int arg;
        
        b1=m8s.new ByteTab("00000001");
        try {
            arg=0;
            b1.resetBit(arg);
            b=b1.toString();
            v="00000000";
            msg=str+"Votre méthode resetBit ne semble pas fonctionner correctement lorsque son argument est "+arg+"\n"+b; 
            assertEquals(msg,b,v);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode setBit a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        
        b1=m8s.new ByteTab("01111010");
        try {
            arg=7;
            b1.setBit(arg);
            b=b1.toString();
            v="11111010";
            msg=str+"Votre méthode setBit ne semble pas fonctionner correctement lorsque son argument est "+arg+"\n"+b; 
            assertEquals(msg,b,v);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode setBit a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        
        b1=m8s.new ByteTab("01111010");
        try {
            arg=2;
            b1.setBit(arg);
            b=b1.toString();
            v="01111110";
            msg=str+"Votre méthode setBit ne semble pas fonctionner correctement lorsque son argument est "+arg+"\n"+b; 
            assertEquals(msg,b,v);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode setBit a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
    }
    
    @Test
    public void testShiftLeft()
    {
        str = "Question 1 :\n Le code semble comporter des erreurs : ";
        Mission8Stu m8s = new Mission8Stu();
        Mission8Stu.ByteTab b1, b2;
        String b;
        String v;
        
        String msg;
        String msgE;
        String val;
        int arg;
        String init=("10000001");
        
        
        b1=m8s.new ByteTab(init);
        try {
            b1.shiftLeft();
            b=b1.toString();
            v="00000010";
            msg=str+"Votre méthode shiftLeft ne semble pas fonctionner correctement lorsqu'elle est appliquée au byte dont la représentation naturelle est "+init+"\n"+"Son résultat est "+b+" alors que l'on attend "+v; 
            assertEquals(msg,b,v);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode shiftLeft a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        
        init="01101001";
        b1=m8s.new ByteTab(init);
        try {
            b1.shiftLeft();
            b=b1.toString();
            v="11010010";
            msg=str+"Votre méthode shiftLeft ne semble pas fonctionner correctement lorsqu'elle est appliquée au byte dont la représentation naturelle est "+init+"\n"+"Son résultat est "+b+" alors que l'on attend "+v; 
            assertEquals(msg,b,v);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode shiftLeft a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        
    }
    
    @Test
    public void rotateRight()
    {
        str = "Question 1 :\n Le code semble comporter des erreurs : ";
        Mission8Stu m8s = new Mission8Stu();
        Mission8Stu.ByteTab b1, b2;
        String b;
        String v;
        
        String msg;
        String msgE;
        String val;
        int arg;
        String init=("10000001");
        
        b1=m8s.new ByteTab(init);
        try {
            b1.rotateRight();
            b=b1.toString();
            v="11000000";
            msg=str+"Votre méthode rotateRight ne semble pas fonctionner correctement lorsqu'elle est appliquée au byte dont la représentation naturelle est "+init+"\n"+"Son résultat est "+b+" alors que l'on attend "+v; 
            assertEquals(msg,b,v);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode rotateRight a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        
        init="01101001";
        b1=m8s.new ByteTab(init);
        try {
            b1.rotateRight();
            b=b1.toString();
            v="10110100";
            msg=str+"Votre méthode rotateRight ne semble pas fonctionner correctement lorsqu'elle est appliquée au byte dont la représentation naturelle est "+init+"\n"+"Son résultat est "+b+" alors que l'on attend "+v; 
            assertEquals(msg,b,v);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode rotateRight a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        
    }
    
    @Test
    public void testgetBit()
    {
        str = "Question 1 :\n Le code semble comporter des erreurs : ";
        Mission8Stu m8s = new Mission8Stu();
        Mission8Stu.ByteTab b1, b2;
        StringBuilder b;
        StringBuilder v;
        
        String msg;
        String msgE;
        int val;
        int arg;
        
        b1=m8s.new ByteTab();
        try {
            arg=0;
            val=b1.getBit(arg);
            msg=str+"Votre méthode getBit ne semble pas fonctionner correctement lorsque son argument est "+arg+"\n"; 
            assertEquals(msg,val,0);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode getBit a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        
        b1=m8s.new ByteTab("10011001");
        try {
            arg=1;
            val=b1.getBit(arg);
            msg=str+"Votre méthode getBit ne semble pas fonctionner correctement lorsque son argument est "+arg+"\n"; 
            assertEquals(msg,val,0);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode getBit a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        
        b1=m8s.new ByteTab("10011011");
        try {
            arg=0;
            val=b1.getBit(arg);
            msg=str+"Votre méthode getBit ne semble pas fonctionner correctement lorsque son argument est "+arg+"\n"; 
            assertEquals(msg,val,1);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode getBit a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        b1=m8s.new ByteTab("10011010");
        try {
            arg=7;
            val=b1.getBit(arg);
            msg=str+"Votre méthode getBit ne semble pas fonctionner correctement lorsque son argument est "+arg+"\n"; 
            assertEquals(msg,val,1);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode getBit a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
    }
    
    @Test
    public void testAnd()
    {
        str = "Question 1 :\n Le code semble comporter des erreurs : ";
        Mission8Stu m8s = new Mission8Stu();
        Mission8Stu.ByteTab b1, b2;
        Mission8Stu.Byte r;
        String b;
        String v;
        
        String msg;
        String msgE;
        String val;
        int arg;
        String init1,init2;
        
        init1=("10000001");
        init2=("00000000");
        b1=m8s.new ByteTab(init1);
        b2=m8s.new ByteTab(init2);
        
        
        try {
            r=b1.and(b2);
            b=b1.toString();
            v=init1;
            msg=str+"Votre méthode and semble modifier this\n";
            assertEquals(msg,b,v);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode and a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        
        init1=("11111111");
        init2=("10000001");
        b1=m8s.new ByteTab(init1);
        b2=m8s.new ByteTab(init2);
        
        
        try {
            r=b1.and(b2);
            b=b1.toString();
            v=init2;
            msg=str+"Votre méthode and ne fonctionne pas correctement appliquée à "+b1.toString()+" lorsque son argument vaut "+b2.toString()+"\n"+"Le résultat obtenu est "+r.toString()+" alors que le résultat attendu est "+v;
            assertEquals(msg,r.toString(),v);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode and a lancé une exception! "+e.getMessage();
            fail(msgE);
            
        }
        
        init1=("10101010");
        init2=("01010101");
        b1=m8s.new ByteTab(init1);
        b2=m8s.new ByteTab(init2);
        
        
        try {
            r=b1.and(b2);
            b=b1.toString();
            v="00000000";
            msg=str+"Votre méthode and ne fonctionne pas correctement appliquée à "+b1.toString()+" lorsque son argument vaut "+b2.toString()+"\n"+"Le résultat obtenu est "+r.toString()+" alors que le résultat attendu est "+v;
            assertEquals(msg,r.toString(),v);    
        }
        catch (Exception e) {
            msgE=str+"Votre méthode and a lancé une exception! "+e.toString();
            fail(msgE);
            
        }
    }
    

    // Code verificateur
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(Mission8.class);
		for (Failure failure: result.getFailures()) {
			System.err.println(failure.toString());
			System.err.println(failure.getTestHeader() + " : " + failure.getException().toString());
		}
		if (result.wasSuccessful()) {
			System.out.println("Tous les tests se sont passés sans encombre");
			System.exit(127);
		}
	}

}
