package gat.database;

import java.nio.file.Path;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;

public class DatabaseModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(Database.class);
	}
	
	@Provides @Named("DatabaseDirectory")
	Path getDatabaseDirectory(@Named("BaseDirectory") Path baseDirectory) {
		return baseDirectory.resolve(".gat/data");
	}
}
