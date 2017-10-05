package revature.gs.seat_hold;

import java.util.ArrayList;
import java.util.LinkedList;

public class CustomerService {
	private ArrayList<Customer> customers;
	
	public CustomerService(){
		customers = new ArrayList<Customer>();
	}
	
	public void addCustomer(Customer c){
		customers.add(c);
	}
	public Customer getCustomer(String email){
		for(Customer customer : customers){
			if(customer.getEmail().equals(email)){
				return customer;
			}
		}
		return null;
	}
}
