package net.hardison.gat;

import java.io.IOException;

/*import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
*/

//import net.kotek.jdbm.*;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File file = new File(".");
		System.out.println(file.getCanonicalFile());
		System.out.println(file.getRelativeFile() );
		
	}

}
