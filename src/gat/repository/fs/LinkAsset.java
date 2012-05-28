package gat.repository.fs;

import gat.repository.Asset;
import gat.workspace.Hash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LinkAsset extends BaseAsset implements Asset {

	LinkAsset(Path path, Hash name, long size) {
		super(path, name, size);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void store(Path path) throws IOException {
		Files.createLink(getPath(), path);
	}

	@Override
	public void attach(Path path) throws IOException {
		Files.createLink(path, getPath());
	}
}