package gat.repository.fs;

import com.google.inject.AbstractModule;
import com.google.inject.Module;

public class FileRepositoryModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(LinkRepository.class);
		bind(CopyRepository.class);
		bind(SymlinkRepository.class);
		
	}

}
