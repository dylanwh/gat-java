package net.hardison.gat;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;

public class RepositoryLink extends Repository implements IRepository {

	@Override
	void store(Path file, Asset asset) throws RepositoryException, IOException {
		Path assetFile = getAssetFile(asset);
		Files.createLink(file, assetFile);
	}

	@Override
	public void attach(Path file, Asset asset) throws RepositoryException, IOException {
		Path assetFile = getAssetFile(asset);
		Files.createLink(file, assetFile);
	}

	@Override
	public boolean isAttached(Path file, Asset asset) {
		if (!Files.exists(file))
			return false;
		if (!isStored(asset))
			return false;
		
		try {
			return Files.isSameFile(file, getAssetFile(asset));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
