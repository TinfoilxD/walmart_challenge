package revature.gs.seat_hold;

import java.util.LinkedList;

import org.apache.log4j.Logger;

public class TicketServiceImpl implements TicketService {

	
	private Logger log = Logger.getLogger(TicketServiceImpl.class);
	// data structure for holding the seats
	private Venue venue;

	// service that deals with holding seats
	private SeatHoldService holdService;

	// service that deals with reserving seats
	private SeatReserveService reserveService;

	// service that deals with customer and customer preferences
	private CustomerService customerService;

	/**
	 * Sets the seat configurationt to 9x33 which was the venue in documentation
	 * 
	 * @see revature.gs.seat_hold.Venue#setDefault()
	 */
	public TicketServiceImpl() {
		venue = new Venue();
		venue.setSize(9, 33);
		holdService = new SeatHoldService();
		reserveService = new SeatReserveService();
		customerService = new CustomerService();

	}
	
	/**
	 * Sets the venue size wth rows and columns
	 * 
	 * @see revature.gs.seat_hold.Venue#setSize()
	 * @param row
	 * @param column
	 */
	public TicketServiceImpl(int row, int column){
		venue = new Venue();
		venue.setSize(row, column);
		holdService = new SeatHoldService();
		reserveService = new SeatReserveService();
		customerService = new CustomerService();
	}

	/**
	 * Creates the menu based on the strings given in the input
	 * 
	 * @see revature.gs.seat_hold.Venue#setSeats(String[])
	 */
	public TicketServiceImpl(String[] seatsString) {
		venue = new Venue();
		venue.setSeats(seatsString);
		holdService = new SeatHoldService();
		reserveService = new SeatReserveService();
		customerService = new CustomerService();

	}

	/**
	 * returns the number of seats available by checking whether or not a seat
	 * is in holdService or reserveService
	 * 
	 */
	public int numSeatsAvailable() {
		int availableSeats = 0;
		Seat[][] seats = venue.getSeats();
		for (Seat[] row : seats) {
			for (Seat s : row) {
				if (!holdService.hasSeat(s) && !reserveService.hasSeat(s)) {
					availableSeats++;
				}
			}
		}
		return availableSeats;
	}

	/**
	 * returns a seathold object which contains the seats held seats are matched
	 * by customer preferences and then by seats closest to the stage
	 * 
	 */
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		if(numSeats < 0){
			throw new IllegalArgumentException("Negative number of seats");
		}
		Customer customer = customerService.getCustomer(customerEmail);
		SeatHold seatHold = new SeatHold();
		seatHold.setCustomerEmail(customerEmail);
		LinkedList<Seat> preferences;
		if (customer != null) {
			preferences = customer.getPreferences();

			/*
			 * /add to seathold all of the seats that belong to customer
			 * preferences
			 */

			// loops if seathold doesn't have enough seats or preferences is
			// empty
			while (seatHold.getHeldSeats().size() < numSeats && !preferences.isEmpty()) {
				Seat seat = preferences.pop();
				if (!!holdService.hasSeat(seat) || !reserveService.hasSeat(seat)) {
					seatHold.addSeat(seat);
				}
			}
		}

		/*
		 * if there are no preferences left, get the next seats starting from
		 * closer to the stage
		 */
		int currentHeldNumber = seatHold.getHeldSeats().size();
		if (currentHeldNumber < numSeats) {
			holdService.holdNextAvailableSeats(numSeats - currentHeldNumber,
					venue, seatHold, reserveService);
		}

		holdService.addSeatHold(seatHold);
		return seatHold;

	}

	
	/**
	 * takes in a seatholdid which was previously given from findAndHoldSeats
	 * Removes seathold from SeatHoldService and puts it into SeatReserveService
	 * throws IllegalArgument if seathold does not exist in SeatHoldService
	 */
	public String reserveSeats(int seatHoldId, String customerEmail) {
		SeatHold toReserve = holdService.remove(seatHoldId);
		if(toReserve == null){
			throw new IllegalArgumentException("Seathold does not exist.");
		}
		return reserveService.reserveSeatHold(toReserve);
	}

}
