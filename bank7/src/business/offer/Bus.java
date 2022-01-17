package business.offer;

public class Bus extends Transport {
	public Bus() {}
	
	public Bus(int duration, int price, int distance) {
		super(duration, price, distance);
	}

	@Override
	public int comfort() {
		// TODO Auto-generated method stub
		return 0;
	}
}
