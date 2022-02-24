package student;
import java.util.Arrays;

/**
 * Une implémentation de Mission9Corr
 *
 * @author Ludovic Taffin
 * @version Novembre 2016
 */

public class Mission10Corr{
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
        String[] s1=s.split(";");
        if(s1.length!=2)    
            throw new StudentFormatException(s);
        this.nom=s1[0];
        String [] s2=s1[1].split(",");
        double [] c= new double[s2.length];
        for(int i=0; i<s2.length;i++) {
            try {
                c[i] = Double.parseDouble(s2[i]);
            }catch (NumberFormatException e) {
                throw new StudentFormatException(e.getMessage());
            }
            if( (c[i]>20) || (c[i]<0) ) 
               throw new StudentFormatException(s);
        }
        cotes=c;
       }


      // code non fourni

      public boolean same(Student s) {
          return (s.nom.equals(this.nom) &&
                 Arrays.equals(s.cotes,this.cotes));

      }
  }

}
