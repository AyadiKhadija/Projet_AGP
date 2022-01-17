package business.offer;

public abstract class Place {
	String name;
	Coordinate location;
	Island island;
	float price;
	
	public Place() { } 
	
	public Place(String name, Coordinate location, Island island, float price) {
		this.name = name;
		this.location = location;
		this.island = island;
		this.price = price;
	}
	
	
}
