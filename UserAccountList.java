import java.util.*;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;


public class UserAccountList implements Serializable
{

	ArrayList<UserAccount> users = new ArrayList<UserAccount>();


	public void addUser(UserAccount newUser)
	{
		if(newUser == null)
		{
			System.out.println("Object is empty");
			System.exit(0);
		}

		users.add(newUser);
		outputStream();
	}

	public void removeUser(String userName)
	{
		Iterator<UserAccount> it = users.iterator();

		while(it.hasNext())
		{
				if(it.next().getUserName().equals(userName))
				{
						it.remove();
				}
		}
		outputStream();
	}

	public boolean login(String userName, String passWord)
	{
			Iterator<UserAccount> it = users.iterator();
			UserAccount ua;
			while(it.hasNext())
			{
				  ua = it.next();
					if(ua.getUserName().equals(userName))
					{
							if(ua.getPassword().equals(passWord))
							{
									return true;
							}
					}
			}
			return false;
	}

	public void outputStream()
	{
		try
		{
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Users"));

			System.out.println("Working");
			//outputStream.writeObject(user);
			System.out.println("Working");
			//System.out.println(this.accountAmount);

			outputStream.close();
		}
		catch(IOException e)
		{
			System.out.println("IO exception");
			System.exit(0);
		}

	}

	//input object from the file
	public void inputObject()
	{
		try
		{
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Users"));

			UserAccountList in1 = (UserAccountList)inputStream.readObject();

			users = in1.users;

			inputStream.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Cannot find file");
			System.out.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.out.println("Serious error");
			System.out.println(e.getMessage());
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Class not found");
			System.out.println(e.getMessage());
		}

	}

}
