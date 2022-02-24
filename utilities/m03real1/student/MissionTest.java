package student;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * MissionTest.java
 * 
 * Prévue pour être lancée dans un processus dédié, cette classe lance les tests
 * d'une classe de test JUnit passée en argument à la méthode principale.
 * 
 * @author Bastien Bodart (bastien.bodart@student.uclouvain.be)
 * @version 1.2
 * @date 13 mai 2014
 */
public class MissionTest
{
	/**
	 * Méthode principale
	 * 
	 * @param args
	 *            args[0] doit être l'URI du dossier où se trouve le fichier de
	 *            la classe de test args[1] doit être le nom de la classe de
	 *            test
	 */
	public static void main(String[] args)
	{
		try
		{
			URLClassLoader urlClassLoader = new URLClassLoader(new URL[] { new URI(args[0]).toURL() });
			
			Result result = test(urlClassLoader, args[1]);
			
			urlClassLoader.close();
			
			for (Failure failure : result.getFailures()) {
				System.err.println(failure.toString());
			}
			if(result.wasSuccessful()){
				System.out.println("Tous les tests se sont passés sans encombre");
				System.exit(127);
			}
			
		}
		catch (Throwable e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Lance les tests contenus dans la classe de test et imprime les résultats
	 * sous un format défini sur la sortie d'erreur. § est utilisé comme
	 * caractère de reconnaissance pour les erreurs de test. Imprime aussi le
	 * temps d'exécution (ns) sur la sortie standard.
	 * 
	 * Format d'impression d'erreur : §nomDeL'Erreur message ligne methode
	 * 
	 * @param urlClassLoader class loader utilisé pour charger la classe de test
	 * @param className nom de la classe de test
	 * @throws Throwable
	 */
	public static Result test(URLClassLoader urlClassLoader, String className) throws Throwable
	{
		Result result = JUnitCore.runClasses(urlClassLoader.loadClass(className));
		return result;
	}
}
