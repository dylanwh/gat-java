package net.hardison.gat;

import java.io.IOException;
import java.io.Serializable;

public class File extends java.io.File implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public File(String pathname) {
		super(pathname);
	}
	
	public File() {
		super(".");
	}

	public File(java.io.File file) {
		super(file.getPath());
	}

	public File getRelativeFile() throws IOException {
		return this.getRelativeFile(new File());
	}
	
	public File getRelativeFile(File dir) throws IOException {
		return null;
	}

	@Override
	public File getAbsoluteFile() {
		// TODO Auto-generated method stub
		return new File(super.getAbsoluteFile());
	}

	@Override
	public File getCanonicalFile() throws IOException {
		// TODO Auto-generated method stub
		return new File(super.getCanonicalFile());
	}
} 
