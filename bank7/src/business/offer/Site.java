package business.offer;

public class Site extends Place {
	
	private boolean isTouristic;
	private int id_site;
	
	
	public Site() { }
	
	public Site(int id_site,boolean isTouristic, String name, Coordinate location, Island island, float price) {
		super(name, location, island, price);
		this.isTouristic = isTouristic;
		this.id_site= id_site;
	}

	

	

	public int getId_site() {
		return id_site;
	}

	public void setId_site(int id_site) {
		this.id_site = id_site;
	}

	public boolean isTouristic() {
		return isTouristic;
	}

	public void setTouristic(boolean isTouristic) {
		this.isTouristic = isTouristic;
	}

	
	
	

	
	
}
