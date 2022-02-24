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

import student.Process;
import student.ComputerIF;
import StudentCode.Cluster;
import student.FullComputer;
import student.UnavailableException;
import student.BasicComputer;

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
	
	
	public ComputerIF getNextAvailableComputer(Cluster c, Process p){
		int count = c.get__C_oou__n_t();
		Cluster.ListNode current = c.get_____Ccur_ren_rt__();
		for(int i = 0 ; i < count ; i++){
			if(current.getElem().addProcess(p)){
				current.getElem().removeProcess(p);
				return current.getElem();
			}
			current = current.getNext();
		}
		return null;
	}
	
	
	/**
	 * 	Repris en partie de la méthode main de la classe Cluster, écrite par
	 * 	O. Bonaventure, Ch. Pecheur (Dec. 2007)
	 */
	@Test
	public void testAdded(){
		StringBuffer errorMessage = new StringBuffer();
		int clusterCount;
		boolean error = false;
		ComputerIF c1,c2,c3, c4;
    	c1=new FullComputer("joe",1,10);
    	c2=new FullComputer("jack",2,0);
    	c3=new FullComputer("bill",3,20);
    	c4=new BasicComputer("bob");

    	Cluster c=new Cluster();
    	c.addComputer(c1);
    	c.addComputer(c2);
    	c.addComputer(c3);
    	c.addComputer(c4);

    	Process p1,p2,p3,p4,p5;
    	p1=new Process("p1",0);
    	p2=new Process("p2",0);
    	p3=new Process("p3",2);
    	p4=new Process("p4",4);
    	p5=new Process("p5",1);
    	try
    	{
			boolean added, inside;
			try{
				c.addProcess(p1);
			}catch(UnavailableException e){
				error = true;
				errorMessage.append("\n"+ str + "addprocess a envoyé une UnavailableException quand on lui a demandé d'ajouter "
											  + "le processus ["+p1+"] à un cluster disposant des ressources nécessaires. ");
			}
    		try{
				c.removeProcess(p1);
			}catch(UnavailableException e){
				error = true;
				errorMessage.append("\n"+ str + "addprocess n'a pas ajouté le processus ["+p1+"] comme demandé. ");
			}
			// Déjà là s'il y a une erreur, on lance une AssertionError.
			if(error){
				throw new AssertionError(errorMessage.toString());
			}
			ComputerIF nextAvailable = getNextAvailableComputer(c, p1);
			try{
				c.addProcess(p1);
			}catch(UnavailableException e){
				error = true;
				errorMessage.append("\n"+ str + "addprocess a envoyé une UnavailableException quand on lui a demandé d'ajouter "
											  + "le processus ["+p1+"] à un cluster disposant des ressources nécessaires. ");
			}
			clusterCount = c.get__C_oou__n_t();
			Cluster.ListNode current = c.get_____Ccur_ren_rt__();
			// On se rend au noeud juste avant current (le noeud où le processus est sensé être ajouté)
			for(int i = 0 ; i < clusterCount -1 ; i++){
				current = current.getNext();
			}
			// On regarde si c'est bien là que le processus a été ajouté
			inside = current.getElem().removeProcess(p1);
			if(!inside){
				error = true;
				errorMessage.append("\n" + str + "addprocess n'a pas mis la tête du cluster sur le noeud suivant comme demandé après l'ajout d'un processus. ");
			}
			else if(current.getElem() != nextAvailable){
				error = true;
				errorMessage.append("\n" + str + "addprocess n'a pas ajouté le processus ["+p1+"] au premier ordinateur disponible comme demandé. ");
			}
			current.getElem().addProcess(p1);
			// Là, s'il y a une erreur, on lance une AssertionError.
			if(error){
				throw new AssertionError(errorMessage.toString());
			}
			
			// p2
			try{
				c.addProcess(p2);
			}catch(UnavailableException e){
				error = true;
				errorMessage.append("\n"+ str + "addprocess a envoyé une UnavailableException quand on lui a demandé d'ajouter "
											  + "le processus ["+p2+"] à un cluster disposant des ressources nécessaires. ");
			}
    		try{
				c.removeProcess(p2);
			}catch(UnavailableException e){
				error = true;
				errorMessage.append("\n"+ str + "addprocess n'a pas ajouté le processus ["+p2+"] comme demandé. ");
			}
			// Déjà là s'il y a une erreur, on lance une AssertionError.
			if(error){
				throw new AssertionError(errorMessage.toString());
			}
			nextAvailable = getNextAvailableComputer(c, p2);
			try{
				c.addProcess(p2);
			}catch(UnavailableException e){
				error = true;
				errorMessage.append("\n"+ str + "addprocess a envoyé une UnavailableException quand on lui a demandé d'ajouter "
											  + "le processus ["+p2+"] à un cluster disposant des ressources nécessaires. ");
			}
			clusterCount = c.get__C_oou__n_t();
			current = c.get_____Ccur_ren_rt__();
			// On se rend au noeud juste avant current (le noeud où le processus est sensé être ajouté)
			for(int i = 0 ; i < clusterCount -1 ; i++){
				current = current.getNext();
			}
			
			// On regarde si c'est bien là que le processus a été ajouté
			inside = current.getElem().removeProcess(p2);
			if(!inside){
				error = true;
				errorMessage.append("\n" + str + "addprocess n'a pas mis la tête du cluster sur le noeud suivant comme demandé après l'ajout d'un processus. ");
			}else if(current.getElem() != nextAvailable){
				error = true;
				errorMessage.append("\n" + str + "addprocess n'a pas ajouté le processus ["+p2+"] au premier ordinateur disponible comme demandé. ");
			}
			current.getElem().addProcess(p2);
			// Là, s'il y a une erreur, on lance une AssertionError.
			if(error){
				throw new AssertionError(errorMessage.toString());
			}
			
			
			// p3
			try{
				c.addProcess(p3);
			}catch(UnavailableException e){
				error = true;
				errorMessage.append("\n"+ str + "addprocess a envoyé une UnavailableException quand on lui a demandé d'ajouter "
											  + "le processus ["+p3+"] à un cluster disposant des ressources nécessaires. ");
			}
    		try{
				c.removeProcess(p3);
			}catch(UnavailableException e){
				error = true;
				errorMessage.append("\n"+ str + "addprocess n'a pas ajouté le processus ["+p3+"] comme demandé. ");
			}
			// Déjà là s'il y a une erreur, on lance une AssertionError.
			if(error){
				throw new AssertionError(errorMessage.toString());
			}
			nextAvailable = getNextAvailableComputer(c, p3);
			try{
				c.addProcess(p3);
			}catch(UnavailableException e){
				error = true;
				errorMessage.append("\n"+ str + "addprocess a envoyé une UnavailableException quand on lui a demandé d'ajouter "
											  + "le processus ["+p3+"] à un cluster disposant des ressources nécessaires. ");
			}
			clusterCount = c.get__C_oou__n_t();
			current = c.get_____Ccur_ren_rt__();
			// On se rend au noeud juste avant current (le noeud où le processus est sensé être ajouté)
			for(int i = 0 ; i < clusterCount -1 ; i++){
				current = current.getNext();
			}
			
			// On regarde si c'est bien là que le processus a été ajouté
			inside = current.getElem().removeProcess(p3);
			if(!inside){
				error = true;
				errorMessage.append("\n" + str + "addprocess n'a pas mis la tête du cluster sur le noeud suivant comme demandé après l'ajout d'un processus. ");
			}else if(current.getElem() != nextAvailable){
				error = true;
				errorMessage.append("\n" + str + "addprocess n'a pas ajouté le processus ["+p3+"] au premier ordinateur disponible comme demandé. ");
			}
			current.getElem().addProcess(p3);
			// Là, s'il y a une erreur, on lance une AssertionError.
			if(error){
				throw new AssertionError(errorMessage.toString());
			}
			
			
			// p4
			try{
				c.addProcess(p4);
			}catch(UnavailableException e){
				error = true;
				errorMessage.append("\n"+ str + "addprocess a envoyé une UnavailableException quand on lui a demandé d'ajouter "
											  + "le processus ["+p4+"] à un cluster disposant des ressources nécessaires. ");
			}
    		try{
				c.removeProcess(p4);
			}catch(UnavailableException e){
				error = true;
				errorMessage.append("\n"+ str + "addprocess n'a pas ajouté le processus ["+p4+"] comme demandé. ");
			}
			// Déjà là s'il y a une erreur, on lance une AssertionError.
			if(error){
				throw new AssertionError(errorMessage.toString());
			}
			nextAvailable = getNextAvailableComputer(c, p4);
			try{
				c.addProcess(p4);
			}catch(UnavailableException e){
				error = true;
				errorMessage.append("\n"+ str + "addprocess a envoyé une UnavailableException quand on lui a demandé d'ajouter "
											  + "le processus ["+p4+"] à un cluster disposant des ressources nécessaires. ");
			}
			clusterCount = c.get__C_oou__n_t();
			current = c.get_____Ccur_ren_rt__();
			// On se rend au noeud juste avant current (le noeud où le processus est sensé être ajouté)
			for(int i = 0 ; i < clusterCount -1 ; i++){
				current = current.getNext();
			}
			
			// On regarde si c'est bien là que le processus a été ajouté
			inside = current.getElem().removeProcess(p4);
			if(!inside){
				error = true;
				errorMessage.append("\n" + str + "addprocess n'a pas mis la tête du cluster sur le noeud suivant comme demandé après l'ajout d'un processus. ");
			}else if(current.getElem() != nextAvailable){
				error = true;
				errorMessage.append("\n" + str + "addprocess n'a pas ajouté le processus ["+p4+"] au premier ordinateur disponible comme demandé. ");
			}
			current.getElem().addProcess(p4);
			// Là, s'il y a une erreur, on lance une AssertionError.
			if(error){
				throw new AssertionError(errorMessage.toString());
			}
			
			// p5
			try{
				c.addProcess(p5);
			}catch(UnavailableException e){
				error = true;
				errorMessage.append("\n"+ str + "addprocess a envoyé une UnavailableException quand on lui a demandé d'ajouter "
											  + "le processus ["+p5+"] à un cluster disposant des ressources nécessaires. ");
			}
    		try{
				c.removeProcess(p5);
			}catch(UnavailableException e){
				error = true;
				errorMessage.append("\n"+ str + "addprocess n'a pas ajouté le processus ["+p5+"] comme demandé. ");
			}
			// Déjà là s'il y a une erreur, on lance une AssertionError.
			if(error){
				throw new AssertionError(errorMessage.toString());
			}
			nextAvailable = getNextAvailableComputer(c, p5);
			try{
				c.addProcess(p5);
			}catch(UnavailableException e){
				error = true;
				errorMessage.append("\n"+ str + "addprocess n'a pas ajouté le processus ["+p5+"] comme demandé. ");
			}
			clusterCount = c.get__C_oou__n_t();
			current = c.get_____Ccur_ren_rt__();
			// On se rend au noeud juste avant current (le noeud où le processus est sensé être ajouté)
			for(int i = 0 ; i < clusterCount -1 ; i++){
				current = current.getNext();
			}
			
			// On regarde si c'est bien là que le processus a été ajouté
			inside = current.getElem().removeProcess(p5);
			if(!inside){
				error = true;
				errorMessage.append("\n" + str + "addprocess n'a pas mis la tête du cluster sur le noeud suivant comme demandé après l'ajout d'un processus. ");
			}else if(current.getElem() != nextAvailable){
				error = true;
				errorMessage.append("\n" + str + "addprocess n'a pas ajouté le processus ["+p5+"] au premier ordinateur disponible comme demandé. ");
			}
			current.getElem().addProcess(p5);
			// Là, s'il y a une erreur, on lance une AssertionError.
			if(error){
				throw new AssertionError(errorMessage.toString());
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
			e.printStackTrace();
			fail(str + "Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			e.printStackTrace();
		} catch (Exception e) {
			fail(str + "\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 	Repris en partie de la méthode main de la classe Cluster, écrite par
	 * 	O. Bonaventure, Ch. Pecheur (Dec. 2007)
	 */
	@Test
	public void testLaunchException(){
		StringBuffer errorMessage = new StringBuffer();
		int clusterCount;
		boolean error = false;
		ComputerIF c1,c2,c3, c4;
    	c1=new FullComputer("joe",1,10);
    	c2=new FullComputer("jack",2,0);
    	c3=new FullComputer("bill",3,20);
    	c4=new BasicComputer("bob");

    	Cluster c=new Cluster();
    	c.addComputer(c1);
    	c.addComputer(c2);
    	c.addComputer(c3);
    	c.addComputer(c4);

    	Process p1,p2,p3,p4,p5;
    	p1=new Process("p1",10000);
    	p2=new Process("p2",0);
    	p3=new Process("p3",2);
    	p4=new Process("p4",4);
    	p5=new Process("p5",1);
    	try{
			final Cluster.ListNode current = c.get_____Ccur_ren_rt__();
			boolean launched = false;
			try{
				c.addProcess(p1);
			}catch(UnavailableException e){
				launched = true;
			}catch(Exception e){
				fail(str + "addProcess a lancé une autre exception que UnavailableException lors de l'ajout du processus ["+p1+"] : "+e.getMessage());
			}
			if(!launched){
				fail(str + "addProcess() n'a pas lancé d'UnavailableException lors de l'ajout du processus ["+p1+"]. ");
			}
			if(current != c.get_____Ccur_ren_rt__()){
				fail(str + "lorsqu'un processus n'a pas pu être ajouté, la tête du cluster a changé. ");
			}
			launched = false;
			Cluster cl2 = new Cluster();
			cl2.addComputer(new BasicComputer("a"));
			try{
				cl2.addProcess(p3);
			}catch(UnavailableException e){
				launched = true;
			}catch(Exception e){
				fail(str + "addProcess a lancé une autre exception que UnavailableException lors de l'ajout du processus ["+p3+"] : "+e.getMessage());
			}
			if(!launched){
				fail(str + "addProcess() n'a pas lancé d'UnavailableException lors de l'ajout du processus ["+p3+"]. (pas assez de storage disponible sur les ordinateurs du cluster)") ;
			}
			if(current != c.get_____Ccur_ren_rt__()){
				fail(str + "lorsqu'un processus n'a pas pu être ajouté, la tête du cluster a changé. ");
			}
			FullComputer b = new FullComputer("b", 1 , 100); 
			b.addProcess(p3);
			cl2.addComputer(b);
			try{
				cl2.addProcess(p4);
			}catch(UnavailableException e){
				launched = true;
			}catch(Exception e){
				fail(str + "addProcess a lancé une autre exception que UnavailableException lors de l'ajout du processus ["+p3+"] : "+e.getMessage());
			}
			if(!launched){
				fail(str + "addProcess() n'a pas lancé d'UnavailableException lors de l'ajout du processus ["+p3+"]. (Plus de slots disponibles sur les ordinateurs du cluster)") ;
			}
			if(current != c.get_____Ccur_ren_rt__()){
				fail(str + "lorsqu'un processus n'a pas pu être ajouté, la tête du cluster a changé. ");
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
			e.printStackTrace();
			fail(str + "Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			e.printStackTrace();
		} catch (Exception e) {
			fail(str + "\n" + e.getMessage());
			e.printStackTrace();
		}
	}
}
