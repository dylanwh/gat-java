package net.hardison.gat.test;

import static org.junit.Assert.*;

import java.io.IOException;

import net.hardison.gat.File;
import net.hardison.gat.Label;
import net.hardison.gat.LabelFactory;

import org.junit.Test;

public class LabelFactoryTest {
	@Test
	public final void testConstructor() throws IOException {
		LabelFactory lf = new LabelFactory(new File("/foo/./../bar"));
		assertEquals("/bar", lf.getBaseDirectory().getPath());
	}

	@Test
	public final void testMakeLabelSlash() throws IOException {
		LabelFactory lf = new LabelFactory(new File("/foo/bar"));
		Label label1 = lf.makeLabel(new File("/foo/bar/baz/"));
		assertEquals("baz", label1.getName());
	}

	@Test
	public final void testMakeLabelRelative() throws IOException {
		LabelFactory lf = new LabelFactory(new File("bar"));
		Label label     = lf.makeLabel(new File("bar/baz"));
		assertEquals("baz", label.getName());
	}
	
	@Test
	public final void testMakeLabelParent() throws IOException {
		LabelFactory lf = new LabelFactory(new File(".."));
		Label label     = lf.makeLabel(new File("../baz"));
		assertEquals("baz", label.getName());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testMakeLabelIllegalArg() throws IOException {
		LabelFactory lf = new LabelFactory(new File("foo"));
		
		@SuppressWarnings("unused")
		Label label     = lf.makeLabel(new File("../baz"));
	}
}
