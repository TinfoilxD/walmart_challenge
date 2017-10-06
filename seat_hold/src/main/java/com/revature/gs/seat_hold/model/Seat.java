package com.revature.gs.seat_hold.model;

public class Seat {
	int seatNumber;

	public Seat() {
		super();
	}

	public Seat(int seatNumber) {
		super();
		this.seatNumber = seatNumber;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + seatNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		if (seatNumber != other.seatNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Seat [seatNumber=" + seatNumber + "]";
	}
	
	
	

}
