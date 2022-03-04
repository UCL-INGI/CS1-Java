package src;


import java.io.*;

public class Correction {
	public static int premierPrenom(String filename) throws IOException{
        File file=new File(filename); 
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line.split("-")[0]);
                }
            return 0;
            } catch(Exception e){
            return -1;
        }
	}
}