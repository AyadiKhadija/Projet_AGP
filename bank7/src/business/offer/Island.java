package business.offer;

public class Island {
	private String id_island;
	private String name_island;
	
	public Island() {	}
	
	public Island(String id_island, String name_island) {
		this.id_island = id_island;
		this.name_island = name_island;
	}
	
	public String getId_island() {
		return id_island;
	}

	public void setId_island(String id_island) {
		this.id_island = id_island;
	}

	public String getName_island() {
		return name_island;
	}

	public void setName_island(String name_island) {
		this.name_island = name_island;
	}
}
