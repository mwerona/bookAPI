package searchAPI.book;

import searchAPI.book.entity.User;

public interface UserService {
    void save(User user);

	User findByUserId(String userId);
	
	boolean matchPassword(String inputPassword, String userPassword);
}