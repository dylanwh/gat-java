package gat.workspace;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.Test;

public class BondManagerTest {
	private BondManager bondManager;

	@Before
	public void setUp() throws Exception {
		Path dir = Files.createTempDirectory("bond-manager-");
		
		bondManager = new BondManager(dir);
	}

	@Test
	public void testBind() {
		Hash hash = new Hash("d41d8cd98f00b204e9800998ecf8427e");
		Label label = new Label("foo");
		
		try {
			bondManager.bind(label, hash);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("exception raised");
		}
		
		assertEquals("d41d8cd98f00b204e9800998ecf8427e", bondManager.findHash(label).toString());
		
		
		
	}

	@Test
	public void testUnbind() {
		fail("Not yet implemented");
	}

}
