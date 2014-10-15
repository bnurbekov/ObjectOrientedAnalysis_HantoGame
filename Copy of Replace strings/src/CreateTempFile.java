import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
 
public class CreateTempFile
{
    public static void main(String[] args)
    {	
 
    	try{
    		PrintWriter writer1 = new PrintWriter("out1.txt", "UTF-8");
    		PrintWriter writer2 = new PrintWriter("out2.txt", "UTF-8");
    		
    		String sCurrentLine;
    		 
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader("the-file-name.txt"));

			int i = 0;
			
			while ((sCurrentLine = br.readLine()) != null) {
				if (i%2 == 0) {
					writer1.println(sCurrentLine);
				}
				else {
					writer2.println(sCurrentLine);
				}
				
				i++;
			}
    		
    		writer1.close();
    		writer2.close();
 
    	}catch(IOException e){
 
    	   e.printStackTrace();
 
    	}
 
    }
}