package net.hardison.gat;

import java.io.IOException;

public abstract class Repository implements IRepository {
	private File assetDirectory = null;
	private File tempDirectory = null;
	private boolean opened = false;

	
	private File getAssetFile(Asset asset) {
		return new File(getAssetDirectory().getPath(), asset.getChecksum());
	}

	@Override
	public void open() {
		// TODO Auto-generated method stub
		this.setOpened(true);
	}
	
	@Override
	public abstract Asset store(File file) throws RepositoryException;

	@Override
	public boolean purge(Asset asset) throws RepositoryException {
		assertOpened();
		
		File assetFile = getAssetFile(asset);
		return assetFile.delete();
	}

	@Override
	public abstract void attach(File file, Asset asset) throws RepositoryException;

	@Override
	public boolean detach(File file, Asset asset) throws RepositoryException {
		assertOpened();
		
		if (isAttached(file, asset))
			return file.delete();
		else
			return false;
	}

	@Override
	public File clone(Asset asset) throws RepositoryException, IOException {
		assertOpened();
		File tempFile = new File(File.createTempFile("temp", ".asset", getTempDirectory()));
		File assetFile = getAssetFile(asset);
		assetFile.copy(tempFile);
		tempFile.deleteOnExit();
		return tempFile;
	}

	@Override
	public abstract boolean isAttached(File file, Asset asset);

	@Override
	public boolean isStored(Asset asset) {
		File assetFile = getAssetFile(asset);
		return assetFile.exists();
	}

	@Override
	public void close() throws RepositoryException {
		// TODO Auto-generated method stub

	}

	public File getAssetDirectory() {
		return assetDirectory;
	}

	public void setAssetDirectory(File assetDirectory) {
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

	public File getTempDirectory() {
		return tempDirectory;
	}

	public void setTempDirectory(File tempDirectory) {
		this.tempDirectory = tempDirectory;
	}

}
