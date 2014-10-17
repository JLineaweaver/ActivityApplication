package testSuite;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

import domainLogic.TestCommandToCreateUser;
import domainLogic.TestCommandToMakeFriendRequest;
import domainLogic.TestCommandToSelectUser;
import domainLogic.TestPerson;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestCommandToSelectUser.class, 
	TestCommandToCreateUser.class,
	TestPerson.class,
	TestCommandToMakeFriendRequest.class})
public class TestSuite {
}



