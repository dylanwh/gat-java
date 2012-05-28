package gat.workspace;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

class Bond implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Hash hash;
	private final Set<Label> labels;
	
	Bond(Hash hash) {
		this.labels = new HashSet<Label>();
		this.hash   = hash;
	}

	public Hash getHash() {
		return hash;
	}

	/**
	 * Gets the labels for this instance.
	 *
	 * @return The labels.
	 */
	List<Label> getLabels()
	{
		return Lists.newLinkedList(labels);
	}

	void addLabel(Label label) {
		this.labels.add(label);
	}

	void removeLabel(Label label) {
		this.labels.remove(label);
	}
	
	public boolean hasLabel(Label label) {
		return this.labels.contains(label);
	}
}
