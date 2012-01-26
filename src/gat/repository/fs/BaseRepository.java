package gat.repository.fs;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import gat.database.Name;
import gat.repository.Repository;
import gat.repository.RepositoryException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseRepository implements Repository<FileAsset> {
	private final Path assetDirectory;
	private final Path tempDirectory;
	private final List<Path> cloneFiles;

	BaseRepository(Path assetDirectory) {
		this.assetDirectory = assetDirectory.toAbsolutePath();
		this.tempDirectory  = this.assetDirectory.resolve("tmp");
		this.cloneFiles     = new LinkedList<Path>();
	}
	
	final protected FileAsset newAsset(Path path) throws IOException {
		// TODO Auto-generated method stub
		return newAsset(path, gat.Util.newName(path));
	}
	
	final protected FileAsset newAsset(Path path, Name name) throws IOException {
		if (Files.notExists(path))
			throw new FileNotFoundException(path.toString());
		
		BasicFileAttributes attr = Files.readAttributes(path,
				BasicFileAttributes.class,
				NOFOLLOW_LINKS
		);
		
		if (!attr.isRegularFile()) 
			throw new FileNotFoundException(path.toString() + " is not a regular path");
		
		if (!Files.isReadable(path))
			throw new FileNotFoundException(path.toString() + " is not readable");
		

		return new FileAsset(assetDirectory.resolve(name.toPath()), name, attr.size());
	}

	@Override
	final public void init() throws IOException {		
		Files.createDirectories(assetDirectory);
		Files.createDirectories(tempDirectory);
	}
	
	@Override
	final public void open() { }
	
	@Override
	final public void close() throws RepositoryException, IOException {
		for (Path path: cloneFiles) {
			Files.deleteIfExists(path);
		}
		cloneFiles.clear();
	}


	public abstract FileAsset store(Path path) throws RepositoryException, IOException;
	
	@Override
	public abstract void attach(Path path, FileAsset asset) throws RepositoryException, IOException;
	
	@Override
	final public boolean detach(Path path, FileAsset asset) throws RepositoryException, IOException {
		if (isAttached(path, asset))
			return Files.deleteIfExists(path);
		else
			return false;
	}

	@Override
	final public boolean isAttached(Path path, FileAsset asset) {
		if (!Files.exists(path))
			return false;
		if (!isStored(asset))
			return false;
		
		try {
			return isAttachedInner(path, asset);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	abstract protected boolean isAttachedInner(Path path, FileAsset asset) throws IOException;

	@Override
	public boolean isStored(FileAsset asset) {
		Path assetFile = asset.getPath();
		return Files.exists(assetFile);
	}

	@Override
	final public boolean purge(FileAsset asset) throws RepositoryException, IOException {
		return Files.deleteIfExists(asset.getPath());		
	}

	@Override
	final public FileAsset fetch(Name name) throws IOException {
		return newAsset(assetDirectory.resolve(name.toPath()), name);
	}

	@Override
	final public Path clone(FileAsset asset) throws RepositoryException, IOException {
		
		Path tempFile = Files.createTempFile(tempDirectory, "gat-", ".asset");
		Path assetFile = asset.getPath();
		
		if (Files.notExists(assetFile))
			throw new RepositoryException("unable to clone non-existing asset");
		
		Files.copy(assetFile, tempFile, REPLACE_EXISTING);
		cloneFiles.add(tempFile);
		return tempFile;
	}
}
