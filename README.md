
# TicketService Implementation

## Assumptions

* A Venue has a default size of 9x33 as specificed in given documentation
* Venue size can be configured using the implementation instructor
* This implementation of the implementation is a black-box implementation
* The interface is non-negotiable.
* The best seats are according to customer preference.
* If customer does not exist in a data structure reachable by TicketService. The closest seats are best.


## Architecture

This implementation uses object representations of seats fitted in a Venue. Seatholds are managed by SeatHoldHelper. Reserves are managed by SeatReserveHelper. TicketService delegates functions to those helpers.

## Instructions


Get Source
```
git clone https://github.com/TinfoilxD/walmart_challenge.git
```
Navigate to Project
```
cd seat_hold
```
Install and Package
```
mvn clean package
```

