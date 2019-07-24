package searchAPI.book;

import searchAPI.book.entity.History;

public interface HistoryService {
    void save(History history);

    History findByKeyword(String keyword);
}