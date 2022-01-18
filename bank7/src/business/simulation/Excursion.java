package business.simulation;

import java.util.ArrayList;
import business.offer.*;

public class Excursion {
	private String date;
	private HashMap<Integer, ArrayList<Journey>> journeys = new HashMap<Integer, ArrayList<Journey>>();
	
	public Excursion() {} 
	
	public Excursion(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	public HashMap<Integer, ArrayList<Journey>> getJourneys() {
		return journeys;
	}
	
	public void setJourneys(HashMap<Integer, ArrayList<Journey>>) {
		this.journeys = journeys;
	}
	
	public float getPrice() {
		float total = 0;
		
		for(int i = 0; i<journeys.size(); i++) {
			total = total + journeys.get(i).getPrice();
		}
		
		return total;
	}
	
}
