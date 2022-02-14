public class Test
{
    public static void main (String[] args)
    {
        System.out.println ("\f");
        

        LibrairieImage.show (LibrairieImage.createCross (890));
        System.out.println ("La premiere fenetre presente une photo celebre d'Albert Einstein");
        LibrairieImage.show (LibrairieImage.imageGrayFromFile ("einstein.jpg"));
    }
}
