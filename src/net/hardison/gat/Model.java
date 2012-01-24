package net.hardison.gat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

import jdbm.InverseHashView;
import jdbm.PrimaryHashMap;
import jdbm.PrimaryStoreMap;
import jdbm.PrimaryTreeMap;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import jdbm.SecondaryHashMap;
import jdbm.SecondaryKeyExtractor;
import jdbm.SecondaryTreeMap;

public class Model {

	private Path databaseDirectory = null;
	private RecordManager recMan = null;
	private PrimaryStoreMap<Long,Link> links = null;
	private InverseHashView<Long,Link> linksInverse = null;
	private SecondaryHashMap<Label,Long,Link>  linksByLabel = null;
	private SecondaryHashMap<Name,Long,Link> linksByName = null;
	
	private SecondaryKeyExtractor<Iterable<Label>,Long,Link> indexLabel = 
			new SecondaryKeyExtractor<Iterable<Label>, Long, Link>() {
		@Override
		public Iterable<Label> extractSecondaryKey(Long key, Link link) {
			return link.getLabels();
		}
	};
	
	private SecondaryKeyExtractor<Name,Long,Link> indexName = 
			new SecondaryKeyExtractor<Name, Long, Link>() {
		@Override
		public Name extractSecondaryKey(Long key, Link link) {
			return link.getName();
		}
	};
	
	public void create() throws IOException {
		assert databaseDirectory != null;
		Files.createDirectories(databaseDirectory);
	}
	
	public void open() throws IOException {
		if (recMan == null) {
			recMan = RecordManagerFactory.createRecordManager(
					databaseDirectory.resolve("gat.db").toString());
			links           = recMan.storeMap("links");
			linksInverse    = links.inverseHashView("linksInverse");
			linksByLabel    = links.secondaryHashMapManyToOne("linksByLabel", indexLabel);
			linksByName     = links.secondaryHashMap("linksByName", indexName);
		}
	}

	public void close() throws IOException {
		links = null;
		linksInverse = null;
		linksByLabel = null;
		linksByName  = null;
		recMan.close();
		recMan = null;
	}
	
	public void insertLink(Link link) {
		Long id = linksInverse.findKeyForValue(link);
		if (id == null) {
			links.putValue(link);
		}
		else {
			links.put(id, link);
		}
	}

	public Path getDatabaseDirectory() {
		return databaseDirectory;
	}

	public void setDatabaseDirectory(Path value) {
		databaseDirectory = value;
	}

	public Link getLink(Label label) {
		Iterator<Link> iter = linksByLabel.getPrimaryValues(label).iterator();
		if (iter.hasNext()) {
			Link link = iter.next();
			assert !iter.hasNext(): "only one";
			return link;
		}
		else {
			return null;
		}
	}
	
	public Link getLink(Name name) {
		Iterator<Link> iter = linksByName.getPrimaryValues(name).iterator();
		if (iter.hasNext()) {
			Link link = iter.next();
			assert !iter.hasNext(): "only one";
			return link;
		}
		else {
			return null;
		}
	}
}
