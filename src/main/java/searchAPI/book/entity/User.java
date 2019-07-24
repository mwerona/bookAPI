package searchAPI.book.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
	private String userId;

    private String password;

    private History[] histories;

    public User() {
    	
    }
    
    public User(String userId, String password) {
    	this.userId = userId;
    	this.password = password;
    }
    
    public User(String userId, String password, History[] histories) {
    	this.userId = userId;
    	this.password = password;
    	this.histories = histories;
    }
    
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public History[] getHistories() {
		return histories;
	}

	public void setHistories(History[] histories) {
		this.histories = histories;
	}
}