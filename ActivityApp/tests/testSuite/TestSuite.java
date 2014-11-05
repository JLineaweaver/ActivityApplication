package testSuite;

import mockedUI.UserThreadTests;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

import Gateways.TestPersonRowDataGateway;
import domainLogic.TestCommandToAcceptFriendRequest;
import domainLogic.TestCommandToCancelChanges;
import domainLogic.TestCommandToCreateUser;
import domainLogic.TestCommandToGetPendingIncomingFriendList;
import domainLogic.TestCommandToGetPendingOutgoingFriendList;
import domainLogic.TestCommandToMakeFriendRequest;
import domainLogic.TestCommandToModifyUser;
import domainLogic.TestCommandToPersistChanges;
import domainLogic.TestCommandToRejectFriend;
import domainLogic.TestCommandToSelectUser;
import domainLogic.TestCommandToUnfriend;
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
	TestCommandToGetPendingIncomingFriendList.class,
	TestCommandToGetPendingOutgoingFriendList.class,
	TestCommandToCancelChanges.class,
	TestCommandToModifyUser.class,
	TestCommandToRejectFriend.class,
	TestCommandToPersistChanges.class,
	TestCommandToUnfriend.class,
	UserThreadTests.class,
	TestPersonRowDataGateway.class})
public class TestSuite {
}



