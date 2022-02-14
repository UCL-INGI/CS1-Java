package student;

import java.util.Random;

public class M1EC13 {

	public static void main(String[] args) throws Exception{
		
		if(args.length != 1){
			System.err.println("Erreur interne : pas assez d'arguments: arg1 code arg2");
		}
        else{
           Random gen = new Random();	// On enleve la seed pour le moment car les etudiants peuvent hardcoder les reponses sinon.
			double a = 1;
			double b = 1;
			double c = 1;
		
			if(args[0].equals("etudiant")){
				M1EC13Stu.equationSecondDegre_det(a, b ,c );
			}else if(args[0].equals("correction")){
				M1EC13Corr.equationSecondDegre_det(a, b ,c );

			}
       }

	}

}