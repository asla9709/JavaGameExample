package TileMap;
import java.io.*;
import java.util.ArrayList;

public class FileInput {
	
	private static ArrayList<String> text;
	
	public static ArrayList<String> tileMap(String s) {
		try {
			
			text = new ArrayList<String>();
			
			FileInputStream fstream = new FileInputStream(s);
			DataInputStream in = new DataInputStream(fstream);

			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			while ((strLine = br.readLine()) != null) {
				text.add(strLine);
			}
			in.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return text;
	}
}
