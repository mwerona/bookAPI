package searchAPI.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import searchAPI.book.entity.Keyword;

public interface KeywordRepository extends JpaRepository<Keyword, String>{
	Keyword findByKeyword(String keyword);

	@Query(value = "SELECT * FROM KEYWORD ORDER BY COUNT DESC LIMIT 10", nativeQuery = true)
	Keyword[] getTop10();
}