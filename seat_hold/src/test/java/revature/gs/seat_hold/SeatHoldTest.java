package revature.gs.seat_hold;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SeatHoldTest {

	@Before
	public void backgroud(){
		
	}
	@Test
	public void test1(){
		TicketService ticketService = new TicketServiceImpl(5,10);
	}
	@Test
	public void test2(){
		TicketService ticketService = new TicketServiceImpl(10,10);
		assertEquals(100, ticketService.numSeatsAvailable());
	}
	@Test
	public void test3(){
		TicketService ticketService = new TicketServiceImpl(10,10);
		ticketService.findAndHoldSeats(5, "lemon@lemon.com");
		assertEquals(95, ticketService.numSeatsAvailable());
	}
	@Test
	public void test4(){
		TicketService ticketService = new TicketServiceImpl(10,10);
		SeatHold seatHold = ticketService.findAndHoldSeats(5, "lemon@lemon.com");
		assertEquals(95, ticketService.numSeatsAvailable());
		ticketService.reserveSeats(seatHold.getSeatHoldId(), "lemon@lemon.com");
		assertEquals(95, ticketService.numSeatsAvailable());
		ticketService.findAndHoldSeats(8, "tofu@tofu.com");
		assertEquals(87, ticketService.numSeatsAvailable());
	}
	@Test(expected = IllegalArgumentException.class)
	public void test5(){
		TicketService ticketService = new TicketServiceImpl(10,10);
		ticketService.reserveSeats(8, "huehuehuehue.com");
	}
	@Test(expected = IllegalArgumentException.class)
	public void test6(){
		TicketService ticketService = new TicketServiceImpl(10,10);
		ticketService.findAndHoldSeats(-4, "lemon@lemon.com");
	}
	@Test(expected = IllegalArgumentException.class)
	public void test7(){
		TicketService ticketService = new TicketServiceImpl(0,0);
		ticketService.findAndHoldSeats(4, "lemon@lemon.com");
	}
	
}
