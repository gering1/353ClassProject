import java.io.Serializable;

public class UserAccount implements Serializable
{
	private String username;
	private String password;
	private String authenticationCode;


	public UserAccount()
	{
	}

	public UserAccount(UserAccount user)
	{
		if(user == null)
		{
			System.out.println("Object is null");
			System.exit(0);
		}

		this.username = user.username;
		this.password = user.password;
		this.authenticationCode = user.authenticationCode;
	}

	public UserAccount(String newUsername, String newPass, String newCode)
	{
		setUserInfo(newUsername, newPass, newCode);
	}

	public void setUserInfo(String newUsername, String newPass, String newCode)
	{
		this.username = newUsername;
		this.password = newPass;
		this.authenticationCode = newCode;
	}

	public String toString()
	{
		return("\nUsername : " + username + "\n Password : " +
			       	"\n authentication code : " + authenticationCode);

	}

	public String getUserName()
	{
			return this.username;
	}

	public String getPassword()
	{
			return this.password;
	}
}
