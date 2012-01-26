package gat.database;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;

public class Link implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Name name;
	private final Set<Label> labels;
	
	public Link(Name name) {
		this.labels = new HashSet<Label>();
		this.name   = name;
	}

	public Name getName() {
		return name;
	}

	/**
	 * Gets the labels for this instance.
	 *
	 * @return The labels.
	 */
	public List<Label> getLabels()
	{
		return Lists.newLinkedList(labels);
	}

	
	public void addLabel(Label label) {
		this.labels.add(label);
	}

	public void removeLabel(Label label) {
		this.labels.remove(label);
	}
	
	public boolean hasLabel(Label label) {
		return this.labels.contains(label);
	}
}
