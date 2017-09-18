package student;

/**
 * 	Ecrivez un programme qui permet de donner, lorsqu’elles sont r ́eelles, les solutions de l’ ́equation du
 *	second degr ́e a × x 2 + b × x + c = 0. Pour cela, e  ́ crivez un programme qui demande a ` l’utilisateur
 * de taper les valeurs de a, b et c et ensuite e  ́ crit sur sa sortie standard les racines si celles-ci sont
 *	r ́eelles.Pensez aux diff ́erents cas possibles en fonction des diff ́erentes valeurs respectives de a, b et
 */
public class M1EC13Corr {

	/**
	 * @param a,b et les coefficients
	 * @pre 
	 * @pos le format d'une racine, s'il y en a, affichée à l'écran est le suivant:
	 * 
	 * valeur_racine<retour à la ligne>
	 */
	public static void equationSecondDegre_det(double a, double b, double c){

		double det = b*b - 4*a*c;
		if(a == 0){
			if(b == 0){
				if(c == 0)
					System.out.print(c);
				else
					System.out.print("");
			}else
				System.out.print(-c/b);
		}else{

			if(det > 0){
				System.out.println((-b - Math.sqrt(det)) / (2*a));
				System.out.println((-b + Math.sqrt(det)) / (2*a));
			}
			if(det == 0){
				System.out.print(-(b/a*2));
			}
			if(det < 0){
				System.out.print("");
			}
		}
	}
}