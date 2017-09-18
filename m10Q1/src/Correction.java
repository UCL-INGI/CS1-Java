/**
 *  Copyright (c)  2016 Ludovic Taffin, 2017 Naitali Brandon
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
package src;
import StudentCode.*;

public class Correction{
  /**
   * Une classe représentant une fraction
   *
   * @author O. Bonaventure
   * @version Oct. 2016
   */
  public static class Fraction 
  {
      private int num; // numerateur
      private int den; // denominateur

      /**
       * @pre den>0
       * @post construit la fraction num/den
       */
      public Fraction(int num, int den)
      {
          this.num=num;
          this.den=den;
      }

      /*
       * @pre -
       * @post construit une fraction sur base d'une chaine caractère de la
       *       forme num/den  où num est un entier et den un entier non nul
       *       lance une exception lorsque la chaîne de caractères passée
       *       en argument ne respecte pas ce format ou que le dénominateur est nul
       */
      public Fraction(String s) throws NumberFormatException
      {
          if (s==null)
              throw new NumberFormatException();
          String[] tab=s.split("/");
          if(tab.length!=2)
              throw new NumberFormatException();
          int num=Integer.parseInt(tab[0]);
          int den=Integer.parseInt(tab[1]);
          if (den==0)
              throw new NumberFormatException();
          this.num=num;
          this.den=den;


      }

      /*
       * @pre -
       * @post retourne le dénominateur de la fraction
       */
      public int getDen()
      {
          return this.den;
      }


      /*
       * @pre -
       * @post retourne le numérateur de la fraction
       */
      public int getNum()
      {

          return this.num;
      }

      // la méthode qui manque  (modifiée pour des raison de test)
      public static int compareTo(StudentCode.Fraction f1, StudentCode.Fraction f2) {
  	  double f11,f21;
          f21=(double) f1.num/(double) f1.den;
          f11=(double) f2.num/(double) f2.den;
          if(f11==f21)
              return 0;
          if(f21<f11)
              return -1;
          else
              return 1;

      }



      // ne pas montrer aux étudiants, utilisé pour les tests
      public boolean equals(Object o)
      {
          if (o instanceof Fraction){
              Fraction f=(Fraction) o;
              return ( (f.getNum()==this.getNum()) &&
                  (f.getDen()==this.getDen()) );

          }
          return false;


      }


  }

}

