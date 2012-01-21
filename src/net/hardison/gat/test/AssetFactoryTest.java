package net.hardison.gat.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import net.hardison.gat.Asset;
import net.hardison.gat.AssetFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AssetFactoryTest {
	AssetFactory assetFactory = null;
	Path goodFile = null;
	Path badFile = null;
	FileSystem fs = FileSystems.getDefault();
	

	@Before
	public void setUp() throws Exception {
		assetFactory = new AssetFactory();
		goodFile     = fs.getPath("good.txt");
		badFile      = fs.getPath("bad.txt");
		OutputStream output = Files.newOutputStream(goodFile);
		output.write("hello".getBytes());
		output.flush();
		output.close();
	}

	@After
	public void tearDown() throws Exception {
		Files.deleteIfExists(goodFile);
	}

	@Test
	public final void testMakeAssetGood() {
		Asset asset;
		
		try {
			asset = assetFactory.makeAsset(goodFile);
			assertEquals(5, asset.size());
			assertNotNull(asset.lastModifiedTime());
			assertEquals("5d41402abc4b2a76b9719d911017c592", asset.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("IOException");
		}
	}
	
	@Test(expected = FileNotFoundException.class)
	public final void testMakeAssetBad() throws IOException {
		assetFactory.makeAsset(badFile);
	}
}
