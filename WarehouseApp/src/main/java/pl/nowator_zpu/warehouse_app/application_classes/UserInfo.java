package pl.nowator_zpu.warehouse_app.application_classes;

public class UserInfo {
	
	private String userName;
	private String jobTitle;
	private String userRights;
	private Boolean logged;
		
	public UserInfo(String userName, String jobTitle, String userRights, Boolean logged) {
		super();
		this.userName = userName;
		this.jobTitle = jobTitle;
		this.userRights = userRights;
		this.logged = logged;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getUserRights() {
		return userRights;
	}
	public void setUserRights(String userRights) {
		this.userRights = userRights;
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
		result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
		result = prime * result + ((logged == null) ? 0 : logged.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userRights == null) ? 0 : userRights.hashCode());
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
		UserInfo other = (UserInfo) obj;
		if (jobTitle == null) {
			if (other.jobTitle != null)
				return false;
		} else if (!jobTitle.equals(other.jobTitle))
			return false;
		if (logged == null) {
			if (other.logged != null)
				return false;
		} else if (!logged.equals(other.logged))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userRights == null) {
			if (other.userRights != null)
				return false;
		} else if (!userRights.equals(other.userRights))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "UserInfo [userName=" + userName + ", jobTitle=" + jobTitle + ", userRights=" + userRights + ", logged="
				+ logged + "]";
	}
	
}
