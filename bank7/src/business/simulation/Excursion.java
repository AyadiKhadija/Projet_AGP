package business.simulation;

import java.util.ArrayList;
import business.offer.*;

public class Excursion {
	private String date;
	private ArrayList<Journey> journeys = new ArrayList<Journey>();
	
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

	public ArrayList<Journey> getJourneys() {
		return journeys;
	}

	public void setJourneys(ArrayList<Journey> journeys) {
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
