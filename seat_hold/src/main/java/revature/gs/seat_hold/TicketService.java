package revature.gs.seat_hold;

public interface TicketService {
	int numSeatsAvailable();
	SeatHold findAndHoldSeats(int numSeats, String customerEmail);
	String reserveSeats(int seatHoldId, String customerEmail);
}
