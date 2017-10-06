package com.revature.gs.seat_hold.exception;

public class CapacityReachedException extends RuntimeException{
	public CapacityReachedException(String s){
		super(s);
	}
}
