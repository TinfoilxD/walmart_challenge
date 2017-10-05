package revature.gs.seat_hold;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class SeatReserveService {
	
	private Logger log = Logger.getLogger(SeatReserveService.class);

	
	private List<SeatHold> reservedSeats;
	
	public SeatReserveService(){
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
