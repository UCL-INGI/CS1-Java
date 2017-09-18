package src;

import src.librairies.Byte;

/**
 * Une implémentation de MyString
 *
 * @author O. Bonaventure
 * @version Novembre 2016
 */

class ByteStringCorr implements Byte
{
   private StringBuilder b; // contient les caractères représentant le byte

   /*
    * @pre -
    * @post a construit un byte dont les huit bits sont mis à zéro
    *       le bit de poids fort est en position 7 dans le StringBuilder
    */
   public ByteStringCorr()
   {
       b=new StringBuilder("00000000");
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
   public ByteStringCorr(String s)
   {
       b=new StringBuilder();
       for(int i=s.length()-1;i>=0;i--) {
         b.append(s.charAt(i));
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
        for(int i=b.length()-1; i>=0;i--) {
            r=r+b.charAt(i);
        }
        return r;
    }

    // à implémenter sur base de l'interface

    // Implémentez


   public void setBit(int i)
   {
        b.setCharAt(i,'1');
   }

   public void resetBit(int i)
   {
        b.setCharAt(i,'0');
   }

   public int getBit(int i)
   {
        if(b.charAt(i)=='0')
            return 0;
        else
            return 1;
   }

   public void shiftLeft()
    {
        // vu la représentation interne, on décale à droite
        for(int i=b.length()-1; i>0; i--) {
            b.setCharAt(i,b.charAt(i-1));
        }
        b.setCharAt(0,'0');
    }

    public void rotateRight()
    {
        // on décale à gauche vu la représentation interne
        char last=b.charAt(0);

        for(int i=1;i<b.length();i++) {
            b.setCharAt(i-1,b.charAt(i));
        }
        b.setCharAt(7,last);
    }

    public Byte and(Byte b2)
    {
        Byte r=new ByteStringCorr();
        for(int i=0; i<b.length();i++) {
            if(this.getBit(i)==1 && b2.getBit(i)==1)
                r.setBit(i);
            else
                r.resetBit(i);

        }
        return r;
    }
}
