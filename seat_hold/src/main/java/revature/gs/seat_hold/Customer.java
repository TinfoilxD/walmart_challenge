package revature.gs.seat_hold;

import java.util.ArrayList;
import java.util.LinkedList;

public class Customer {
	
	private LinkedList<Seat> preferences;

	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LinkedList<Seat> getPreferences() {
		return preferences;
	}

	public void setPreferences(LinkedList<Seat> preferences) {
		this.preferences = preferences;
	}
	
	
}
