package Runner;
import java.util.Scanner;
import java.io.FileNotFoundException;


import mockedUI.UserThread;
import domainLogic.UnitOfWork;

/**
 *Runs through the files and executes the commands.
 *Prompts you with a question asking you the name of the file that you want to run through.
 *If you want to execute multiple files, respond yes to "Continue?"
 */
public class Runner 
{
	public static void main(String[] args) throws FileNotFoundException
	{
		UnitOfWork.newCurrent();
		Scanner input = new Scanner(System.in);
		System.out.println("What is the name of the file?");
		String file = input.next();
		UserThread t = new UserThread(file);
		t.run();
		System.out.println("Continue? (yes/no)");
		String response = input.next();
		if(response.equalsIgnoreCase("yes"))
			main(args);
	}
}
