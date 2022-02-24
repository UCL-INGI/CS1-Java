/**
 *  Copyright (c)  Francois Michel, 2017 Brandon Naitali
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

public class Correction{
	public static class RevisedRatio
	{
		/**
		 * @pre n1 et n2 sont des chaînes de caractères != null
		 * @post après conversion de n1 et n2 en entier, retourne n1/n2
		 */
		public static int divise(String n1, String n2)
		{
			int n = Integer.parseInt(n1);
			int d = Integer.parseInt(n2);
			return doDivise(n, d);
		}

		/** 
		 * @pre  n et d sont des entiers
		 * @post retourne n/d si d!=0, sinon throws ArithmeticException
		 */
		public static int doDivise(int n, int d)
		{
			return (n/d);
		}

		/**
		 * @pre args contient un tableau de String passés en ligne de commande
		 * @post si args contient seulement un String, affiche un message d'erreur
		 *        sinon affiche le résultat de la division de args[0] par args[i]
		 *        pour tout 0 < i < args.length
		 */
		public static int div(String[] args)
		{
			if(args.length<2)
			{   
				System.out.println("Erreur : la ligne de commande doit être :");
				System.out.println("java ratio num1 num2 num3... ");
				System.exit(-1);
			}
			else
			{
				for(int i=1; i<args.length;i++)
				{
					try{ 
						divise(args[0], args[i]);
					}catch(ArithmeticException e){
						System.err.println("Vous ne pouvez pas diviser par 0.");
						return -2;
					}catch(NumberFormatException e){
						System.err.println("La chaîne de caractères passée à parseInt() ne contient pas que des chiffres.");
						return -2;
					}
                     			System.out.println(args[0] + "/" + args[i] + " = " + divise(args[0], args[i]));
				}
			}
			return 0;        
		}
	}
}
