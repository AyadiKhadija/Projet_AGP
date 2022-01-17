package business.offer;

import java.util.ArrayList;
import business.simulation.*;

public class Offer {
	private float price;
	ArrayList<Excursion> excursions = new ArrayList<Excursion>();
	
	public Offer(float price) {
		this.price = price;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public ArrayList<Excursion> getExcursions() {
		return excursions;
	}

	public void setExcursions(ArrayList<Excursion> excursions) {
		this.excursions = excursions;
	}
	
	
}
