package gat.repository.fs;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import gat.repository.Asset;
import gat.repository.Repository;
import gat.repository.RepositoryException;
import gat.workspace.Hash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

abstract class BaseRepository implements Repository {
	private final Path assetDirectory;
	private final Path tempDirectory;

	BaseRepository(Path assetDirectory) {
		this.assetDirectory = assetDirectory.toAbsolutePath();
		this.tempDirectory  = this.assetDirectory.resolve("tmp");
	}
	
	final private Path getAssetPath (Hash name) {
		return assetDirectory.resolve(name.toPath());
	}
	
	final private BasicFileAttributes getAttributes(Path path)
			throws IOException {
		return Files.readAttributes(path, BasicFileAttributes.class,
				NOFOLLOW_LINKS);
	}
	
	@Override
	final public void init() throws IOException {		
		Files.createDirectories(assetDirectory);
		Files.createDirectories(tempDirectory);
	}

	abstract protected Asset newAsset(Path path, Hash name, BasicFileAttributes attr);

	final public Asset store(Path path) throws RepositoryException, IOException {
		Path assetPath           = getAssetPath(name);
		BasicFileAttributes attr = getAttributes(path);
		Asset asset              = newAsset(assetPath, name, attr);
		
		asset.store(path);
		return asset;
	}

	@Override
	final public boolean contains(Hash name) {
		return Files.exists(getAssetPath(name));
	}
	
	@Override
	final public Asset fetch(Hash name) throws IOException {
		Path assetPath = getAssetPath(name);		
		return newAsset(assetPath, name, getAttributes(assetPath));		
	}

}
