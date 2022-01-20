package business.offer;

public class Hotel extends Place {

	private int stars;
	private String id_hotel;
	
	public Hotel() {}
	
	public Hotel(String id_hotel, String name_hotel, float price_day, Coordinate location, int stars, Island island) {
		super(name_hotel, location, island, price_day);
		this.stars = stars;
		this.id_hotel = id_hotel;
	}
	
	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}
	
	public String getId_hotel() {
		return id_hotel;
	}

	public void setId_hotel(String id_hotel) {
		this.id_hotel = id_hotel;
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
