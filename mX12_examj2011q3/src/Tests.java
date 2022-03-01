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
import org.junit.runners.MethodSorters;

import org.junit.FixMethodOrder;

import StudentCode.Process;
import student.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
	public void testBasic(){
		Random r = new Random();
		try{
			StringBuffer errorMessage = new StringBuffer();
			Process p1 = new Process("init", 0);
			String s = p1.getName();
			int storage = p1.getRequiredStorage();
			int pid = p1.getPid();
			System.out.println(pid);
			boolean error = false;
			try{
				assertTrue(str + "lorsque l'on crée un processus avec le nom \"init\", la méthode getName() renvoie \""+s+"\". ", "init".equals(s));
			}catch(AssertionError e){
				error = true;
				errorMessage.append("\n");
				errorMessage.append(e.getMessage());
			}
			try{
				assertTrue(str + "lorsque l'on crée un processus avec le storage 0, la méthode getRequiredStorage() renvoie "+storage+". ", 0 == storage);
			}catch(AssertionError e){
				error = true;
				errorMessage.append("\n");
				errorMessage.append(e.getMessage());
			}
			try{
				assertTrue(str + "le premier processus créé ne dispose pas du pid numéro 1 (getPid() renvoie "+pid+"). ", 1 == pid);
			}catch(AssertionError e){
				error = true;
				errorMessage.append("\n");
				errorMessage.append(e.getMessage());
			}
			
			if(error){
				throw new AssertionError(errorMessage.toString());
			}
			Process oldProcess = p1;
			for(int i = 2 ; i < 15 ; i++){
				String name = generateString(r.nextInt(7) + 3);
				int storageExp = r.nextInt(201);
				Process p = new Process(name, storageExp);
				s = p.getName();
				pid = p.getPid();
				storage = p.getRequiredStorage();
				try{
					assertTrue(str + "lorsque l'on crée un processus avec le nom \""+name+"\", la méthode getName() renvoie \""+s+"\". ", name.equals(s));
				}catch(AssertionError e){
					error = true;
					errorMessage.append("\n");
					errorMessage.append(e.getMessage());
				}
				try{
					assertTrue(str + "lorsque l'on crée un processus avec le storage "+storageExp+", la méthode getRequiredStorage() renvoie "+storage+". ",
																																	storageExp == storage);
				}catch(AssertionError e){
					error = true;
					errorMessage.append("\n");
					errorMessage.append(e.getMessage());
				}
				try{
					assertTrue(str + "le "+i+"ème processus créé ne dispose pas du pid numéro "+i+" (getPid() renvoie "+pid+"). ", i == pid);
				}catch(AssertionError e){
					error = true;
					errorMessage.append("\n");
					errorMessage.append(e.getMessage());
				}
				
				try{
					assertTrue(str + "pour un même Processus, la méthode getPid() ne renvoie plus ne même pid qu'avant, lorsqu'un nouveau processus a été créé.", i-1 == oldProcess.getPid());
				}catch(AssertionError e){
					error = true;
					errorMessage.append("\n");
					errorMessage.append(e.getMessage());
				}
				if(error){
					throw new AssertionError(errorMessage.toString());
				}
				oldProcess = p;
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
	public void testGetDescr(){
		Random r = new Random();
		try{
			String nameExp = "firefox";
			int storageExp = 0;
			Process p = new Process(nameExp, storageExp);
			String desc = p.getDescr();
			String descExp = nameExp + " " + storageExp;
			String[] tab = desc.split(" ");
			if(tab.length < 2){
				fail(str + "le format de getDescr() doit être : \"nom requiredStorage\", or, getDescr() renvoie \""+desc+"\". ");
			}
			assertTrue(str + "getDescr() renvoie une mauvaise réponse. Au lieu de renvoyer \""+descExp+"\", getDescr() "
							 + "renvoie \""+desc+"\". ", descExp.equals(desc));
			for(int i = 0 ; i < 20 ; i++){
				nameExp = generateString(r.nextInt(7) + 3);
				storageExp = r.nextInt(100);
				p = new Process(nameExp, storageExp);
				desc = p.getDescr();
				descExp = nameExp + " " + storageExp;
				tab = desc.split(" ");
				if(tab.length < 2){
					fail(str + "le format de getDescr() doit être : \"nom requiredStorage\", or, getDescr() renvoie \""+desc+"\". ");
				}
				assertTrue(str + "getDescr() renvoie une mauvaise réponse pour le processus \"" + tab[0] + "\". Au lieu de renvoyer \""+descExp+"\", getDescr() "
								 + "renvoie \""+desc+"\". ", descExp.equals(desc));
				
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