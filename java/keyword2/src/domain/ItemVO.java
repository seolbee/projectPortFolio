package domain;

//VO => Value Object의 약자. 데이터를 저장하는 클래스

public class ItemVO {
	private String title;
	private String originallink;
	private String link;
	private String description;
	private String pubDate;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getOriginallink() {
		return originallink;
	}
	
	public void setOriginallink(String originallink) {
		this.originallink = originallink;
	}
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getPubDate() {
		return pubDate;
	}
	
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
}
