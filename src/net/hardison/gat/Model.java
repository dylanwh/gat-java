package net.hardison.gat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import jdbm.PrimaryHashMap;
import jdbm.PrimaryStoreMap;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import jdbm.SecondaryHashMap;
import jdbm.SecondaryKeyExtractor;

public class Model {

	private Path databaseDirectory = null;
	private RecordManager recMan = null;
	private PrimaryHashMap<Name,HashSet<Label>> links = null;
	private SecondaryHashMap<Label,Name,HashSet<Label>> linksByLabel = null;
	
	private SecondaryKeyExtractor<Iterable<Label>,Name,HashSet<Label>> indexLabel = 
			new SecondaryKeyExtractor<Iterable<Label>, Name, HashSet<Label>>() {
		@Override
		public Iterable<Label> extractSecondaryKey(Name key, HashSet<Label> labels) {
			return Collections.unmodifiableSet(labels);
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
			links           = recMan.hashMap("links");
			linksByLabel    = links.secondaryHashMapManyToOne("linksByLabel", indexLabel);
		}
	}

	public void close() throws IOException {
		links = null;
		linksByLabel = null;
		recMan.close();
		recMan = null;
	}
	
	public Path getDatabaseDirectory() {
		return databaseDirectory;
	}

	public void setDatabaseDirectory(Path value) {
		databaseDirectory = value;
	}

	public Name getName(Label label) throws IOException {
		Iterable<Name> names = linksByLabel.get(label);
		if (names == null)
			throw new IOException("foo");
		
		Iterator<Name> iter  = names.iterator();
		if (!iter.hasNext())
			return null;

		Name name = iter.next();
		assert !iter.hasNext(): "unique";
		return name;
	}
	
	public HashSet<Label> getLabels(Name name) {
		return links.get(name);
	}

	public void bind(Label label, Name name) throws IOException {
		HashSet<Label> labels = links.find(name);
		if (labels == null) {
			System.out.println("is null");
			labels = new HashSet<Label>();
		}

		labels.add(label);
		links.put(name, labels);
		System.out.println(labels);
		recMan.commit();
	}

}
