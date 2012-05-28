package gat.repository;


import gat.workspace.Hash;

import java.io.IOException;
import java.nio.file.Path;

/** Repository Interface
 * 
 * @author Dylan William Hardison <dylan@hardison.net>
 *
 */
public interface Repository<A extends Asset> {
	
	/** Create repository
	 * Perform whatever is required to actualize a repository.
	 * For filesystem-backed repositories this may just be a call to file.mkpath()
	 * where file is the file to the repository's store.
	 * @throws IOException 
	 */
	void init() throws IOException;

	/** Add a file to the repository
	 * <b>note:</b> file may not exist after a call to this method.
	 * The caller must check isAttached() and perform attach() 
	 * afterwards if the continued existence of the file is required.
	 * 
	 * @param file
	 * @return a new Asset object calculated from fiNamerle.
	 * @throws RepositoryException
	 * @throws IOException 
	 */
	A store(Path file) throws IOException, RepositoryException;
	A fetch(Hash hash) throws IOException;
	
	void delete(Hash hash) throws IOException;
	boolean contains(Hash hash);
	
	Iterable<A> assets();
}