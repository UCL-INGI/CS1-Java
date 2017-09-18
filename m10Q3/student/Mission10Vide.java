package student;
import java.util.Arrays;
import student.StudentFormatException;

/**
 * Une implémentation de Mission9Stu
 *
 * @author Ludovic Taffin
 * @version Novembre 2016
 */

public class Mission10Stu{
  /**
   * Une classe décrivant un étudiant
   *
   * @author O. Bonaventure
   * @version Novembre 2016
   */

  
  public class Student
  {
      public String nom;
      public double[] cotes;

      /**
       * @pre nom!=null, cotes!=null
       * @post a construit une instance de la classe Student
       *       lance une exception StudentFormatException si une des
       *       cotes est invalide (non comprise entre 0 et 20)
       */
      public Student(String nom, double[] points) throws StudentFormatException
      {
          this.nom=nom;
          cotes=points;
      }

      /*
       * @pre -
       * @post construit une instance de la classe Student en supposant que le String s a le format suivant
       *       nom; 2.5, 3.0, 6.7
       *
       *       Le nom est suivi de ; et une virgule sépare les points. Ceux doivent être entre 0 et 20
       *       Si le format n'est pas respecté, le constructeur lance une StudentFormatException avec
       *       comme argument le String passé
       */

      public Student(String s) throws StudentFormatException
      {
          @ @q1@@

      }


      // code non fourni

      public boolean same(Student s) {
      	if (s.nom == null) return false;
        return (s.nom.equals(this.nom) &&
                 Arrays.equals(s.cotes,this.cotes));

      }
  }

}
