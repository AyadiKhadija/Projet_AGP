package business.offer;

public abstract class Place {
	private String name;
	private Coordinate location;
	private Island island;
	private float price;
	
	public Place() { } 
	
	public Place(String name, Coordinate location, Island island, float price) {
		this.name = name;
		this.location = location;
		this.island = island;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public Coordinate getLocation() {
		return location;
	}
	
	public Island getIsland() {
		return island;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLocation(Coordinate location) {
		this.location = location;
	}
	
	public void setIsland(Island island) {
		this.island = island;
	}
	
	public void setPrice(float Price) {
		this.price = price;
	}
	
	 public abstract int comfort();
}
