package movie;

public class MovieVO {
	private int no;
	private String name;
	private String category;
	private int runtime;
	private String img;
	private String info;
	
	public MovieVO(int no, String name, String category, int runtime, String img, String info) {
		this.no = no;
		this.name = name;
		this.category = category;
		this.runtime = runtime;
		this.img = img;
		this.info = info;
	}

	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getRuntime() {
		return runtime;
	}
	
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg_url(String img) {
		this.img = img;
	}
	
	public String getInfo() {
		return info;
	}
	
	public void setInfo(String info) {
		this.info = info;
	}
	
}