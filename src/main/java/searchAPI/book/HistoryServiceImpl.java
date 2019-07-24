package searchAPI.book;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import searchAPI.book.entity.History;

@Service
public class HistoryServiceImpl implements HistoryService {
	
    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public void save(History history) {
    	history.setSearchtime(LocalDateTime.now());
        historyRepository.save(history);
    }

    @Override
    public History findByKeyword(String keyword) {
        return historyRepository.findByKeyword(keyword);
    }

    
}