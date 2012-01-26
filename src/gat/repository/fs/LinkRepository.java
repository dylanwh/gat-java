package gat.repository.fs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import gat.repository.Repository;
import gat.repository.RepositoryException;

public class LinkRepository extends BaseRepository implements Repository<FileAsset> {

	public LinkRepository(Path assetDirectory) {
		super(assetDirectory);
	}

	@Override
	public FileAsset store(Path path) throws RepositoryException, IOException {
		FileAsset asset = newAsset(path);
		Path assetPath = asset.getPath();
		Files.createDirectories(assetPath.getParent());
		Files.createLink(assetPath, path);
		return asset;
	}

	@Override
	public void attach(Path path, FileAsset asset) throws RepositoryException,
			IOException {
		Files.createDirectories(path.getParent());
		Files.createLink(path, asset.getPath());
	}

	@Override
	protected boolean isAttachedInner(Path path, FileAsset asset)
			throws IOException {
		// TODO Auto-generated method stub
		return Files.isSameFile(path, asset.getPath());
	}

}
