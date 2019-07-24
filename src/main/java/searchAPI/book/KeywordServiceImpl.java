package searchAPI.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import searchAPI.book.entity.Keyword;

@Service
public class KeywordServiceImpl implements KeywordService {
	
    @Autowired
    private KeywordRepository keywordRepository;

    @Override
    public void save(Keyword keyword) {
        keyword.setCount(1);
        keywordRepository.save(keyword);
    }

    @Override
    public Keyword findByKeyword(String keyword) {
        return keywordRepository.findByKeyword(keyword);
    }

	@Override
	public void counting(String keyword) {
		Keyword target = findByKeyword(keyword);
		if(target == null) {
			save(new Keyword(keyword));
		}else {
			target.setCount(target.getCount() + 1);
			keywordRepository.saveAndFlush(target);
		}
	}

	@Override
	public Keyword[] top10() {
		return keywordRepository.getTop10();
	}
    
}