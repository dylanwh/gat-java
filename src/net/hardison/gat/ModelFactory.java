package net.hardison.gat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;



import org.apache.commons.codec.digest.DigestUtils;

public class ModelFactory {
	/*
	private Path baseDirectory = null;

	public Path getBaseDirectory() {
		return this.baseDirectory;
	}

	public void setBaseDirectory(Path baseDirectory) {
		this.baseDirectory = baseDirectory.toAbsolutePath().normalize();
	}
	
	public void setBaseDirectory(String baseDirectory) {
		setBaseDirectory(FileSystems.getDefault().getPath(baseDirectory));
	}

	public Checksum makeChecksum(Path file) throws IOException {
		InputStream stream = Files.newInputStream(file);
		Checksum result = null;
		try {
			result = new Checksum(DigestUtils.md5(stream));
		} finally {
			stream.close();
		}
		return result;
	}
	
	public Item makeAsset(Path file) throws FileNotFoundException, IOException {
		assert file != null;
		assert file.toString() != null;
		
		if (Files.notExists(file))
			throw new FileNotFoundException(file.toString() + " does not exist");
		
		BasicFileAttributes attr = Files.readAttributes(file,
				BasicFileAttributes.class,
				NOFOLLOW_LINKS
		);
		
		if (!attr.isRegularFile()) 
			throw new FileNotFoundException(file.toString() + " is not a regular file");
		
		if (!Files.isReadable(file))
			throw new FileNotFoundException(file.toString() + " is not readable");
		
		Item item = new Item();
		item.setChecksum(makeChecksum(file));
		item.setSize(attr.size());
		item.addLabel(makeLabel(file));
		item.setContentType("text/plain");
		return item;		
	}
	
	public Label makeLabel(Path file) throws IOException {	
		Path cleanfile = file.toAbsolutePath().normalize();
		// TODO: Ensure canonical paths never end with '/'.
		if (cleanfile.startsWith(baseDirectory))
			return new Label(baseDirectory.relativize(cleanfile).toString());
		else
			throw new IllegalArgumentException(file + " is not relative to " + baseDirectory);
	}
	*/
}
