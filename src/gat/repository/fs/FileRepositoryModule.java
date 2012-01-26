package gat.repository.fs;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;


public class FileRepositoryModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(FileRepository.class);
	}
	
	@Provides @Named("AssetDirectory")
	Path getAssetDirectory(@Named("BaseDirectory") Path baseDirectory) {
		return baseDirectory.resolve(".gat/asset");
	}
	
	@Provides RepositoryFormat
	getRepositoryFormat() {
		return RepositoryFormat.LinkFormat;
	}
		
		
}
