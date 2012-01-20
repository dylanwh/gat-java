package net.hardison.gat.test;

import static java.nio.file.FileSystems.getDefault;
import static org.junit.Assert.*;


import java.io.IOException;
import java.nio.file.Path;

import net.hardison.gat.Label;
import net.hardison.gat.LabelFactory;

import org.junit.Test;

public class LabelFactoryTest {
	
	private Path file(String path) {
		return getDefault().getPath(path);
	}
	
	@Test
	public final void testConstructor() throws IOException {
		LabelFactory lf = new LabelFactory(file("/foo/./../bar"));
		assertEquals("/bar", lf.getBaseDirectory().toString());
	}

	@Test
	public final void testMakeLabelSlash() throws IOException {
		LabelFactory lf = new LabelFactory(file("/foo/bar"));
		Label label1 = lf.makeLabel(file("/foo/bar/baz/"));
		assertEquals("baz", label1.getName());
	}

	@Test
	public final void testMakeLabelRelative() throws IOException {
		LabelFactory lf = new LabelFactory(file("bar"));
		Label label     = lf.makeLabel(file("bar/baz"));
		assertEquals("baz", label.getName());
	}
	
	@Test
	public final void testMakeLabelParent() throws IOException {
		LabelFactory lf = new LabelFactory(file(".."));
		Label label     = lf.makeLabel(file("../baz"));
		assertEquals("baz", label.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testMakeLabelIllegalArg() throws IOException {
		LabelFactory lf = new LabelFactory(file("foo"));
		
		@SuppressWarnings("unused")
		Label label     = lf.makeLabel(file("../baz"));
	}
}
