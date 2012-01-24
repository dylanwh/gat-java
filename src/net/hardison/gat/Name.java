package net.hardison.gat;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class Name implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Hex hex = new Hex();
	
	private byte[] bytes;
	
	public Name(byte[] bytes) {
		setBytes(bytes);
	}
	
	public Name(String string) throws DecoderException {
		setBytes(hex.decode(string.getBytes()));
	}

	public byte[] getBytes() {
		return this.bytes;
	}

	private void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	public String toString() {
		return new String(Hex.encodeHexString(bytes));
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
		if (!(obj instanceof Name))
			return false;
		Name other = (Name) obj;
		if (!Arrays.equals(this.bytes, other.bytes))
			return false;
		return true;
	}
}
