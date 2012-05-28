package gat.workspace;

import java.io.Serializable;

public class Label implements Serializable, Comparable<Label> {
	private static final long serialVersionUID = 1L;
	
	private String value = null;

	Label(String value) {
		assert value != null: "value";
		
		setValue(value);
	}

	public String getValue() {
		return value;
	}

	private void setValue(String val) {
		value = val;
	}

	@Override
	public String toString() {
		return "Label [" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public int compareTo(Label anotherLabel) {
		return value.compareTo(anotherLabel.getValue());
	}	
}
