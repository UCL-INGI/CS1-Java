package src;


/**
 *  @author François MICHEL
 */


// TODO: changer le equals de process ? parce que ça bug pour le moment

import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.*;
import java.util.Random;
import org.junit.runner.notification.Failure;
import java.io.*;
import java.util.ArrayList;
import java.security.Permission;

import StudentCode.Cluster;
import student.ComputerIF;
import student.UnavailableException;
import student.BasicComputer;
import student.FullComputer;
import student.Process;


public class Tests {

	private static String str = "Votre code semble comporter quelques erreurs : ";


	/**
	 * 	Interdire le System.exit(). Repris de : http://stackoverflow.com/questions/5401281/preventing-system-exit-from-api
	 */
	private static void forbidSystemExit() {
		final SecurityManager securityManager = new SecurityManager() {
			public void checkPermission(Permission permission) {
				// Si l'on veut faire System.exit
				if (permission.getName().contains("exitVM")) {
					// On balance une exception
					throw new SecurityException("hello");
				}
			}
		};
		System.setSecurityManager(securityManager);
	}

	/**
	 * 	Interdire le System.exit(). Repris de : http://stackoverflow.com/questions/5401281/preventing-system-exit-from-api
	 */
	private static void allowSystemExit() {
		System.setSecurityManager(null);
	}

	public static String generateString(int length) {
		String s = "";
		Random r = new Random();
		for (int i = 0; i < length; i++) {
			s += (char)((r.nextInt('z' - 'a') + 'a'));
		}
		return s;
	}

	private boolean emptyCluster(Cluster c) {
		int count = c.get__C_oou__n_t();
		Cluster.ListNode current = c.get_____Ccur_ren_rt__();
		for (int i = 0; i < count; i++) {
			if (!((BasicComputer) current.getElem()).is__E_mmp__t_yyy_()) {
				return false;
			}
			current = current.getNext();
		}
		return true;
	}

	@Test
	public void testSave() {
		ArrayList < Process > listProc;
		BufferedReader br;
		String line;
		String[] tab;
		
		Cluster c = new Cluster();
		ComputerIF c1 = new BasicComputer("mac");
		ComputerIF c2 = new BasicComputer("mac2");
		ComputerIF c3 = new FullComputer("FullC", 5, 10);
		ComputerIF c4 = new FullComputer("Brandon", 1, 10);
		ComputerIF c5 = new FullComputer("Oli", 4, 15);
		ComputerIF c6 = new FullComputer("Rob", 3, 10);
		ComputerIF c7 = new FullComputer("FouleCompiouteure", 4, 10);
		c.addComputer(c1);
		c.addComputer(c2);
		c.addComputer(c3);
		c.addComputer(c4);
		c.addComputer(c5);
		c.addComputer(c6);
		c.addComputer(c7);
		
		try {
			String[] files = { "../student/save1.txt", "../student/save2.dat" };
			for (String filename: files) {
				// On remet la variable globale à 1 pour comparer les PRocess entre eux
				Process.s___e_T_gLL__oBa_l__(1);
				listProc = new ArrayList < Process > ();
				try {
					System.out.println(filename);
					br = new BufferedReader(new FileReader(filename));
					line = br.readLine();
					while (line != null) {
						tab = line.split(" ");
						try{
							listProc.add(new Process(tab[0], Integer.parseInt(tab[1])));
						}catch(ArrayIndexOutOfBoundsException e){
							fail("Erreur dans le format des fichiers à lire. Veuillez réessayer plus tard ou contacter un responsable. (OutOfBounds)");
						}catch(NumberFormatException e){
							fail("Erreur dans le format des fichiers à lire. Veuillez réessayer plus tard ou contacter un responsable. (NumberFormat)");
						}
						line = br.readLine();
					}
					br.close();
				} catch (IOException e) {
					fail("Une erreur interne à la question est survenue. Réessayez plus tard ou contactez un responsable. (IOException)");
				} catch (NumberFormatException e) {
					fail("Une erreur interne à la question est survenue. Réessayez plus tard ou contactez un responsable. (NumberFormat)");
				}
				try {
					for (Process p: listProc) {
						c.addProcess(p);
					}
				} catch (UnavailableException e) {
					fail("Une erreur interne à la question est survenue. Veuillez réessayer plus tard ou contacter un responsable. (UnavailableException)");
				}
				boolean error = false;
				StringBuffer sb = new StringBuffer();
				// On remet la variable globale à 1 pour comparer les PRocess entre eux
				Process.s___e_T_gLL__oBa_l__(1);
				forbidSystemExit();
				try {
					c.loadState(filename);
				} catch (SecurityException e) {
					fail(str + "votre méthode ferme le programme alors que le cluster contient les ressources nécessaires pour charger le fichier et qu'aucune erreur d'IO ne devrait apparaître.");
				}
				allowSystemExit();
				// On regarde si le nombre d'ordis (7) a changé
				if (7 != c.get__C_oou__n_t()) {
					error = true;
					sb.append("\n" + str + "le nombre d'ordinateurs du cluster a changé mais ne devrait pas. ");
				}
				try {
					System.out.println(c);
					System.out.println(listProc.size() + filename);
					for (int i = 0 ; i < listProc.size() ; i++) {
						System.out.println("removing : "+listProc.get(i));
						c.removeProcess(listProc.get(i));
					}
				} catch (UnavailableException e) {
					error = true;
					sb.append("\n" + str + "tous les processus n'ont pas été chargés depuis le fichier.");
				}
				Cluster.ListNode current = c.get_____Ccur_ren_rt__();
                
				if (!error && !emptyCluster(c)) {
					error = true;
					sb.append("\n" + str + "la méthode loadState n'a soit pas supprimé tous les processus déjà présents, soit a créé des processus en trop.");
				}
				if(error){
					fail(sb.toString());
				}
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
	
	@Test
	public void TestError(){
		Cluster c = new Cluster();
		c.addComputer(new BasicComputer("jeanjean"));
		String[] files = { "student/save1.txt", "student/save2.dat" };
		PrintStream err = System.err;
		try{
			for(String filename : files){
				boolean launched = false;
				forbidSystemExit();
				System.setErr(new PrintStream(new OutputStream() {
					public void write(int b) {
						// On cache le system.err
					}
				}));
				try{
					c.loadState(filename);
				}catch(SecurityException e){
					launched = true;
				}
				System.setErr(err);
				if(!launched){
					fail(str + "la méthode loadState() n'a pas fermé le programme lorsque le cluster n'a pas assez de ressources pour charger le fichier demandé");
				}
				allowSystemExit();
			}
		}catch(ArithmeticException e){
			fail(str+"Le code est incorrect : il est interdit de diviser par zéro.");
			e.printStackTrace();
		}catch(ClassCastException e){
			fail(str+"Attention, certaines variables ont été mal castées	!");
			e.printStackTrace();
		}catch(StringIndexOutOfBoundsException e){
			e.printStackTrace();
			fail(str+"Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			fail(str+"Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)");
			e.printStackTrace();
		}catch(NullPointerException e){
			fail(str+"Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			e.printStackTrace();
		}catch(Exception e){
			fail(str + "\n" + e.getMessage());
			e.printStackTrace();
		}
		finally{
			System.setErr(err);
		}
	}
	
	@Test
	public void testIOException(){
		Random r = new Random();
		int res = 10;
		Cluster c = new Cluster();
		ComputerIF c1 = new BasicComputer("mac");
		ComputerIF c2 = new BasicComputer("mac2");
		ComputerIF c3 = new FullComputer("FullC", 5, 10);
		ComputerIF c4 = new FullComputer("Brandon", 1, 10);
		ComputerIF c5 = new FullComputer("Oli", 4, 15);
		ComputerIF c6 = new FullComputer("Rob", 3, 10);
		ComputerIF c7 = new FullComputer("FouleCompiouteure", 4, 10);
		c.addComputer(c1);
		c.addComputer(c2);
		c.addComputer(c3);
		c.addComputer(c4);
		c.addComputer(c5);
		c.addComputer(c6);
		c.addComputer(c7);
		PrintStream err = System.err;
		try{
			String filename = "student/"+generateString(7);
			File f = new File(filename);
			if(!f.createNewFile()){
				fail("Erreur de création de fichier, veuillez réessayer plus tard ou prévenir un responsable");
			}
			if(!f.setReadable(false)){
				fail("Erreur de changement de permission de fichier, veuillez réessayer plus tard ou prévenir un responsable");
			}
			boolean launched = false;
			forbidSystemExit();
			System.setErr(new PrintStream(new OutputStream() {
				public void write(int b) {
					// On cache le system.err
				}
			}));
			try{
				c.loadState(filename);
			}catch(SecurityException e){
				launched = true;
			}
			allowSystemExit();
			System.setErr(err);
			if(!launched){
				fail(str + "la méthode loadState() n'a pas fermé le programme lorsqu'une IOException est apparue.");
			}
		}catch(IOException e){
			fail(str+"Une IOException est apparue, or, votre code ne fait rien pour la traiter.");
			e.printStackTrace();
		}catch(ArithmeticException e){
			fail(str+"Le code est incorrect : il est interdit de diviser par zéro.");
			e.printStackTrace();
		}catch(ClassCastException e){
			fail(str+"Attention, certaines variables ont été mal castées	!");
			e.printStackTrace();
		}catch(StringIndexOutOfBoundsException e){
			e.printStackTrace();
			fail(str+"Attention, vous tentez de lire en dehors des limites d'un String ! (StringIndexOutOfBoundsException)");
			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
			fail(str+"Attention, vous tentez de lire en dehors des limites d'un tableau ! (ArrayIndexOutOfBoundsException)");
			e.printStackTrace();
		}catch(NullPointerException e){
			fail(str+"Attention, vous faites une opération sur un objet qui vaut null ! Veillez à bien gérer ce cas.");
			e.printStackTrace();
		}catch(Exception e){
			fail(str + "\n a" + e.getMessage());
			e.printStackTrace();
		}finally{
			System.setErr(err);
		}
	}
}
