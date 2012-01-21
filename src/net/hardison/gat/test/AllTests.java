package net.hardison.gat.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	LabelFactoryTest.class,
	AssetFactoryTest.class,
	RepositoryLinkTest.class 
})
public class AllTests {

}
