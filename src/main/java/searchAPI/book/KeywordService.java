package searchAPI.book;

import searchAPI.book.entity.Keyword;

public interface KeywordService {
    void save(Keyword keyword);

    Keyword findByKeyword(String keyword);
    
    void counting(String keyword);
    
    Keyword[] top10();
}