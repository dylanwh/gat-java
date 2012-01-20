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
	
	public File(String parent, String child) {
		super(parent, child);
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

	public File file(File file) {
		return new File(this.getPath(), file.getPath());
	}

	public void copy(File dest) {
		// TODO Auto-generated method stub
		
	}
} 
