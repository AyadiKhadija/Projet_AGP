package business.offer;

public class Boat extends Transport {
	public Boat() {}
	
	public Boat(int duration, int price, int distance) {
		super(duration, price, distance);
	}

	@Override
	public int comfort() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
