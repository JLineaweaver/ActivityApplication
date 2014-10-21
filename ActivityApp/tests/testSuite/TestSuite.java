package testSuite;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

import domainLogic.TestCommandToAcceptFriendRequest;
import domainLogic.TestCommandToCreateUser;
import domainLogic.TestCommandToGetPendingIncomingFriendList;
import domainLogic.TestCommandToMakeFriendRequest;
import domainLogic.TestCommandToSelectUser;
import domainLogic.TestPerson;
import domainLogic.TestUnitOfWork;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestCommandToSelectUser.class, 
	TestCommandToCreateUser.class,
	TestPerson.class,
	TestCommandToMakeFriendRequest.class,
	TestCommandToAcceptFriendRequest.class,
	TestUnitOfWork.class,
	TestCommandToGetPendingIncomingFriendList.class})
public class TestSuite {
}



