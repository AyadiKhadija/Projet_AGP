package business.offer;

public class Site extends Place {
	
	private String description;
	private boolean isTouristic;
	
	
	public Site() { }
	
	public Site(String description, boolean isTouristic, String name, Coordinate location, Island island, float price) {
		super(name, location, island, price);
		this.description = description;
		this.isTouristic = isTouristic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isTouristic() {
		return isTouristic;
	}

	public void setTouristic(boolean isTouristic) {
		this.isTouristic = isTouristic;
	}
	
	
	

	
	
}
