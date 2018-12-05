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

	public ArrayList<UserAccount> users = new ArrayList<UserAccount>();
	private boolean fileWrite = true;
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
		if(fileWrite)
		{
			outputStream();
		}
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
		return null;
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
		if(fileWrite)
		{
			outputStream();
		}
	}

	public void updateUserFromString(String oldName, String newData)
	{
		UserAccount ua = findUser(oldName);
		if(ua != null)
		{
			String[] userParts = newData.split(":");
			ua.setUserInfo(userParts[0],userParts[1],userParts[2],userParts[3],userParts[4]);
		}

	}

	public void stringToUserList(String data)
	{
		String[] userData = data.split(",");
		users.clear();
		for(int i = 0; i < userData.length;i++)
		{
			String[] uDataParts = userData[i].split(":");
			UserAccount ua = new UserAccount();
			ua.setUserInfo(uDataParts[0],uDataParts[1],uDataParts[2],uDataParts[3],uDataParts[4]);
			addUser(ua);
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
			outputStream.writeObject(this);
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

	public void setFileWrite(boolean flag)
	{
		fileWrite = flag;
	}
	//input object from the file
	public void inputObject()
	{
		try
		{
			ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("Users"));
			UserAccountList in1 = (UserAccountList)inputStream.readObject();
			//users = (ArrayList<UserAccount>)inputStream.readObject();
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
 	public String toString()
	{
		String result = "";
		Iterator<UserAccount> it = users.iterator();
		UserAccount ua;
		boolean first = true;
		while(it.hasNext())
		{
			if(first)
			{
				result = it.next().toString();
				first = false;
			}
			else
			{
				result += "," + it.next().toString();
			}
		}
		return result;
	}
}
