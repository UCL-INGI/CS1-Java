/*
 *
 * @author: VOUS
 * @version: Octobre 2013
 */

public class LibMath {

    public static double average(double a, double b, double c){
@		@average@@
	}
	public static double minimum(double a, double b, double c){
@		@minimum@@	
	}

	public static double maximum(double a, double b, double c){
@		@maximum@@	
	}
	public static double median(double a, double b, double c){
	
@		@median@@	
	}
    public static boolean sublime(int n) {
@		@sublime@@
    }
	

@	@sub_help@@
	
    /*
     * @pre -
     * @post A teste la methode minimum
     */
    public static void test_minimum() {

	double a=1.0;
	double b=2.0;
	double c=3.0;
	int erreur=0;

	if(minimum(a,b,c)!=1.0) {
	    System.out.println("Erreur : minimum(1.0,2.0,3.0)!=1.0");
	    erreur++;
	}

	if(minimum(a,a,a)!=1.0) {
	    System.out.println("Erreur : minimum(1.0,1.0,1.0)!=1.0");
	    erreur++;
	}

	if(minimum(c,c,c)!=3.0) {
	    System.out.println("Erreur : minimum(3.0,3.0,3.0)!=3.0");
	    erreur++;
	}

	if(minimum(c,b,a)!=1.0) {
	    System.out.println("Erreur : minimum(3.0,2.0,1.0)!=1.0");
	    erreur++;
	}

	if(minimum(c,b,c)!=2.0) {
	    System.out.println("Erreur : minimum(3.0,2.0,3.0)!=2.0");
	    erreur++;
	}

	if(erreur==0) {
	    System.out.println("Aucune erreur n'a ete trouvee en utilisant la methode minimum");
	}
	else {
	    System.out.print(erreur);
	    System.out.print(" erreurs ont ete trouvees en utilisant la methode minimum");
	}

    }

    /*
     * @pre -
     * @post A teste la methode maximum
     */

    public static void test_maximum() {

	double a=1.0;
	double b=2.0;
	double c=3.0;
	int erreur=0;

	if(maximum(a,b,c)!=3.0) {
	    System.out.println("Erreur : maximum(1.0,2.0,3.0)!=3.0");
	    erreur++;
	}

	if(maximum(a,a,a)!=1.0) {
	    System.out.println("Erreur : maximum(1.0,1.0,1.0)!=1.0");
	    erreur++;
	}

	if(maximum(c,c,c)!=3.0) {
	    System.out.println("Erreur : maximum(3.0,3.0,3.0)!=3.0");
	    erreur++;
	}

	if(maximum(c,b,a)!=3.0) {
	    System.out.println("Erreur : maximum(3.0,2.0,1.0)!=3.0");
	    erreur++;
	}

	if(maximum(a,b,a)!=2.0) {
	    System.out.println("Erreur : maximum(1.0,2.0,1.0)!=2.0");
	    erreur++;
	}

	if(erreur==0) {
	    System.out.println("Aucune erreur n'a ete trouvee en utilisant la methode maximum");
	}
	else {
	    System.out.print(erreur);
	    System.out.print(" erreurs ont ete trouvees en utilisant la methode maximum");
	}
	
    }


    /*
     * @pre -
     * @post A teste la methode average
     */
    public static void test_average() {

	double a=1.0;
	double b=2.0;
	double c=3.0;
	int erreur=0;

	if(average(a,b,c)!=2.0) {
	    System.out.println("Erreur : average(1.0,2.0,3.0)!=2.0");
	    erreur++;
	}

	if(average(a,a,a)!=1.0) {
	    System.out.println("Erreur : average(1.0,1.0,1.0)!=1.0");
	    erreur++;
	}

	if(average(c,c,c)!=3.0) {
	    System.out.println("Erreur : average(3.0,3.0,3.0)!=3.0");
	    erreur++;
	}

	if(average(c,b,a)!=2.0) {
	    System.out.println("Erreur : average(3.0,2.0,1.0)!=2.0");
	    erreur++;
	}

	if(average(a,b,a)!=1.3333333333333333) {
	    System.out.println("Erreur : average(1.0,2.0,1.0)!=1.3333333333333333");
	    erreur++;
	}

	if(erreur==0) {
	    System.out.println("Aucune erreur n'a ete trouvee en utilisant la methode average");
	}
	else {
	    System.out.print(erreur);
	    System.out.print(" erreurs ont ete trouvees en utilisant la methode average");
	}
	
    }


    /*
     * @pre -
     * @post A teste la methode median
     */
    public static void test_median() {

	double a=1.0;
	double b=2.0;
	double c=3.0;
	int erreur=0;

	if(median(a,b,c)!=2.0) {
	    System.out.println("Erreur : median(1.0,2.0,3.0)!=2.0");
	    erreur++;
	}

	if(median(a,a,a)!=1.0) {
	    System.out.println("Erreur : median(1.0,1.0,1.0)!=1.0");
	    erreur++;
	}

	if(median(c,c,c)!=3.0) {
	    System.out.println("Erreur : median(3.0,3.0,3.0)!=3.0");
	    erreur++;
	}

	if(median(c,b,a)!=2.0) {
	    System.out.println("Erreur : median(3.0,2.0,1.0)!=2.0");
	    erreur++;
	}

	if(median(a,b,a)!=1.0) {
	    System.out.println("Erreur : median(1.0,2.0,1.0)!=1.0");
	    erreur++;
	}

	if(erreur==0) {
	    System.out.println("Aucune erreur n'a ete trouvee en utilisant la methode median");
	}
	else {
	    System.out.print(erreur);
	    System.out.print(" erreurs ont ete trouvees en utilisant la methode median");
	}
	
    }


    public static void main (String[] args) {

	// appel aux differentes methodes de test

	test_minimum();
	test_maximum();
	test_average();
	test_median();

    }


}
