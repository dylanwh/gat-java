package gat.repository.fs;

import gat.repository.Asset;
import gat.workspace.Hash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

abstract class BaseAsset implements Asset {
	private final Path path;
	private final Hash hash;
	private final long size;
	
	BaseAsset(Path path, Hash hash, long size) {
		super();
		this.path = path;
		this.hash = hash;
		this.size = size;
	}

	@Override
	final public Hash getHash() {
		// TODO Auto-generated method stub
		return hash;
	}

	@Override
	final public long getSize() {
		// TODO Auto-generated method stub
		return size;
	}
	
	final Path getPath() {
		return path;
	}

	@Override
	abstract public void attach(Path path) throws IOException;

	@Override
	final public void detach(Path path) throws IOException {
		if (isAttached(path))
			Files.delete(path);
	}

	@Override
	public boolean isAttached(Path path) {
		try {
			return Files.isSameFile(getPath(), path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean isStored() {
		return Files.exists(getPath());
	}
}
