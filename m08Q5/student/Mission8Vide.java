package student;


/**
 * Une implémentation de MyString
 *
 * @author O. Bonaventure
 * @version Novembre 2016
 */

public class Mission8Stu{
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
    
    @	@q1@@
    
    
    
    // ne pas montrer
    
    public boolean[] getTab()
    {
        return b;
    }
    
}
public interface Byte
{
    
    
    public void shiftLeft();
    
    public void rotateRight();
    
    public Byte and(Byte b1);
    
    public void setBit(int i);
    
    public void resetBit(int i);
    
    public int getBit(int i);
}

}
