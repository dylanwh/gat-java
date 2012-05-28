package gat.workspace;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.LinkedList;
import com.google.common.base.Optional;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import jdbm.InverseHashView;
import jdbm.PrimaryStoreMap;
import jdbm.RecordManager;
import jdbm.RecordManagerFactory;
import jdbm.SecondaryHashMap;
import jdbm.SecondaryKeyExtractor;

public class BondManager {
	private final RecordManager                     recordManager;
	private final PrimaryStoreMap<Long,Bond>        bonds;
	private final InverseHashView<Long,Bond>        bondsInverse;
	private final SecondaryHashMap<Label,Long,Bond> bondsByLabel;
	private final SecondaryHashMap<Hash,Long,Bond>  bondsByHash;
	
	/* XXX XXX XXX
	 * From a guice standpoint, this constructor is bad as it does a little
	 * file I/O. However the expense of this is very small and using a factory
	 * is really not warranted in my opinion.
	 * XXX XXX XXX
	 */
	@Inject
	BondManager(@Named("DatabaseDirectory") Path databaseDirectory) throws IOException {
		super();
		
		this.recordManager = RecordManagerFactory.createRecordManager(databaseDirectory.resolve("gat").toString());
		this.bonds         = this.recordManager.storeMap("bonds");
		this.bondsInverse  = bonds.inverseHashView("bondsInverse");
		this.bondsByLabel  = bonds.secondaryHashMapManyToOne("bondsByLabel", indexLabel);
		this.bondsByHash   = bonds.secondaryHashMap("bondsByHash", indexHash);
	}
	
	private final static SecondaryKeyExtractor<Iterable<Label>,Long,Bond> indexLabel = 
			new SecondaryKeyExtractor<Iterable<Label>, Long, Bond>() {
		@Override
		public Iterable<Label> extractSecondaryKey(Long key, Bond bond) {
			return bond.getLabels();
		}
	};
	private final static SecondaryKeyExtractor<Hash,Long,Bond> indexHash =
			new SecondaryKeyExtractor<Hash, Long, Bond>() {
		@Override
		public Hash extractSecondaryKey(Long key, Bond bond) {
			return bond.getHash();
		}
	};
	
	private void insertBond(Bond bond){
		Long recid = bondsInverse.findKeyForValue(bond);
		if(recid == null)
			bonds.putValue(bond);
		else
			bonds.put(recid, bond);
	}
	
	private <K> Optional<Bond> findOne(SecondaryHashMap<K, Long, Bond> map, K key) {
		Iterator<Bond> bonds = map.getPrimaryValues(key).iterator();
		
		return Iterators.tryFind(bonds, Predicates.notNull());
	}
	
	private <K> Iterable<Bond> findMany(SecondaryHashMap<K, Long, Bond> map, K key) {
		return map.getPrimaryValues(key);
	}
	
	public void bind(Label label, Hash hash) throws IOException {
		Optional<Bond> optionalBond = findOne(bondsByHash, hash);
		Bond bond;
		
		if (optionalBond.isPresent()) {
			bond = optionalBond.get();
		}
		else {
			bond = new Bond(hash);
		}
		
		bond.addLabel(label);
		insertBond(bond);
	}                      	
                           	
	public void unbind(Label label) throws IOException {
		LinkedList<Bond> bonds = Lists.newLinkedList(bondsByLabel.getPrimaryValues(label));
		for (Bond bond: bonds) {
			bond.removeLabel(label);
			insertBond(bond);
		}
	}

	public Optional<Hash> findHash(Label label) {
		Iterators.tryFind(bondsByLabel.getPrimaryValues(label).iterator(), Predicates.notNull());
		
		
		
		
		return null;
	}

}
