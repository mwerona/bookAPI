package searchAPI.book.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class History {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	
	@Column
	private String keyword;

	@Column
    private LocalDateTime searchtime;
    
	public History() {
	}
	
	public History(String keyword) {
		this.keyword = keyword;
		this.searchtime = LocalDateTime.now();
	}
	
	public History(String keyword, LocalDateTime searchtime) {
		this.keyword = keyword;
		this.searchtime = searchtime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public LocalDateTime getSearchtime() {
		return searchtime;
	}

	public void setSearchtime(LocalDateTime searchtime) {
		this.searchtime = searchtime;
	}
	
}
