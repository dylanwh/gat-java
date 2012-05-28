package gat.repository.fs;

import gat.workspace.Hash;

import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class CopyRepository extends BaseRepository {

	@Inject
	CopyRepository(@Named("AssetDirectory") Path assetDirectory) {
		super(assetDirectory);
	}

	@Override
	protected CopyAsset newAsset(Path assetPath, Hash name, BasicFileAttributes attr) {
		return new CopyAsset(assetPath, name, attr.size());
	}
}
