package gat.config;

import java.nio.file.Files;
import java.nio.file.Path;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class Config {
	private final Path userDirectory;
	private final Path baseDirectory;
	
	@Inject
	public Config(@Named("UserDirectory") Path userDir) {
		super();
		this.userDirectory = userDir.toAbsolutePath();
		this.baseDirectory = findBaseDirectory();
	}
	
	private Path findBaseDirectory() {
		for (Path dir = userDirectory; dir != null; dir = dir.getParent()) {
			if (Files.isDirectory(dir.resolve(".gat")))
				return dir;
		}
		return userDirectory;
	}

	public Path getBaseDirectory() {
		return baseDirectory;
	}
}
