package business.simulation;

import java.util.ArrayList;
import java.util.HashMap;

import business.offer.*;

public class OfferManager {
	
	private ArrayList<Offer> offers = new ArrayList<Offer>();
	
	public HashMap<Offer, Float> getTotalPrice() {
		HashMap<Offer, Float> prices = new HashMap<Offer, Float>();
	
		float total;

		for(int i = 0; i<offers.size(); i++) {
			total = 0;
			for(int j = 0; i<offers.get(i).getExcursions().size(); j++) {
				total = offers.get(i).getExcursions().get(j).getPrice();
			}
			prices.put(offers.get(i), total);
		}
		return prices;
	}
	
	/*public HashMap<Offer, Integer> getComfort() {
		HashMap<Offer, Float> prices = new HashMap<Offer, Float>();
	
		float total;

		for(int i = 0; i<offers.size(); i++) {
			total = 0;
			for(int j = 0; i<offers.get(i).getExcursions().size(); j++) {
				total = offers.get(i).getExcursions().get(j).gsetPrice();
			}
			prices.put(offers.get(i), total);
		}
		return prices;
	}*/
	
	
}
