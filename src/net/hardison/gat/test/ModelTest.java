package net.hardison.gat.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import net.hardison.gat.Name;
import net.hardison.gat.Label;
import net.hardison.gat.Model;
import net.hardison.gat.Link;

import org.apache.commons.codec.DecoderException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModelTest extends BaseTest {
	
	@SuppressWarnings("unused")
	private int count(@SuppressWarnings("rawtypes") Iterable items) {
		int count = 0;
		
		for (Object item: items) {
			count++;
		}
		return count;
	}

	@Test
	public final void testGetAssets() throws IOException, DecoderException {
		Model m = new Model();
		m.setDatabaseDirectory(dir);
		m.create();
		m.open();
		
		
		
		m.close();
	}

}