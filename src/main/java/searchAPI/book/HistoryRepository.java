package searchAPI.book;

import org.springframework.data.jpa.repository.JpaRepository;
import searchAPI.book.entity.History;

public interface HistoryRepository extends JpaRepository<History, String>{
	History findByKeyword(String keyword);
}