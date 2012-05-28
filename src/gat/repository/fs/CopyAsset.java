package gat.repository.fs;

import gat.workspace.Hash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CopyAsset extends BaseAsset {

	CopyAsset(Path path, Hash name, long size) {
		super(path, name, size);
	}

	@Override
	public void store(Path path) throws IOException {
		Files.copy(path, getPath());
	}

	@Override
	public void attach(Path path) throws IOException {
		Files.copy(getPath(), path);
	}
	
	@Override
	public boolean isAttached(Path path) {
		return false;
	}
}
