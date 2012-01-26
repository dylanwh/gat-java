package gat.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedList;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import jdbm.InverseHashView;
import jdbm.PrimaryStoreMap;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import jdbm.SecondaryHashMap;
import jdbm.SecondaryKeyExtractor;

public class Database {
	private final RecordManager recordManager;
	private final PrimaryStoreMap<Long,Link> links;
	private final InverseHashView<Long,Link> linksInverse;
	private final SecondaryHashMap<Label,Long,Link> linksByLabel;
	private final SecondaryHashMap<Name,Long,Link> linksByName;
	
	private final static SecondaryKeyExtractor<Iterable<Label>,Long,Link> indexLabel = 
			new SecondaryKeyExtractor<Iterable<Label>, Long, Link>() {
		@Override
		public Iterable<Label> extractSecondaryKey(Long key, Link link) {
			return link.getLabels();
		}
	};
	private final static SecondaryKeyExtractor<Name,Long,Link> indexName =
			new SecondaryKeyExtractor<Name, Long, Link>() {
		@Override
		public Name extractSecondaryKey(Long key, Link link) {
			return link.getName();
		}
	};

	/* XXX XXX XXX
	 * From a guice standpoint, this constructor is bad as it does a little
	 * file I/O. However the expense of this is very small and using a factory
	 * is really not warranted in my opinion.
	 * XXX XXX XXX
	 */
	@Inject
	Database(@Named("DatabaseDirectory") Path databaseDirectory) throws IOException {
		super();
		Files.createDirectories(databaseDirectory);
		
		this.recordManager = RecordManagerFactory.createRecordManager(databaseDirectory.resolve("gat").toString());
		this.links         = this.recordManager.storeMap("links");
		this.linksInverse  = links.inverseHashView("linksInverse");
		this.linksByLabel  = links.secondaryHashMapManyToOne("linksByLabel", indexLabel);
		this.linksByName   = links.secondaryHashMap("linksByName", indexName);
	}
	
	public void insertLink(Link link){
		Long recid = linksInverse.findKeyForValue(link);
		if(recid == null)
			links.putValue(link);
		else
			links.put(recid, link);
	}
	
	public Optional<Link> findLink(Name name) throws IOException {
		if (!linksByName.containsKey(name))
			return Optional.absent();
		
		Iterator<Link> links = linksByName.getPrimaryValues(name).iterator();
		assert links.hasNext(): "this should not happen";
		Link link = links.next();
		assert !links.hasNext();
		return Optional.of(link);
	}
	
	public void bind(Label label, Name name) throws IOException {
		Optional<Link> link = findLink(name);
		if (link.isPresent()) {

		}
	}                      	
                           	
	public void unbind(Label label) throws IOException {
		LinkedList<Link> links = Lists.newLinkedList(linksByLabel.getPrimaryValues(label));
		for (Link link: links) {
			link.removeLabel(label);
			insertLink(link);
		}
	}

}
