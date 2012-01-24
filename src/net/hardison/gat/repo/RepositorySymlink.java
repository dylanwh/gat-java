package net.hardison.gat.repo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import net.hardison.gat.Repository;
import net.hardison.gat.model.Item;
import net.hardison.gat.repo.RepositoryBase;
import net.hardison.gat.repo.RepositoryException;

public class RepositorySymlink extends RepositoryBase implements Repository {

	@Override
	public void store(Path file, Item asset) throws RepositoryException, IOException {
		Path assetFile = getAssetFile(asset);
		if (Files.notExists(assetFile)) {
			Files.createDirectories(assetFile.getParent());
			Files.move(file, assetFile);
		}
		else
			Files.delete(file);
	}

	@Override
	public void attach(Path file, Item asset) throws RepositoryException, IOException {
		Path assetFile = getAssetFile(asset);
		if (Files.notExists(assetFile))
			throw new RepositoryException("asset does not exist");
		
		Files.deleteIfExists(file);
		Files.createSymbolicLink(file, file.getParent().relativize(assetFile));
	}

}
