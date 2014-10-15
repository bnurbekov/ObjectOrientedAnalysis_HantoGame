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
    		PrintWriter writer = new PrintWriter("converted.txt", "UTF-8");
    		
    		String sCurrentLine;
    		 
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader("convert.txt"));
 
			while ((sCurrentLine = br.readLine()) != null) {
				sCurrentLine = sCurrentLine.replace("BLUE places ", "game.makeMove(");
				sCurrentLine = sCurrentLine.replace("RED places ", "game.makeMove(");
				sCurrentLine = sCurrentLine.replace("BLUE moves ", "game.makeMove(");
				sCurrentLine = sCurrentLine.replace("RED moves ", "game.makeMove(");
				sCurrentLine = sCurrentLine.replace(" at ", ", null, makeCoordinate");
				sCurrentLine = sCurrentLine.replace(" from ", ", makeCoordinate");
				sCurrentLine = sCurrentLine.replace(" to ", ", makeCoordinate");
				sCurrentLine = sCurrentLine.replace("Horse", "HORSE");
				sCurrentLine = sCurrentLine.replace("Butterfly", "BUTTERFLY");
				sCurrentLine = sCurrentLine.replace("Sparrow", "SPARROW");
				sCurrentLine = sCurrentLine.replace("Crab", "CRAB");
				sCurrentLine = sCurrentLine.concat(");");
				
				writer.println(sCurrentLine);
			}
    		
    		writer.close();
 
    	}catch(IOException e){
 
    	   e.printStackTrace();
 
    	}
 
    }
}