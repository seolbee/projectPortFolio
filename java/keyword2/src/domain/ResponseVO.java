package domain;

import java.util.List;

public class ResponseVO {
	private String lastBuildDate;
	private long total;
	private int start;
	private int display;
	
	private List<ItemVO> items;

	public String getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBulidDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public List<ItemVO> getItems() {
		return items;
	}

	public void setItems(List<ItemVO> items) {
		this.items = items;
	}
	
	
}
