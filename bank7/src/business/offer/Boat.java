package business.offer;

public class Boat extends Transport {
	public Boat() {}
	
	public Boat(String id_Transport, String name_Transport, String id_island) {
		super(id_Transport, name_Transport, id_island);
	}

	@Override
	public int comfort() {
		// TODO Auto-generated method stub
		
		/*int duration = getDuration();
		if(duration > 120) {
			return 0;
		}
		else if(duration > 90) {
			return 2;
		}
		else if(duration > 60) {
			return 4;
		}
		else if(duration > 30) {
			return 6;
		}
		else if(duration > 15) {
			return 8;
		}
		else {
			return 10;
		}*/
		return 0;
	}
	
	
}
