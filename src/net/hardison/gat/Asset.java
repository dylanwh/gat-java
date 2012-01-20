package net.hardison.gat;

import java.io.Serializable;
import java.util.Date;

public class Asset implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String checksum;
	private int size;
	private Date mtime;
	private String content_type;
	
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public Date getMtime() {
		return mtime;
	}
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
	
	public String getContent_type() {
		return content_type;
	}
	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}
}
