package net.hardison.gat;

/** Repository Interface
 * 
 * @author Dylan William Hardison <dylan@hardison.net>
 *
 */
public interface IRepository {
	
	/** Create repository
	 * Perform whatever is required to actualize a repository.
	 * For filesystem-backed repositories this may just be a call to file.mkpath()
	 * where file is the file to the repository's store.
	 */
	void open();
	
	/** Add a file to the repository
	 * <b>note:</b> file may not exist after a call to this method.
	 * The caller must check isAttached() and perform attach() 
	 * afterwards if the continued existence of the file is required.
	 * 
	 * @param file
	 * @return a new Asset object calculated from file.
	 * @throws RepositoryException
	 */
	Asset store(File file) throws RepositoryException;
	
	/** Remove an asset from the Repository
	 * 
	 * @param asset the asset to remove
	 * @throws RepositoryException
	 */
	void  purge(Asset asset) throws RepositoryException;
	
	/** Attach an asset to a file
	 * This is a no-op if isAttached(file, asset) would return true.
	 * This operation may take a while depending on the repository implementation.
	 * 
	 * @param file the file to create
	 * @param asset the asset that file should have attached.
	 * @throws RepositoryException
	 */
	void attach(File file, Asset asset) throws RepositoryException;
	
	/** Remove the attachment from file to asset.
	 * Typically this would just be a file.delete();
	 * @param file
	 * @param asset
	 * @throws RepositoryException
	 */
	void detach(File file, Asset asset) throws RepositoryException;
	
	/** Return a unique file, which references a writable file.
	 * This function is intended to allow one to edit entities in the repository.
	 * 
	 * @param asset
	 * @return a file which is only guaranteed to exist until Repository.close() time.
	 * @throws RepositoryException
	 */
 	File clone(Asset asset) throws RepositoryException;
 	
 	/** Test the attachment between an arbitrary file and an asset.
 	 * 
 	 * @param file
 	 * @param asset
 	 * @return true if the file could conceivably have been created by attach(),
 	 * otherwise false.
 	 * @throws RepositoryException
 	 */
	boolean isAttached(File file, Asset asset) throws RepositoryException;
	
	/** Test if an asset is already stored in the repository.
	 * 
	 * @param asset
	 * @return true if asset exists in the repository.
	 */
	boolean isStored(Asset asset);
	
	
	/** Perform cleanup (if needed).
	 * this may be implemented as a no-op.
	 * Nevertheless, this must be called before the object goes out of scope.
	 * No other method should be called after the repository is closed.
	 * @throws RepositoryException
	 */
	void close() throws RepositoryException;
	

}
;