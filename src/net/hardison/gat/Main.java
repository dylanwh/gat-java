package net.hardison.gat;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

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
		Path base = FileSystems.getDefault().getPath("/home/dylan");
		Path file = FileSystems.getDefault().getPath("..").toAbsolutePath().normalize();
		System.out.println(base.relativize(file));
		
	
		
	}

}
