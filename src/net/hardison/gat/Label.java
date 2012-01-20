package net.hardison.gat;

import java.io.Serializable;

public class Label implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name = null;
	
	public Label(String name) {
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
	
}
