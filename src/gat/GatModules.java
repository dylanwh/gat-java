package gat;

import gat.config.ConfigModule;
import gat.database.DatabaseModule;
import gat.repository.fs.FileRepositoryModule;

import com.google.inject.AbstractModule;


public class GatModules extends AbstractModule {

	@Override
	protected void configure() {
		install(new ConfigModule());
		install(new DatabaseModule());
		install(new FileRepositoryModule());
	}
}
