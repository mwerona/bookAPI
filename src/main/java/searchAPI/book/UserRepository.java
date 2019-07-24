package searchAPI.book;

import org.springframework.data.jpa.repository.JpaRepository;
import searchAPI.book.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	User findByUserId(String userId);
}