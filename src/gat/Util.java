package gat;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.common.base.Optional;

import gat.database.Name;

public final class Util {
	public static Name newName(Path file) throws IOException {
		InputStream stream = null;
		Optional<Name> name = Optional.absent();
		
		try {
			stream = Files.newInputStream(file);
			name = Optional.of(new Name(DigestUtils.md5(stream)));
		} finally {
			if (stream != null)
				stream.close();
		}
		if (name.isPresent())
			return name.get();
		else 
			throw new IOException(file.toString() + ": failed to get md5sum");
	}
}
