package business.offer;

public class Site extends Place {
	
	private String description;
	private boolean isTouristic;
	
	
	public Site() { }
	
	public Site(String description, boolean isTouristic, String name, Coordinate location, Island island, float price) {
		this.description = description;
		this.isTouristic = isTouristic;
		super(name, location, island, price);
	}
	
	
}
