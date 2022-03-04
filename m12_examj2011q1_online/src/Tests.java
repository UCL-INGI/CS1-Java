package src;


/**
 *  @author François MICHEL
 */

import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.*;
import java.util.Random;
import org.junit.runner.notification.Failure;
import java.io.*;
import java.util.Arrays;

import StudentCode.BasicComputer;
import student.ComputerIF;
import student.UnavailableException;
import student.FullComputer;
import student.Process;


public class Tests {

	private static String str = "Votre code semble comporter quelques erreurs : ";


	public static String generateString(int length){
		String s = "";
		Random r = new Random();
		for(int i = 0 ; i < length ; i++){
			s += (char) ((r.nextInt('z' - 'a') + 'a'));
		}
		return s;
	}


	@Test
	public void testRemoveOther(){
		Random r = new Random();
		try{
			BasicComputer bc = new BasicComputer("ordi1");
			Process p = new Process("firefox", 0);
			Process pOther = new Process("init", 0);
			bc.addProcess(p);
			boolean b;
			b = bc.removeProcess(pOther);
			assertFalse(str + "removeProcess a renvoyé true lorsqu'on lui demande de retirer le processus \"init\" alors que le processus actif est \"firefox\". ", b);
			assertNotNull(str + "removeProcess a retiré le processus nommé \"firefox\" alors qu'on lui a demandé de retirer le processus \"init\".",
							bc.getProcess());
			bc.removeAllProcesses();
			for(int i = 0 ; i < 20 ; i++){
				p = new Process(generateString(r.nextInt(7) + 3), 0);
				bc.addProcess(p);
				pOther = new Process(generateString(r.nextInt(7) + 3), 0);
				b = bc.removeProcess(pOther);
				assertFalse(str + "removeProcess a renvoyé true lorsqu'on lui demande de retirer le processus dont le pid est "
								+pOther.getPid()+" alors que le processus actif possède le pid "+p.getPid()+". ", b);
				assertNotNull(str + "removeProcess a retiré le processus dont le pid est "+p.getPid()+" alors qu'on lui a demandé de retirer "
								  +	"le processus dont le pid est " + pOther.getPid() + ". ", bc.getProcess());
				bc.removeAllProcesses();
			}
		} catch (ArithmeticException e) {
			fail(str + "Le code est incorrect : il est interdit de diviser par zéro.");
			e.printStackTrace();
		} catch (ClassCastException e) {
			fail(str + "Attention, certaines variables ont été mal castées	!");
			e.printStackTrace();
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			fail(str + "Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			fail(str + "Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)");
			e.printStackTrace();
		} catch (NullPointerException e) {
			fail(str + "Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			e.printStackTrace();
		} catch (Exception e) {
			fail(str + "\n" + e.getMessage());
			e.printStackTrace();
		}
	}


	@Test
	public void testRemoveOtherSameAttributes(){
		Random r = new Random();
		try{
			BasicComputer bc = new BasicComputer("ordi1");
			Process p = new Process("firefox", 0);
			Process pOther = new Process("firefox", 0);
			bc.addProcess(p);
			boolean b;
			b = bc.removeProcess(pOther);
			assertFalse(str + "removeProcess a renvoyé true lorsqu'on lui demande de retirer le processus \"firefox\" dont le pid est "+
							pOther.getPid()+" alors que le processus actif est un autre \"firefox\" dont le pid est "+p.getPid()+". ", b);
			assertNotNull(str + "remove process a retiré le processus nommé \"firefox\" dont le pid est "+p.getPid()
							  + " alors qu'on lui a demandé de retirer un autre \"firefox\" dont le pid est "+pOther.getPid()+". ", bc.getProcess());
			bc.removeAllProcesses();
			for(int i = 0 ; i < 20 ; i++){
				String name = generateString(r.nextInt(7) + 3);
				p = new Process(name, 0);
				bc.addProcess(p);
				pOther = new Process(name, 0);
				b = bc.removeProcess(pOther);
				assertFalse(str + "removeProcess a renvoyé true lorsqu'on lui demande de retirer le processus dont le pid est "
								+pOther.getPid()+" alors que le processus actif possède le même nom et storage mais dont le pid "+p.getPid()+". ", b);
				assertNotNull(str + "removeProcess a retiré le processus dont le pid est "+p.getPid()+" alors qu'on lui a demandé de retirer "
								  +	"le processus qui possède le même nom et storage mais dont le pid est " + pOther.getPid() + ". ", bc.getProcess());
				bc.removeAllProcesses();
			}
		} catch (ArithmeticException e) {
			fail(str + "Le code est incorrect : il est interdit de diviser par zéro.");
			e.printStackTrace();
		} catch (ClassCastException e) {
			fail(str + "Attention, certaines variables ont été mal castées	!");
			e.printStackTrace();
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			fail(str + "Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			fail(str + "Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)");
			e.printStackTrace();
		} catch (NullPointerException e) {
			fail(str + "Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			e.printStackTrace();
		} catch (Exception e) {
			fail(str + "\n" + e.getMessage());
			e.printStackTrace();
		}
	}


	@Test
	public void testRemove(){
		Random r = new Random();
		try{
			BasicComputer bc = new BasicComputer("ordi1");
			Process p = new Process("firefox", 0);
			bc.addProcess(p);
			boolean b;
			b = bc.removeProcess(p);
			assertTrue(str + "removeProcess a renvoyé false au lieu de true lorsqu'on lui demande de retirer son processus actif. ", b);
			assertNull(str + "removeProcess n'a pas mis le processus de l'ordinateur à null quand on lui demande de retirer son processus actif.",
							bc.getProcess());
			bc.removeAllProcesses();
			for(int i = 0 ; i < 20 ; i++){
				p = new Process(generateString(r.nextInt(7) + 3), 0);
				bc.addProcess(p);
				b = bc.removeProcess(p);
				assertTrue(str + "removeProcess a renvoyé false au lieu de true lorsqu'on lui demande de retirer son processus actif. ", b);
				assertNull(str + "removeProcess n'a pas mis le processus de l'ordinateur à null quand on lui demande de retirer son processus actif.",
							bc.getProcess());
				bc.removeAllProcesses();
			}
		} catch (ArithmeticException e) {
			fail(str + "Le code est incorrect : il est interdit de diviser par zéro.");
			e.printStackTrace();
		} catch (ClassCastException e) {
			fail(str + "Attention, certaines variables ont été mal castées	!");
			e.printStackTrace();
		} catch (StringIndexOutOfBoundsException e) {
			e.printStackTrace();
			fail(str + "Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			fail(str + "Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)");
			e.printStackTrace();
		} catch (NullPointerException e) {
			fail(str + "Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			e.printStackTrace();
		} catch (Exception e) {
			fail(str + "\n" + e.getMessage());
			e.printStackTrace();
		}
	}
}
