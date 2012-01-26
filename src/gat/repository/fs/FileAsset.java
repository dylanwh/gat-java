package gat.repository.fs;

import gat.database.Name;
import gat.repository.Asset;

import java.nio.file.Path;

import com.google.inject.Inject;


public class FileAsset implements Asset {
	private final Path path;
	private final Name name;
	private final long size;
	
	FileAsset(Path path, Name name, long size) {
		this.path = path;
		this.name = name;
		this.size = size;
	}

	Path getPath() {
		return path;
	}

	@Override
	public Name getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public long getSize() {
		// TODO Auto-generated method stub
		return size;
	}
	
	@Override
	public boolean isSameAsset(Asset other) {
		return this.name.equals( other.getName() );
	}

}
