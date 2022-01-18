package business.offer;

//Class Bus fonctionnelle

public class Bus extends Transport {
	public Bus() {}
	
	public Bus(int duration, float price, int distance) {
		super(duration, price, distance);
	}

	@Override
	public int comfort() {
		// TODO Auto-generated method stub
		
		int duration = getDuration();
		if(duration > 240) {
			return 0;
		}
		else if(duration > 180) {
			return 2;
		}
		else if(duration > 120) {
			return 4;
		}
		else if(duration > 90) {
			return 6;
		}
		else if(duration > 60) {
			return 8;
		}
		else {
			return 10;
		}
	}
}
