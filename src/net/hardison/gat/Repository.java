package net.hardison.gat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Repository implements IRepository {
	private AssetFactory assetFactory = null;
	private Path assetDirectory = null;
	private Path tempDirectory = null;
	private boolean opened = false;

	protected Path getAssetFile(Asset asset) {
		return assetDirectory.resolve(asset.getChecksum());
	}

	@Override
	public void open() throws IOException {
		// TODO Auto-generated method stub
		this.setOpened(true);
		Files.createDirectories(assetDirectory);
		Files.createDirectories(tempDirectory);
	}
	
	@Override
	public Asset store(Path file) throws RepositoryException, IOException {
		assertOpened();
		
		Asset asset = assetFactory.makeAsset(file);
		if (asset == null)
			throw new RepositoryException("AssetFactory returned null!");
		
		store(file, asset);
		return asset;
	}

	abstract void store(Path file, Asset asset) throws RepositoryException, IOException;

	@Override
	public boolean purge(Asset asset) throws RepositoryException, IOException {
		assertOpened();
		
		Path assetFile = getAssetFile(asset);
		return Files.deleteIfExists(assetFile);
	}

	@Override
	public abstract void attach(Path file, Asset asset) throws RepositoryException, IOException;

	@Override
	public boolean detach(Path file, Asset asset) throws RepositoryException, IOException {
		assertOpened();
		
		if (isAttached(file, asset))
			return Files.deleteIfExists(file);
		else
			return false;
	}

	@Override
	public Path clone(Asset asset) throws RepositoryException, IOException {
		assertOpened();
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

	@Override
	public void close() throws RepositoryException {
		setOpened(false);
	}

	public Path getAssetDirectory() {
		return assetDirectory;
	}

	public void setAssetDirectory(Path assetDirectory) {
		this.assetDirectory = assetDirectory;
	}

	public boolean isOpened() {
		return opened;
	}

	private void setOpened(boolean opened) {
		this.opened = opened;
	}
	
	private void assertOpened() throws RepositoryException {
		if (!isOpened())
			throw new RepositoryException("repository is not opened!");
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
