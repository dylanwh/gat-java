package gat.repository.fs;

import gat.database.Name;
import gat.repository.Repository;
import gat.repository.RepositoryException;

import java.io.IOException;
import java.nio.file.Path;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class FileRepository implements Repository<FileAsset> {
	BaseRepository repository;
	
	@Inject
	FileRepository(@Named("AssetDirectory") Path assetDirectory,
			RepositoryFormat format) {
		switch (format) {
		case CopyFormat:
			repository = new CopyRepository(assetDirectory);
			break;
		case LinkFormat:
			repository = new LinkRepository(assetDirectory);
			break;
		case SymlinkFormat:
			repository = new SymlinkRepository(assetDirectory);
			break;
		}
		
	}

	public void attach(Path file, FileAsset asset) throws RepositoryException,
			IOException {
		this.repository.attach(file, asset);
	}

	public Path clone(FileAsset asset) throws RepositoryException, IOException {
		return this.repository.clone(asset);
	}

	public void open() throws RepositoryException, IOException {
		this.repository.open();
	}

	public void close() throws RepositoryException, IOException {
		this.repository.close();
	}

	public boolean detach(Path file, FileAsset asset)
			throws RepositoryException, IOException {
		return this.repository.detach(file, asset);
	}

	public boolean isAttached(Path file, FileAsset asset)
			throws RepositoryException {
		return this.repository.isAttached(file, asset);
	}

	public boolean isStored(FileAsset asset) {
		return this.repository.isStored(asset);
	}

	public void init() throws IOException {
		this.repository.init();
	}

	public boolean purge(FileAsset asset) throws RepositoryException,
			IOException {
		return this.repository.purge(asset);
	}

	public FileAsset store(Path file) throws RepositoryException, IOException {
		return this.repository.store(file);
	}

	public FileAsset fetch(Name name) throws RepositoryException, IOException {
		return this.repository.fetch(name);
	} 

}
