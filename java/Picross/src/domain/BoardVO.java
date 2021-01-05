package domain;

public class BoardVO {
	
	private String location;
	
	private int id;
	
	private String name;
	
	private int length;
	
	private boolean empty;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public void setEmpty(boolean emtpy) {
		this.empty = emtpy;
	}
	
	public boolean isEmpty() {
		return this.empty;
	}
}
