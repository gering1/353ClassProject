/*Charlie Raymond, Colton Gering, CPSC353 MWF 9-10am, Michael Fahy
 * Final project
 * this is the user account list class
 * it keeps a list of all the users who have accounts
*/

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


//A class which holds a list of all users and is serialized
public class UserAccountList implements Serializable
{
	//arraylist of all users accounts	
	public ArrayList<UserAccount> users = new ArrayList<UserAccount>();
	private boolean fileWrite = true;

	//prints whole list
	public void printList()
	{
		Iterator<UserAccount> it = users.iterator();
		UserAccount ua;
		while(it.hasNext())
		{
			System.out.println(it.next().toString());
		}
	}

	//adds user to list
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
	
	//finds user in list and returs user
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

	//removes user
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

	//updates userame
	public void updateUserFromString(String oldName, String newData)
	{
		UserAccount ua = findUser(oldName);
		if(ua != null)
		{
			String[] userParts = newData.split(":");
			ua.setUserInfo(userParts[0],userParts[1],userParts[2],userParts[3],userParts[4]);
		}

	}

	//a to string method for outputing data
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

	//checks login
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

	//outputs to a file
	public void outputStream()
	{
		try
		{
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("Users"));
			outputStream.writeObject(this);
			outputStream.close();
		}
		catch(IOException e)
		{
			System.out.println("IO exception");
			System.exit(0);
		}

	}

	//setter function
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

	//to string method
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
