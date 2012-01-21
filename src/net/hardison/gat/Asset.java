package net.hardison.gat;

import java.io.Serializable;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import static java.util.concurrent.TimeUnit.SECONDS;
import org.apache.commons.codec.binary.Hex;

public class Asset implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private byte[] checksum;
	private String contentType;
	private BasicFileAttributes attributes;
	        
	public byte[] getChecksum() {
		return checksum;
	}
	public void setChecksum(byte[] bs) {
		this.checksum = bs;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public BasicFileAttributes getAttributes() {
		return attributes;
	}
	public void setAttributes(BasicFileAttributes attributes) {
		this.attributes = attributes;
	}
	
	public Date lastModifiedTime() {
		return new Date(attributes.lastModifiedTime().to(SECONDS));
	}
	public long size() {
		return attributes.size();
	}
	
	public String toString() {
		return Hex.encodeHexString(checksum);
	}
}
