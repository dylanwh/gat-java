package net.hardison.gat;

/** Base class for Repository-spawned errors.
 * 
 * @author dylan
 */
public class RepositoryException extends Exception {
	private static final long serialVersionUID = 1L;
	private String reason;

	public RepositoryException(String string) {
		setReason(string);
	}

	public String getReason() {
		return reason;
	}

	private void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "RepositoryException [reason=" + reason + "]";
	}
}
