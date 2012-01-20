package net.hardison.gat;

import java.io.Serializable;
import java.util.Date;

public class Asset implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private byte[] checksum = {'c'};
	private int size = 0;
	private Date mtime = null;
	private String content_type = "";
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public byte[] getChecksum() {
		return checksum;
	}
	public int getSize() {
		return size;
	}
	public Date getMtime() {
		return mtime;
	}
	public String getContent_type() {
		return content_type;
	}
}
