package com.revature.gs.seat_hold.service;

import com.revature.gs.seat_hold.model.SeatHold;

public interface TicketService {
	int numSeatsAvailable();
	SeatHold findAndHoldSeats(int numSeats, String customerEmail);
	String reserveSeats(int seatHoldId, String customerEmail);
}
