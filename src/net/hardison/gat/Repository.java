package net.hardison.gat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public abstract class Repository implements IRepository {
	private AssetFactory assetFactory = null;
	private Path assetDirectory = null;
	private Path tempDirectory = null;
	private ArrayList<Path> cloneFiles = null;
	private boolean opened = false;

	protected Path getAssetFile(Asset asset) {
		return assetDirectory.resolve(asset.toString());
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
	public Asset store(Path file) throws RepositoryException, IOException {
		assert opened;
		
		Asset asset = assetFactory.makeAsset(file);
		if (asset == null)
			throw new RepositoryException("AssetFactory returned null!");
		
		store(file, asset);
		return asset;
	}

	abstract void store(Path file, Asset asset) throws RepositoryException, IOException;

	@Override
	public boolean purge(Asset asset) throws RepositoryException, IOException {
		assert opened;
		
		Path assetFile = getAssetFile(asset);
		return Files.deleteIfExists(assetFile);
	}

	@Override
	public abstract void attach(Path file, Asset asset) throws RepositoryException, IOException;

	@Override
	public boolean detach(Path file, Asset asset) throws RepositoryException, IOException {
		assert opened;
		
		if (isAttached(file, asset))
			return Files.deleteIfExists(file);
		else
			return false;
	}

	@Override
	public Path clone(Asset asset) throws RepositoryException, IOException {
		assert opened;
		Path tempFile = Files.createTempFile(tempDirectory, "gat", ".asset");
		Path assetFile = getAssetFile(asset);
		Files.copy(assetFile, tempFile);
		return tempFile;
	}

	@Override
	public abstract boolean isAttached(Path file, Asset asset);

	@Override
	public boolean isStored(Asset asset) {
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

	public AssetFactory getAssetFactory() {
		return assetFactory;
	}

	public void setAssetFactory(AssetFactory assetFactory) {
		this.assetFactory = assetFactory;
	}

}
