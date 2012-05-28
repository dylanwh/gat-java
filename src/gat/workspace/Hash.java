package gat.workspace;

import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Arrays;

import javax.xml.bind.DatatypeConverter;

public class Hash implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final byte[] bytes;
	
	Hash(byte[] bytes) {
		this.bytes = bytes;
	}
	
	Hash(String string) {
		this.bytes = DatatypeConverter.parseHexBinary(string);
	}

	public byte[] getBytes() {
		return this.bytes;
	}
	
	public String toString() {
		return new String(DatatypeConverter.printHexBinary(bytes));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(this.bytes);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Hash))
			return false;
		Hash other = (Hash) obj;
		if (!Arrays.equals(this.bytes, other.bytes))
			return false;
		return true;
	}
}
