package net.hardison.gat;

import java.io.IOException;

public class LabelFactory {
	
	private File baseDirectory = null;
	
	public LabelFactory(File baseDirectory) throws IOException {
		setBaseDirectory(baseDirectory);
	}

	public File getBaseDirectory() {
		return baseDirectory;
	}

	public void setBaseDirectory(File baseDirectory) throws IOException {
		this.baseDirectory = baseDirectory.getCanonicalFile();
	}
	
	public Label makeLabel(File file) throws IOException {
		String path = file.getCanonicalPath();
		String prefix = baseDirectory.getPath() + "/";
		
		// TODO: Ensure canonical paths never end with '/'.
		if (path.startsWith(prefix))
			return new Label(path.substring(prefix.length()));
		else
			throw new IllegalArgumentException(file + " is not relative to " + baseDirectory);
	}

}
