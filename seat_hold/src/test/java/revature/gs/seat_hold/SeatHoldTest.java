package revature.gs.seat_hold;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.gs.seat_hold.exception.CapacityReachedException;
import com.revature.gs.seat_hold.exception.ExpiredSeatHoldException;
import com.revature.gs.seat_hold.model.SeatHold;
import com.revature.gs.seat_hold.service.TicketService;
import com.revature.gs.seat_hold.service.TicketServiceImpl;


public class SeatHoldTest {

	@Before
	public void backgroud(){
		
	}
	@Test
	public void initializationTest(){
		TicketService ticketService = new TicketServiceImpl(5,10);
	}
	@Test
	public void configurationTest(){
		TicketService ticketService = new TicketServiceImpl(10,10);
		assertEquals(100, ticketService.numSeatsAvailable());
	}
	@Test
	public void holdTest(){
		TicketService ticketService = new TicketServiceImpl(10,10);
		ticketService.findAndHoldSeats(5, "lemon@lemon.com");
		assertEquals(95, ticketService.numSeatsAvailable());
	}
	@Test
	public void reservTest(){
		TicketService ticketService = new TicketServiceImpl(10,10);
		SeatHold seatHold = ticketService.findAndHoldSeats(5, "lemon@lemon.com");
		assertEquals(95, ticketService.numSeatsAvailable());
		ticketService.reserveSeats(seatHold.getSeatHoldId(), "lemon@lemon.com");
		assertEquals(95, ticketService.numSeatsAvailable());
		ticketService.findAndHoldSeats(8, "tofu@tofu.com");
		assertEquals(87, ticketService.numSeatsAvailable());
	}
	@Test(expected = IllegalArgumentException.class)
	public void badSeatHoldTest(){
		TicketService ticketService = new TicketServiceImpl(10,10);
		ticketService.reserveSeats(8, "huehuehuehue.com");
	}
	@Test(expected = IllegalArgumentException.class)
	public void negativeSeatTest(){
		TicketService ticketService = new TicketServiceImpl(10,10);
		ticketService.findAndHoldSeats(-4, "lemon@lemon.com");
	}
	@Test(expected = CapacityReachedException.class)
	public void emptyVenueTest(){
		TicketService ticketService = new TicketServiceImpl(0,0);
		ticketService.findAndHoldSeats(4, "lemon@lemon.com");
	}
	public void fullVenueTest(){
		TicketService ticketService = new TicketServiceImpl(5,5);
		ticketService.findAndHoldSeats(25, "tofu@tofu.com");
	}
	@Test(expected = CapacityReachedException.class)
	public void capacityTest(){
		TicketService ticketService = new TicketServiceImpl(5,5);
		ticketService.findAndHoldSeats(25, "tofu@tofu.com");
		ticketService.findAndHoldSeats(3, "eggplant@eggplant.com");
	}
	
	//ignored because takes too long to expire
	//unignore to test expiration
	@Test(expected = ExpiredSeatHoldException.class)
	@Ignore
	public void expirationTest() throws InterruptedException{
		
		String customerEmail = "parfait@parfait.com";
		
		TicketService ticketService = new TicketServiceImpl(5,5);
		SeatHold seatHold = ticketService.findAndHoldSeats(25, customerEmail);
		Thread.sleep(20000L);
		ticketService.reserveSeats(seatHold.getSeatHoldId(), customerEmail);
		
	}
	
}
