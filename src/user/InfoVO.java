package user;

public class InfoVO {

	private int userId;
	private String userName;
	private String userGender;
	private String userSpecies;
	private int userAge;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserSpecies() {
		return userSpecies;
	}
	public void setUserSpecies(String userSpecies) {
		this.userSpecies = userSpecies;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	
	@Override
	public String toString() {
		return "infoVO [userId=" + userId + ", userName=" + userName + ", userGender=" + userGender + ", userSpecies="
				+ userSpecies + ", userAge=" + userAge + "]";
	}
	
	
}
