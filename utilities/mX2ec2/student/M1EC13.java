package student;

import java.util.Random;

public class M1EC13 {

	public static void main(String[] args) throws Exception{
		if(args.length != 4){
                    System.err.println(args.length);
			System.err.println("Erreur interne : pas assez d'arguments: arg1 code a b c");
		}
        else{
           Random gen = new Random();	// On enleve la seed pour le moment car les etudiants peuvent hardcoder les reponses sinon.
			double a = Double.parseDouble(args[1]);
			double b = Double.parseDouble(args[2]);
			double c = Double.parseDouble(args[3]);
		
			if(args[0].equals("etudiant")){
				M1EC13Stu.equationSecondDegre_det(a, b ,c );
			}else if(args[0].equals("correction")){
				M1EC13Corr.equationSecondDegre_det(a, b ,c );

			}
       }

	}

}