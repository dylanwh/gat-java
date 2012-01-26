package gat;

import gat.database.Database;
import gat.repository.RepositoryException;
import gat.repository.fs.FileRepository;

import java.io.IOException;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {

	public static void main(String[] args) {
		Injector injector   = Guice.createInjector(new GatModules());
		FileRepository repo = injector.getInstance(FileRepository.class);
		Database db         = injector.getInstance(Database.class);
		
		
		
		try {
			repo.init();
			repo.open();
			System.out.println(db);
		} catch (RepositoryException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
