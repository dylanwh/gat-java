package net.hardison.gat.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import net.hardison.gat.Asset;
import net.hardison.gat.AssetFactory;
import net.hardison.gat.RepositoryException;
import net.hardison.gat.RepositoryLink;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RepositoryLinkTest {
	RepositoryLink repo;
	FileSystem fs;

	@Before
	public void setUp() throws Exception {
		fs = FileSystems.getDefault();
		if (!Files.exists(fs.getPath("test")))
			Files.createDirectory(fs.getPath("test"));
		repo = new RepositoryLink();
		repo.setAssetFactory(new AssetFactory());
		repo.setTempDirectory(fs.getPath("test/tmp"));
		repo.setAssetDirectory(fs.getPath("test/asset"));
		repo.create();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testStore() throws IOException, RepositoryException {
		Path file           = fs.getPath("test/foo.txt");
		OutputStream stream = Files.newOutputStream(file);
		String msg          = "hello, world";
		stream.write(msg.getBytes());
		stream.close();
		
		Asset asset = repo.store(file);
		assertFalse(Files.exists(file));
		assertEquals("e4d7f1b4ed2e42d15898f4b27b019da4", asset.getChecksum());
	}

	@Test
	public final void testAttach() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testIsAttached() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetAssetFile() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testPurge() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testDetach() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCloneAsset() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testIsStored() {
		fail("Not yet implemented"); // TODO
	}

}
