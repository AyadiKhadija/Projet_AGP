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
	
	@Override
	public int comfort() {
		// TODO Auto-generated method stub
		int stars = getStars();
		switch(stars) {
			case 1: 
	           return 2;
	   
	       case 2:
	           return 4;
	   
	       case 3:
	           return 6;
	           
	       case 4:
	           return 8;
	           
	       case 5:
	           return 10;
	           
	       default:
	    	   return 2;
		}
	}

}
