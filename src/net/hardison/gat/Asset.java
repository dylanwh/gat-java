package net.hardison.gat;

import java.io.Serializable;
import java.util.Date;

public class Asset implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String checksum = "foo";
	private int size = 0;
	private Date mtime = null;
	private String content_type = "";
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getChecksum() {
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
