package net.hardison.gat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

import org.apache.commons.codec.digest.DigestUtils;

public class AssetFactory {
	
	public Asset makeAsset(Path file) throws FileNotFoundException, IOException {
		assert file != null;
		assert file.toString() != null;
		
		if (!file.toFile().exists())
			throw new FileNotFoundException(file.toString() + " does not exist");
		
		BasicFileAttributes attr = Files.readAttributes(file,
				BasicFileAttributes.class,
				NOFOLLOW_LINKS
		);
		
		if (!attr.isRegularFile()) 
			throw new FileNotFoundException(file.toString() + " is not a regular file");
		
		try {
			if (!Files.isReadable(file))
				throw new FileNotFoundException(file.toString() + " is not readable");
		}
		catch (NullPointerException n) {
			throw new FileNotFoundException("this is weird");
		}
		
		InputStream stream = Files.newInputStream(file);
		Asset asset = new Asset();
		asset.setAttributes(attr);
		asset.setChecksum(DigestUtils.md5(stream));
		asset.setContentType("text/plain");
		stream.close();
		return asset;		
	}
}
