package gat.database;

import java.io.Serializable;

public class Label implements Serializable, Comparable<Label> {
	private static final long serialVersionUID = 1L;
	
	private String name = null;

	
	public Label(String name) {
		assert name != null: "name";
		
		setName(name);
	}

	public String getName() {
		return name;
	}

	private void setName(String val) {
		name = val;
	}

	@Override
	public String toString() {
		return "Label [" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Label other = (Label) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public int compareTo(Label anotherLabel) {
		return name.compareTo(anotherLabel.getName());
	}	
}
