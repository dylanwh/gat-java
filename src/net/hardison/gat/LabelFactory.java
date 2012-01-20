package net.hardison.gat;

import java.io.IOException;
import java.nio.file.Path;

public class LabelFactory {
	
	private Path baseDirectory = null;
	
	public LabelFactory(Path baseDirectory) throws IOException {
		setBaseDirectory(baseDirectory);
	}

	public Path getBaseDirectory() {
		return baseDirectory;
	}

	public void setBaseDirectory(Path baseDirectory) throws IOException {
		this.baseDirectory = baseDirectory.toAbsolutePath().normalize();
	}
	
	public Label makeLabel(Path file) throws IOException {	
		Path cleanfile = file.toAbsolutePath().normalize();
		// TODO: Ensure canonical paths never end with '/'.
		if (cleanfile.startsWith(baseDirectory))
			return new Label(baseDirectory.relativize(cleanfile).toString());
		else
			throw new IllegalArgumentException(file + " is not relative to " + baseDirectory);
	}

}
