package business.offer;

public abstract class Transport {
	private int duration;
	private float price;
	private int distance;
	
	public Transport() {}
	
	public Transport(int duration, float price, int distance) {
		this.duration = duration;
		this.price = price;
		this.distance = distance;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public abstract int comfort();
}
