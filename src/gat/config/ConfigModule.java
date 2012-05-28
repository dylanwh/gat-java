package gat.config;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DateFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

public class ConfigModule extends AbstractModule {
	@Override
	protected void configure() {
		// TODO
	}
	
	@Provides
	Gson getGson() {
		 Gson gson = new GsonBuilder()
	     .serializeNulls()
	     .setDateFormat(DateFormat.LONG)
	     .setPrettyPrinting()
	     .setVersion(1.0)
	     .create();
		 
		 return gson;
	}
	
	@Provides @Named("UserDirectory")
	Path getUserDirectory() {
		return FileSystems.getDefault().getPath(System.getProperty("user.dir"));
	}
	

}	