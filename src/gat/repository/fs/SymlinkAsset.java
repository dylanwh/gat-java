package gat.repository.fs;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import gat.workspace.Hash;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SymlinkAsset extends BaseAsset {
	
	SymlinkAsset(Path path, Hash name, long size) {
		super(path, name, size);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void store(Path path) throws IOException {
		Files.move(path, getPath(), ATOMIC_MOVE, REPLACE_EXISTING);
	}

	@Override
	public void attach(Path path) throws IOException {
		Files.createSymbolicLink(path, getPath());
	}										

}
