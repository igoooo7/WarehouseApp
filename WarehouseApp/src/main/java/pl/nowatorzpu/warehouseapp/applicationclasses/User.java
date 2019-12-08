package pl.nowatorzpu.warehouseapp.applicationclasses;

public class User {

	private Integer userId;
	private String jobTitle;
	private String userRights;
	private Integer userRightsLevel;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String firstName;
	private String lastName;
	private Boolean logged;

	public User(Integer userId, String jobTitle, String userRights, Integer userRightsLevel, String userName,
			String userPassword, String userEmail, String firstName, String lastName, Boolean logged) {
		super();
		this.userId = userId;
		this.jobTitle = jobTitle;
		this.userRights = userRights;
		this.userRightsLevel = userRightsLevel;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.firstName = firstName;
		this.lastName = lastName;
		this.logged = logged;
	}

	public User() {
		this.userId = 0;
		this.jobTitle = "";
		this.userRights = "";
		this.userRightsLevel = 0;
		this.userName = "";
		this.userPassword = "";
		this.userEmail = "";
		this.firstName = "";
		this.lastName = "";
		this.logged = false;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public  String getUserRights() {
		return userRights;
	}

	public void setUserRights(String userRights) {
		this.userRights = userRights;
	}	
	
	public Integer getUserRightsLevel() {
		return userRightsLevel;
	}

	public void setUserRightsLevel(Integer userRightsLevel) {
		this.userRightsLevel = userRightsLevel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Boolean getLogged() {
		return logged;
	}

	public void setLogged(Boolean logged) {
		this.logged = logged;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((logged == null) ? 0 : logged.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userPassword == null) ? 0 : userPassword.hashCode());
		result = prime * result + ((userRights == null) ? 0 : userRights.hashCode());
		result = prime * result + ((userRightsLevel == null) ? 0 : userRightsLevel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (jobTitle == null) {
			if (other.jobTitle != null)
				return false;
		} else if (!jobTitle.equals(other.jobTitle))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (logged == null) {
			if (other.logged != null)
				return false;
		} else if (!logged.equals(other.logged))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userPassword == null) {
			if (other.userPassword != null)
				return false;
		} else if (!userPassword.equals(other.userPassword))
			return false;
		if (userRights == null) {
			if (other.userRights != null)
				return false;
		} else if (!userRights.equals(other.userRights))
			return false;
		if (userRightsLevel == null) {
			if (other.userRightsLevel != null)
				return false;
		} else if (!userRightsLevel.equals(other.userRightsLevel))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", jobTitle=" + jobTitle + ", userRights=" + userRights + ", userRightsLevel="
				+ userRightsLevel + ", userName=" + userName + ", userPassword=" + userPassword + ", userEmail="
				+ userEmail + ", firstName=" + firstName + ", lastName=" + lastName + ", logged=" + logged + "]";
	}
}
