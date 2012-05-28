package gat.repository.fs;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import gat.repository.Asset;
import gat.repository.Repository;
import gat.workspace.Hash;

public class SymlinkRepository extends BaseRepository implements Repository {
	@Inject
	SymlinkRepository(@Named("AssetDirectory") Path assetDirectory) {
		super(assetDirectory);
	}

	@Override
	protected Asset newAsset(Path path, Hash name, BasicFileAttributes attr) {
		// TODO Auto-generated method stub
		return new SymlinkAsset(path, name, attr.size());
	}
}
