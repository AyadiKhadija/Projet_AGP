package business.offer;

public abstract class Transport {
	private String id_Transport;
	private String name_Transport; 
	private String id_island;
	
	public Transport() {}
	
	public Transport(String id_Transport, String name_Transport, String id_island) {
		this.id_Transport = id_Transport;
		this.name_Transport = name_Transport;
		this.id_island = id_island;
	}
	
	public String getId_Transport() {
		return id_Transport;
	}

	public void setId_Transport(String id_Transport) {
		this.id_Transport = id_Transport;
	}

	public String getName_Transport() {
		return name_Transport;
	}

	public void setName_Transport(String name_Transport) {
		this.name_Transport = name_Transport;
	}

	public String getId_island() {
		return id_island;
	}

	public void setId_island(String id_island) {
		this.id_island = id_island;
	}

	public abstract int comfort();
}
