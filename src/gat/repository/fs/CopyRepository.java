package gat.repository.fs;

import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import gat.repository.Repository;
import gat.repository.RepositoryException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CopyRepository extends BaseRepository {

	public CopyRepository(Path assetDirectory) {
		super(assetDirectory);
	}

	@Override
	public FileAsset store(Path path) throws RepositoryException, IOException {
		FileAsset asset = newAsset(path);
		Path assetPath = asset.getPath();
		Files.createDirectories(assetPath.getParent());
		Files.copy(path, assetPath, COPY_ATTRIBUTES, REPLACE_EXISTING);
		return asset;
	}

	@Override
	public void attach(Path path, FileAsset asset) throws RepositoryException,
			IOException {
		Files.createDirectories(path.getParent());
		Files.copy(asset.getPath(), path, COPY_ATTRIBUTES, REPLACE_EXISTING);
	}

	@Override
	protected boolean isAttachedInner(Path path, FileAsset asset)
			throws IOException {
		return newAsset(path).isSameAsset(asset);
	}

}
