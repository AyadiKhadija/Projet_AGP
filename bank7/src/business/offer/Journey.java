package business.offer;

public class Journey {
	private Place departure;
	private Place arrival;
	private int distance;
	private Transport transport;
	private float price;
	private int duration;
	private int comfort;
	
	public Journey() {}
	
	public Journey(Place departure, Place arrival, int distance, Transport transport, float price, int duration, int comfort) {
		this.departure = departure;
		this.arrival = arrival;
		this.distance = distance;
		this.transport = transport;
		this.price = price;
		this.duration = duration;
		this.comfort = comfort;
	}

	public Place getDeparture() {
		return departure;
	}

	public void setDeparture(Place departure) {
		this.departure = departure;
	}

	public Place getArrival() {
		return arrival;
	}

	public void setArrival(Place arrival) {
		this.arrival = arrival;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getComfort() {
		return comfort;
	}

	public void setComfort(int comfort) {
		this.comfort = comfort;
	}
	
	
}
