package gat.repository.fs;

import gat.workspace.Hash;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class LinkRepository extends BaseRepository {

	@Inject
	LinkRepository(@Named("AssetDirectory") Path assetDirectory) {
		super(assetDirectory);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected LinkAsset newAsset(Path path, Hash name, BasicFileAttributes attr) {
		// TODO Auto-generated method stub
		return new LinkAsset(path, name, attr.size());
	}

}
