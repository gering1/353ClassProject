import java.io.Serializable;

public class UserAccount implements Serializable
{
	private String username;
	private String password;
	private Integer age;
	private String artist = "x";
	private String track = "x";
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
		this.age = user.age;
		this.username = user.username;
		this.password = user.password;
		this.authenticationCode = user.authenticationCode;
	}

	public UserAccount(String newUsername, String newPass, String newCode, String newTrack, String newArtist)
	{
		setUserInfo(newUsername, newPass, newCode,newTrack,newArtist);
	}

	public void setUserInfo(String newUsername, String newPass, String newCode, String newTrack, String newArtist)
	{
		this.username = newUsername;
		this.password = newPass;
		this.authenticationCode = newCode;
		this.track = newTrack;
		this.artist = newArtist;
	}

	public String toString()
	{
		return(username + ":" + password + ":" + authenticationCode + ":" + track + ":" + artist);

	}



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
