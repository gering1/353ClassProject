import java.io.ObjectOutputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.Serializable;


public class UserAccountList implements Serializable
{

	ArrayList<UserAccount> users = new ArrayList<UserAccount>();

	public void printList()
	{
		Iterator<UserAccount> it = users.iterator();
		UserAccount ua;
		while(it.hasNext())
		{
			System.out.println(it.next().toString());
		}
	}

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

	public UserAccount findUser(String uName)
	{
		Iterator<UserAccount> it = users.iterator();
		UserAccount ua;
		while(it.hasNext())
		{
			ua = it.next();
			if(ua.getUserName().equals(uName))
			{
				return ua;
			}
		}
		return new UserAccount();
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
			outputStream.writeObject(users);
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

			users = (ArrayList<UserAccount>)inputStream.readObject();

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
