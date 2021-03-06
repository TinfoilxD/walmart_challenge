package com.revature.gs.seat_hold.helper;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.gs.seat_hold.exception.CapacityReachedException;
import com.revature.gs.seat_hold.model.Seat;
import com.revature.gs.seat_hold.model.SeatHold;
import com.revature.gs.seat_hold.model.Venue;


public class SeatHoldHelper {
	
	private Logger log = Logger.getLogger(SeatHoldHelper.class);

	
	private Set<SeatHold> seatHolds;
	
	public SeatHoldHelper(){
		seatHolds = new HashSet<SeatHold>();
	}
	public boolean hasSeat(Seat s){
		
		//iterates through all of the seatholds and all of the held seats in each
		for(SeatHold seathold: seatHolds){
			Set<Seat> seats = seathold.getHeldSeats();
			for(Seat seat: seats){
				if(s.equals(seat) && !seathold.isExpired())
					return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param i
	 * @param venue
	 * @param seatHold
	 * @param reserveService
	 * @return a mutated Seathold with the added seats still required to fill the numSeats quota
	 */
	public SeatHold holdNextAvailableSeats(int i, Venue venue, SeatHold seatHold, SeatReserveHelper reserveService) {
		Seat[][] seats = venue.getSeats();
		
		LinkedList<Seat> seatsLeft = new LinkedList<Seat>();
		for(int j = 0; j < seats.length; j++){
			Seat[] seatRow = seats[j];
			for(Seat seat : seatRow){
				seatsLeft.add(seat);
			}
		}
		
		/*
		 * Goes through all of the seats that are currently held and remove them from
		 * the available seats
		 */

		Iterator<Seat> iter = seatsLeft.iterator();
		while(iter.hasNext()){
			Seat seat = iter.next();
			if(this.hasSeat(seat) || reserveService.hasSeat(seat)){
				iter.remove();
			}
		}
		
		/*
		 * Go through the seats after all held seats are taken out.
		 * Fill up the seats left.
		 */
		Set<Seat> seatHoldSeats = seatHold.getHeldSeats();
		while(seatHold.getHeldSeats().size() < i){
			if(seatsLeft.isEmpty()){
				throw new CapacityReachedException("No more seats left");
			}
			seatHoldSeats.add(seatsLeft.pop());
		}
		log.debug("available seats: " + seatsLeft.size());
		
		/*
		 * Return seatHold which now has the required seats
		 */
		log.debug("holdNextAvailableSeats " + seatHold);
		return seatHold;
	}
	public void addSeatHold(SeatHold seatHold) {
		seatHolds.add(seatHold);
		
	}
	public SeatHold remove(int seatHoldId, String customerEmail) {
		SeatHold toRemove = findById(seatHoldId);
		if(toRemove == null){
			throw new IllegalArgumentException("Seathold does not exist");
		}
		if(!toRemove.getCustomerEmail().equals(customerEmail)){
			throw new SecurityException("Email does not match");
		}
		seatHolds.remove(toRemove);
		log.debug(seatHolds);
		return toRemove;
		
	}
	private SeatHold findById(int seatHoldId){
		for(SeatHold seatHold: seatHolds){
			if(seatHold.getSeatHoldId() == seatHoldId){
				return seatHold;
			}
		}
		return null;
	}

}
