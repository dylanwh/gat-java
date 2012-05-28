package gat.workspace;


import java.io.IOException;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.google.common.base.Joiner;
import com.google.common.io.Files;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Workspace {
	private Path baseDirectory;
	private Path userDirectory;
	
	@Inject
	Workspace(@Named("UserDirectory") Path userDirectory, 
			@Named("BaseDirectory") Path baseDirectory) {
		this.userDirectory = userDirectory;
		this.baseDirectory = baseDirectory;
	}
	
	public Path getBaseDirectory() {
		return this.baseDirectory;
	}

	public Path getUserDirectory() {
		return this.userDirectory;
	}
	
	public Path resolveLabel(Label label) {
		return null;
	}
	
	/* As Label is package private, this is the only place where a Label can be constructed. */
	public Label newLabel(Path path) {
		Path rel = path.normalize().relativize(baseDirectory);
		return new Label(Joiner.on('/').join(rel));	// use proper slashes regardless of platform.
	}
	
	public Hash newHash(Path path) throws NoSuchAlgorithmException, IOException {
		return new Hash(Files.getDigest(path.toFile(),
				MessageDigest.getInstance("MD5")));
	}
	
}
