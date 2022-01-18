package business.offer;

public class Hotel extends Place {

	private int stars;
	
	public Hotel() {}
	
	public Hotel(int stars, String name, Coordinate location, Island island, float price) {
		super(name, location, island, price);
		this.stars = stars;
	}
	
	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}
	
	public int comfort() {
		switch(stars) {
			case 5:
				return 10;
			case 4:
				return 8;
			case 3:
				return 6;
			case 2:
				return 4;
			case 1:
				return 2;
			default:
				return 2;
		}
	}

}
