package net.hardison.gat.test;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.junit.After;
import org.junit.Before;

public class BaseTest {

	FileSystem fs;
	protected Path dir;

	public BaseTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		fs = FileSystems.getDefault();
		dir = Files.createTempDirectory("gat-test-");
	}

	@After
	public void tearDown() throws Exception {
		Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
	         @Override
	         public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
	             throws IOException
	         {
	        	 System.out.println(file);
	             Files.delete(file);
	             return FileVisitResult.CONTINUE;
	         }
	         @Override
	         public FileVisitResult postVisitDirectory(Path dir, IOException e)
	             throws IOException
	         {
	             if (e == null) {
	                 Files.delete(dir);
	                 return FileVisitResult.CONTINUE;
	             } else {
	                 // directory iteration failed
	                 throw e;
	             }
	         }
	     });
	}

}