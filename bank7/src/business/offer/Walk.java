package business.offer;

public class Walk extends Transport {
	
	public Walk() {}
	
	public Walk(int duration, float price, int distance) {
		super(duration, price, distance);
	}

	@Override
	public int comfort() {
		// TODO Auto-generated method stub
		
		int duration = getDuration();
		if(duration > 40) {
			return 0;
		}
		else if(duration > 30) {
			return 2;
		}
		else if(duration > 20) {
			return 4;
		}
		else if(duration > 15) {
			return 6;
		}
		else if(duration > 10) {
			return 8;
		}
		else {
			return 10;
		}
	}
	
	
}
