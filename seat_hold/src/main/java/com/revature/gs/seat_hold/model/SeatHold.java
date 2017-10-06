package com.revature.gs.seat_hold.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A model class meant to to resemble a seat reservation
 * has the seats reserved, the reservation id, and the expiration date
 *
 */
public class SeatHold {
	
	private static final long EXPIRATION_TIME = 15000L;
	
	//"id" generator
	private static int seatHoldSequence = 0;
	private int seatHoldId;
	
	//expiration time in epoch time
	private long expirationDate;
	
	private Set<Seat> heldSeats;
	
	private String customerEmail;
	
	public SeatHold(){
		seatHoldSequence++;
		seatHoldId = seatHoldSequence;
		heldSeats = new HashSet<Seat>();
		expirationDate = new Date().getTime() + EXPIRATION_TIME;
	}

	public boolean isExpired(){
		return expirationDate < new Date().getTime();
	}
	public int getSeatHoldId() {
		return seatHoldId;
	}

	public void setSeatHoldId(int seatHoldId) {
		this.seatHoldId = seatHoldId;
	}

	public long getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(long expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Set<Seat> getHeldSeats() {
		return heldSeats;
	}

	public void setHeldSeats(HashSet<Seat> heldSeats) {
		this.heldSeats = heldSeats;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public void addSeat(Seat seat){
		heldSeats.add(seat);
	}

	@Override
	public String toString() {
		return "SeatHold [seatHoldId=" + seatHoldId + ", expirationDate=" + expirationDate + ", heldSeats=" + heldSeats
				+ ", customerEmail=" + customerEmail + "]";
	}
	
	
}
