package revature.gs.seat_hold;

public class Venue {
	
	Seat[][] seats;

	public Seat[][] getSeats() {
		return seats;
	}

	public void setSeats(Seat[][] seats) {
		this.seats = seats;
	}
	
	/*
	 * Takes an input of 
	 * ----- [[ STAGE ]] ----
	 * ----------------------
	 * ssssssssssssssssssssss
	 * ssssssssssssssssssssss
	 * ssssssssssssssssssssss
	 * 
	 * with s being a seat
	 * 
	 * Ignores all other values except s.
	 * Starts adding seats to the venue from the first row.
	 * First row is closest to the stage.
	 * Begins from stage-right.
	 */
	public void setSeats(String[] seatsString){
		
		
		int seatRows = seatsString.length;
		int seatColumns = seatsString[0].length();
		
		//first param is number of rows; second param is columns
		seats = new Seat[seatRows][seatColumns];
		
		for(String s: seatsString){
			if(s.length() > seatRows){
				throw new IllegalArgumentException("Rows are larger than expected");
			}
			
		}
		
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	public void setSize(int rows, int columns){
		int seatNumber = 0;
		seats = new Seat[rows][columns];
		for(int i = 0; i < seats.length; i++){
			for(int j = 0; j < seats[0].length; j++){
				//seats start numbering from 1
				seats[i][j] = new Seat(++seatNumber);
			}
		}
	}
	public void setDefault(){
		/*
		 * The venue specifications given
		 * in documentation
		 */
		
		int seatNumber = 0;	
		seats = new Seat[9][33];
		for(int i = 0; i < seats.length; i++){
			for(int j = 0; j < seats[0].length; j++){
				//seats start numbering from 1
				seats[i][j] = new Seat(++seatNumber);
			}
		}
	}
	
	
}
