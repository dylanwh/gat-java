package net.hardison.gat.repo;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import net.hardison.gat.Asset;
import net.hardison.gat.Repository;


import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public abstract class RepositoryBase implements Repository<Foo> {
	private Path assetDirectory = null;
	private Path tempDirectory = null;
	private ArrayList<Path> cloneFiles = null;
	private boolean opened = false;
	private FileSystem fs = FileSystems.getDefault();

	protected Path getAssetFile(Foo asset) {
		String checksum = asset.toString();
		
		return fs.getPath(assetDirectory.toString(),
				checksum.substring(0, 2),
				checksum.substring(2));
	}

	@Override
	public void create() throws IOException {
		assert assetDirectory != null;
		assert tempDirectory != null;
		
		Files.createDirectories(assetDirectory);
		Files.createDirectories(tempDirectory);
	}
	
	@Override
	public void open() {
		opened = true;
		this.cloneFiles = new ArrayList<Path>();
	}
	
	@Override
	public void close() throws RepositoryException, IOException {
		assert opened;
		
		for (Path path: cloneFiles) {
			Files.deleteIfExists(path);
		}
		
		opened = false;
	}
	
	
	@Override
	abstract public Foo store(Path file) throws RepositoryException, IOException;

	@Override
	public boolean purge(Foo asset) throws RepositoryException, IOException {
		assert opened;
		
		Path assetFile = getAssetFile(asset);
		return Files.deleteIfExists(assetFile);
	}

	@Override
	abstract public void attach(Path file, Foo asset) throws RepositoryException, IOException;

	@Override
	public boolean detach(Path file, Foo asset) throws RepositoryException, IOException {
		assert opened;
		
		if (isAttached(file, asset))
			return Files.deleteIfExists(file);
		else
			return false;
	}

	@Override
	public Path clone(Foo asset) throws RepositoryException, IOException {
		assert opened;
		
		Path tempFile = Files.createTempFile(tempDirectory, "gat-", ".asset");
		Path assetFile = getAssetFile(asset);
		
		if (Files.notExists(assetFile))
			throw new RepositoryException("unable to clone non-existing asset");
		
		Files.copy(assetFile, tempFile, REPLACE_EXISTING);
		cloneFiles.add(tempFile);
		return tempFile;
	}

	@Override
	public boolean isStored(Foo asset) {
		Path assetFile = getAssetFile(asset);
		return Files.exists(assetFile);
	}

	public Path getAssetDirectory() {
		return assetDirectory;
	}

	public void setAssetDirectory(Path assetDirectory) {
		this.assetDirectory = assetDirectory;
	}

	public Path getTempDirectory() {
		return tempDirectory;
	}

	public void setTempDirectory(Path tempDirectory) {
		this.tempDirectory = tempDirectory;
	}

	@Override
	public boolean isAttached(Path file, Foo asset) {
		if (!Files.exists(file))
			return false;
		if (!isStored(asset))
			return false;
		
		try {
			return Files.isSameFile(file, getAssetFile(asset));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
