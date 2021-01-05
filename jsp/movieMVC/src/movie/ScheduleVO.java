package movie;

import java.sql.Date;

public class ScheduleVO {
	private int no;
	private int movieno;
	private String runDay;
	private int runtime;
	private int seatCnt;
	private int roomNo;
	
	public ScheduleVO(int no, int movieno, String runDay, int runtime, int roomno, int seatCnt) {
		this.no = no;
		this.movieno = movieno;
		this.runDay = runDay;
		this.runtime = runtime;
		this.roomNo = roomno;
		this.seatCnt = seatCnt;
	}

	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public int getMovieno() {
		return movieno;
	}
	
	public void setMovieno(int movieno) {
		this.movieno = movieno;
	}
	
	public String getRunDay() {
		return runDay;
	}
	
	public void setRunDay(String runDay) {
		this.runDay = runDay;
	}
	
	public int getRuntime() {
		return runtime;
	}
	
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	
	public int getSeatCnt() {
		return seatCnt;
	}
	
	public void setSeatCnt(int seatCnt) {
		this.seatCnt = seatCnt;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
}
