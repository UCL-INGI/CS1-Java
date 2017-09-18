/**
 * Une impl�mentation primitive de classement, non ordonn�e et de capacit� fixe.
 * 
 * @author Charles Pecheur
 * @version septembre 2013
 */
public class ClassementTemps implements Classement {

    private static final int CAPACITY = 10;

    private Resultat[] liste; // contenu de la liste
    private int size; // nombre de r�sultats

    public ClassementTemps() {
        liste = new Resultat[CAPACITY];
    }

    /** @see Classement#add */
    public void add(Resultat r) {
        // ins�re en fin de liste, pas ordonn�
        liste[size] = r; // exception si size >= CAPACITY
        size++;
    }

    /** @see Classement#get */
    public Resultat get(Coureur c) {
        for (int i = 0; i < size; i++) {
            if (liste[i].getCoureur().equals(c)) {
                return liste[i];
            }
        }
        return null;
    }

    /** @see Classement#getPosition */
    public int getPosition(Coureur c) {
        // liste non tri�e
        for (int i = 0; i < size; i++) {
            if (liste[i].getCoureur().equals(c)) {
                return i + 1;
            }
        }
        return -1;
    }

    /** @see Classement#remove */
    public boolean remove(Coureur c) {
        int i = 0;
        while (i < size && !liste[i].getCoureur().equals(c)) {
            i++;
        }

        if (i >= size) {
            // pas trouv� le coureur
            return false;
        }

        // coureur en liste[i]
        for (int j = i+1; j < size; j++) {
            liste[j-1] = liste[j];

        }
        size--;
        return true;
    }

    /** @see Classement#size */
    public int size() {
        return size;
    }

    /** @see Classement#toString */
    public String toString() {
        StringBuffer buf = new StringBuffer();
        for (int i=0; i < size; i++) {
            buf.append(liste[i]);
            buf.append("\n");
        }
        return buf.toString();
    }
}
