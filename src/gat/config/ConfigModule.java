package gat.config;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

public class ConfigModule extends AbstractModule {
	

	@Override
	protected void configure() {
		bind(Config.class);
	}
	
	@Provides @Named("UserDirectory")
	Path getUserDirectory() {
		return FileSystems.getDefault().getPath(System.getProperty("user.dir"));
	}
	
	@Provides @Named("BaseDirectory")
	Path getBaseDirectory(Config config) {
		return config.getBaseDirectory();
	}
}	