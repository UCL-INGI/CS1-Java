package student;


/**
 * Une implémentation de Mission9Corr
 *
 * @author Ludovic Taffin
 * @version Novembre 2016
 */

public class Mission10Corr{
  /**
   * Une implémentation partielle d'une ArrayList en utilisant un tableau
   *
   * Cette implémentation supporte les méthodes suivantes :
   *
   * Constructeur : crée une liste vide      AList()
   * Insère d’un élément à la position index void add (int index, Object obj)
   * Renvoie l’objet en position index       Object get (int index)
   * Remplace l’élément en position index        void set (int index, Object obj)
   * Retire et renvoie l’élément en position index Object remove (int index)
   * Renvoie true si obj appartient à la liste       boolean contains (Object obj)
   *
   * @author O; Bonaventure
   * @version Novembre 2016
   */
  public class AList
  {
      Object[] l;


      public static final int INIT_SIZE=5;  // taille initiale d'un AList

      /*
       * @pre -
       * @post a créé une AList initialement vide permettant de stocker 5 références
       */
      public AList()
      {
          l=new Object[INIT_SIZE];

      }

      /*
       * @pre o!=null
       * @post retourne true si l'objet o se trouve dans l'AList
       */
      public boolean contains(Object o)
      {
          for(int i=0;i<l.length;i++) {
              if(o.equals(l[i])) {
                 return true;
              }
          }
          return false;
      }

      /*
       * @pre -
       * @post retourne l'objet se trouvant à la position index et null si rien n'est stocké à cette position
       *       ou si index>=length
       */
      public Object get(int index)
      {
          if(index<l.length)
              return l[index];
          else
              return null;

      }

      /*
       * @pre -
       * @retourne la taille actuelle de la liste
       */
      public int length()
      {
          return l.length;
      }

      /*
       * @pre -
       * @post ajoute l'objet o à la liste en position index. Si index>length, augmente d'avoir la taille
       *       de la liste en créant un nouveau tableau et en y recopiant toutes les
       *        références de la liste this avant d'ajouter l'objet o
       *
       */
      public void add(int index, Object o)
      {
          if(index<l.length) {
              l[index]=o;
          }
          else
          {
           Object[] lnew=new Object[index+1];
           for(int i=0; i<l.length;i++) {
               lnew[i]=l[i];
           }
           lnew[index]=o;
           l=lnew;
          }
      }

      /*
       * @pre 0<=index< this.length()
       * @post remplace la référence se trouvant à l'index dans la liste par la référence à o
       *
       */
      public void set(int index, Object o)
      {
          l[index]=o;
      }



      // ne pas montrer aux étudiants, utilisé pour les tests

      public Object[] getTab()
      {
          return l;
      }
  }

}
