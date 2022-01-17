package business.offer;

public class Walk extends Transport {
	
	public Walk() {}
	
	public Walk(int duration, int price, int distance) {
		super(duration, price, distance);
	}

	@Override
	public int comfort() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
