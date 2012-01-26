package gat.repository;

import gat.database.Name;


public interface Asset {
	Name getName();
	long getSize();
	boolean isSameAsset(Asset other);
}
