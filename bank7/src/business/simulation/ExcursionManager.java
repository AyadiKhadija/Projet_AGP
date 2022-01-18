package business.simulation;

import business.offer.*;


public class ExcursionManager {
	
	public int getDistance(Place departure, Place arrival) {
		int absDeparture = departure.getLocation().getX();
		int ordDeparture = departure.getLocation().getY();
		int absArrival = arrival.getLocation().getX();
		int ordArrival = arrival.getLocation().getY();
		
		double geoDistance = Math.sqrt(Math.pow(absDeparture - absArrival, 2.0) + Math.pow(ordDeparture - ordArrival,  2.0));
		
		int realDistance = (int)(geoDistance*380)/2010;
		
		return realDistance;
	}
	
	public Transport getTransport(Place departure, Place arrival) {
		int distance = getDistance(departure, arrival);
		int duration = 0;
		float price = 0;
		
		if(!departure.getIsland().getName().equals(arrival.getIsland().getName())) {
			Boat boat = new Boat();
			duration = getDuration(boat, distance);
			price = getPriceTransport(boat, distance);
			return new Boat(duration, price, distance);
		}
		else {
			if(distance > 2) {
				Bus bus = new Bus();
				duration = getDuration(bus, distance);
				price = getPriceTransport(bus, distance);
				return new Bus(duration, price, distance);
			}
			else {
				Walk walk = new Walk();
				duration = getDuration(walk, distance);
				price = getPriceTransport(walk, distance);
				return new Walk(duration, price, distance);
			}
		}
	}
	
	public float getPriceTransport(Transport transport, int distance) {
		if(transport instanceof Boat) {
			return distance * 0.8f;
		}
		else if(transport instanceof Bus) {
			return distance * 0.5f;
		}
		else {
			return 0f;
		}
	}
	
	public float getPriceJourney(Place departure, Place arrival, Transport transport, int distance) {
		float price = getPriceTransport(transport, distance);
		
		if(arrival instanceof Site) {
			price += arrival.getPrice();
		}
		
		return price;
	}
	
	public int getDuration(Transport transport, int distance) {
		if(transport instanceof Boat) {
			return (distance*70)/100;
		}
		else if(transport instanceof Bus) {
			return (distance*50)/100;
		}
		else {
			return (distance*20)/2;
		}
	}
	
	public Journey createJourney(Place departure, Place arrival) {
		Transport transport = getTransport(departure, arrival);
		int distance = getDistance(departure, arrival);
		float price = getPriceJourney(departure, arrival, transport, distance);
		int duration = getDuration(transport, distance);
		return new Journey(departure, arrival, distance, transport, price, duration);
	}
}
