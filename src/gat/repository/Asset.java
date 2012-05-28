package gat.repository;

import gat.workspace.Hash;

import java.io.IOException;
import java.nio.file.Path;

public interface Asset {
	Hash getHash();
	long getSize();
	

	void attach(Path path) throws IOException;
	void detach(Path path) throws IOException;
	
	boolean isAttached(Path path);
	boolean isStored();
	
}
 