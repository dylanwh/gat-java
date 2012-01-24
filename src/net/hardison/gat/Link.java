package net.hardison.gat;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Link implements Serializable {
	private static final long serialVersionUID = 1L;
	private Name name;
	private Set<Label> labels;
	
	public Link(Name name) {
		setLabels(new HashSet<Label>());
		setName(name);
	}

	public Name getName() {
		return name;
	}

	private void setName(Name name) {
		this.name = name;
	}

	/**
	 * Gets the labels for this instance.
	 *
	 * @return The labels.
	 */
	public Set<Label> getLabels()
	{
		return Collections.unmodifiableSet(this.labels);
	}

	/**
	 * Sets the labels for this instance.
	 *
	 * @param labels The labels.
	 */
	private void setLabels(Set<Label> labels)
	{
		this.labels = labels;
	}
}
