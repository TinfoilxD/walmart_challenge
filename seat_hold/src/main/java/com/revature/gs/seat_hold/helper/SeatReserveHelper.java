package com.revature.gs.seat_hold.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.gs.seat_hold.exception.ExpiredSeatHoldException;
import com.revature.gs.seat_hold.model.Seat;
import com.revature.gs.seat_hold.model.SeatHold;

public class SeatReserveHelper {
	
	private Logger log = Logger.getLogger(SeatReserveHelper.class);

	
	private List<SeatHold> reservedSeats;
	
	public SeatReserveHelper(){
		reservedSeats = new ArrayList<SeatHold>();
	}
	
	public boolean hasSeat(Seat s){
		//iterates through all of the seatholds and all of the held seats in each
		for(SeatHold seathold: reservedSeats){
			Set<Seat> seats = seathold.getHeldSeats();
			for(Seat seat: seats){
				if(s.equals(seat))
					return true;
			}
		}
		return false;
	}

	public String reserveSeatHold(SeatHold reserveSeatHold) {
		
		if(reserveSeatHold.isExpired()){
			throw new ExpiredSeatHoldException("Seathold has expired");
		}
		
		reservedSeats.add(reserveSeatHold);
		
		
		log.debug("reserveSeatHold " + reservedSeats);
		//returns a string representation of the seathold hashcode
		//since seathold doesn't change once reserved
		return String.valueOf(reserveSeatHold.hashCode());
	}
	private SeatHold findById(int seatHoldId){
		for(SeatHold seatHold: reservedSeats){
			if(seatHold.getSeatHoldId() == seatHoldId){
				return seatHold;
			}
		}
		return null;
	}

}
