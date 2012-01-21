package net.hardison.gat;

import java.io.IOException;

public interface IModel {
	
	/**
	 * Create the database files.
	 * This is idempotent.
	 */
	void create() throws IOException;
	
	void open();
	void close();
	
	/**
	 * associate a label with an asset.
	 * This is idempotent.
	 * @param label
	 * @param asset
	 */
	void bind(Label label, Asset asset);
	
	/**
	 * remove the association between a label and asset.
	 * This is idempotent.
	 * @param label
	 * @param asset
	 */
	void unbind(Label label, Asset asset);
	
	Asset findAsset(String checksum) throws AssetNotFound;
	Label findLabel(String name)     throws LabelNotFound;
	
	// TODO: some API for iterating over assets/labels.
	
}
