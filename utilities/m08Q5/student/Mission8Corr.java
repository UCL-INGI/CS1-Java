package student;


/**
 * Une implémentation de MyString
 *
 * @author O. Bonaventure
 * @version Novembre 2016
 */

public class Mission8Corr{
  public class ByteTab implements Byte
{
    private boolean b[];
    // true signifie 1
    // false signifie 0

    /*
     * @pre -
     * @post a construit un Byte dont tous les bits sont mis à 0
     */
    public ByteTab()
    {
        b=new boolean[8];
        for(int i=0;i<b.length;i++) {
            b[i]=false;
        }
    }

    /*
    * @pre s.length==8, s ne contient que des caractères 0 et 1
    * @post a construit un byte dont la représentation naturelle en String est s
    *       La représentation naturelle d'un byte sous la forme d'un String est
    *       le bit de poids fort à gauche. Dans le StringBuilder, on stocke
    *       le byte de façon à avoir le bit de poids faible (resp. fort)
    *       en position 0 (resp. 7).
    *
    *       Exemple
    *
    *       5 en décimal correspond à 00000101 en binaire et
    *       sera stocké dans le StringBuilder 10100000
    */
    public ByteTab(String s)
    {
        b=new boolean[8];
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)=='0')
                b[7-i]=false;
            else
                b[7-i]=true;
        }

    }

    /*
     * @pre -
     * @post retourne la représentation naturelle du nombre binaire
     *       c'est-à-dire celle où le bit de poids fort est à gauche
     */
    public String toString()
    {
        String r="";
        for(int i=b.length-1;i>=0;i--) {
            if(b[i])
                r+='1';
            else
                r+='0';
        }
        return r;

    }

    /*
     * @pre 0<=i<8
     * @post a mis le bit i à 1
     */
    public void setBit(int i)
    {
        b[i]=true;
    }
    /*
     * @pre 0<=i<8
     * @post a mis le bit i à 0
     */
    public void resetBit(int i)
    {
        b[i]=false;
    }

    /*
     * @pre 0<=i<8
     * @post retourne la valeur du bit i
     */
    public int getBit(int i)
    {
        if(b[i])
            return 1;
        else
            return 0;
    }


    public void shiftLeft()
    {
        for(int i=b.length-1; i>0; i--) {
            b[i]=b[i-1];
        }
        b[0]=false;
    }

    public void rotateRight()
    {
        // vu la représentation du tableau, on décale à gauche
        boolean last=b[0];

        for(int i=1;i<b.length;i++) {
            b[i-1]=b[i];
        }
        b[7]=last;
    }
    /*
     * @pre b2!=null
     * @post retourne le byte qui est la conjonction des bytes b et b2
     */

    public Byte and(Byte b2)
    {
        Byte r=new ByteTab();
        for(int i=0; i<b.length;i++) {
            if(this.getBit(i)==1 && b2.getBit(i)==1)
                r.setBit(i);
            else
                r.resetBit(i);

        }
        return r;
    }



    // ne pas montrer

    public boolean[] getTab()
    {
        return b;
    }

}
public interface Byte
{
  /*
  * @pre -
  * @post La chaîne est décallée d'un bit vers la droite (attention à la structure interne)
  */
  public void shiftLeft();
  /*
  * @pre -
  * @post La chaîne est décallée d'un bit vers la gauche (attention à la structure interne)
  */
  public void rotateRight();
  /*
  * @pre -
  * @post On effectue un ET logique entre chaque bit de b et b1
  */
  public Byte and(Byte b1);
  /*
  * @pre i est un entier positif
  * @post le bit à la position i est mis à 1
  */
  public void setBit(int i);
  /*
  * @pre i est un entier positif
  * @post le bit à la position i est remis à 0
  */
  public void resetBit(int i);
  /*
  * @pre i est un entier positif
  * @post On retourne la valeur du bit à la position i
  */
  public int getBit(int i);
}

}
