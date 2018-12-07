/*Charlie Raymond, Colton Gering, CPSC353 MWF 9-10am, Michael Fahy
Final project
this is the account class
it keeps information for each user
*/


import java.io.Serializable;


//class is serializable so we can store the data easily
public class UserAccount implements Serializable
{

	//user data we collect
	private String username;
	private String password;
	private Integer age;
	private String artist = "x";
	private String track = "x";
	private String authenticationCode;


	//basic constructor
	public UserAccount()
	{
	}

	//copy constructor
	public UserAccount(UserAccount user)
	{
		if(user == null)
		{
			System.out.println("Object is null");
			System.exit(0);
		}
		this.age = user.age;
		this.username = user.username;
		this.password = user.password;
		this.authenticationCode = user.authenticationCode;
	}

	//constructor to initialize variables
	public UserAccount(String newUsername, String newPass, String newCode, String newTrack, String newArtist)
	{
		setUserInfo(newUsername, newPass, newCode,newTrack,newArtist);
	}

	//sets the info
	public void setUserInfo(String newUsername, String newPass, String newCode, String newTrack, String newArtist)
	{
		this.username = newUsername;
		this.password = newPass;
		this.authenticationCode = newCode;
		this.track = newTrack;
		this.artist = newArtist;
	}

	//to string method
	public String toString()
	{
		return(username + ":" + password + ":" + authenticationCode + ":" + track + ":" + artist);
	}


	//getter & setter functions
	public String getUserName()
	{
			return this.username;
	}

	public void setUserName(String username)
	{
		this.username = username;
	}
	public Integer getAge()
	{
		return this.age;
	}

	public void setAge()
	{
		this.age = age;
	}

	public String getTrack()
	{
		return this.track;
	}
	public void setTrack(String trck)
	{
		this.track = trck;
	}
	public String getArtist()
	{
		return this.artist;
	}
	public void setArtist(String arst)
	{
		this.artist = arst;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getPassword()
	{
			return this.password;
	}
}
