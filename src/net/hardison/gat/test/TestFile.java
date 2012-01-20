package net.hardison.gat.test;

import static org.junit.Assert.*;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import net.hardison.gat.File;

public class TestFile {

	@Test
	public final void testGetRelativeFileFile() {
		File first = new File("/foo/bar/baz");
		try {
			assertEquals("relative path", "bar/baz", first.getRelativeFile(new File("/foo")).getPath());
			assertEquals("relative path", "bar/baz", first.getRelativeFile(new File("/foo/")).getPath());
		}
		catch (IOException e) {
			fail("got IO exception");
		}
	}
}
