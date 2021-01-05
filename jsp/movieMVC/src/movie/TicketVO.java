package movie;

import java.sql.Date;

public class TicketVO {
	private int ticketNo;
	private String id;
	private String reserveDay;
	private int roomNo;
	private String runDay;
	private String movieName;
	private int runTime;
	private int seatNo;
	private int schNo;
	
	public TicketVO(int ticketNo, String id, String reserveDay, int roomNo, String runDay, String movieName, int runTime, int seatNo, int schNo) {
		this.ticketNo = ticketNo;
		this.id = id;
		this.reserveDay = reserveDay;
		this.roomNo = roomNo;
		this.runDay = runDay;
		this.movieName = movieName;
		this.runTime = runTime;
		this.seatNo = seatNo;
		this.schNo = schNo;
	}

	public int getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(int ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReserveDay() {
		return reserveDay;
	}

	public void setReserveDay(String reserveDay) {
		this.reserveDay = reserveDay;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getRunDay() {
		return runDay;
	}

	public void setRunDay(String runDay) {
		this.runDay = runDay;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getRunTime() {
		return runTime;
	}

	public void setRunTime(int runTime) {
		this.runTime = runTime;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public int getSchNo() {
		return schNo;
	}

	public void setSchNo(int schNo) {
		this.schNo = schNo;
	}
}
