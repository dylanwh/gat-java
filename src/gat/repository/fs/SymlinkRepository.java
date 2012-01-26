package gat.repository.fs;

import gat.repository.RepositoryException;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class SymlinkRepository extends BaseRepository {

	SymlinkRepository(Path assetDirectory) {
		super(assetDirectory);
	}

	@Override
	public FileAsset store(Path path) throws RepositoryException, IOException {
		FileAsset asset = newAsset(path);
		Path assetPath = asset.getPath();
		Files.createDirectories(assetPath.getParent());
		Files.move(path, asset.getPath(), REPLACE_EXISTING, ATOMIC_MOVE);
		return asset;
	}

	@Override
	public void attach(Path path, FileAsset asset) throws RepositoryException,
			IOException {
		Path assetPath = asset.getPath();
		Files.createDirectories(path.getParent());
		Files.createSymbolicLink(path, path.getParent().relativize(assetPath));
	}

	@Override
	protected boolean isAttachedInner(Path file, FileAsset asset)
			throws IOException {
		// TODO Auto-generated method stub
		return Files.isSameFile(file, asset.getPath());
	}

}
