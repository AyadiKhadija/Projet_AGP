package business.simulation;

import java.util.ArrayList;
import java.util.HashMap;

import business.offer.*;

public class Excursion {
	private int date;
	private HashMap<Integer, ArrayList<Journey>> journeys = new HashMap<Integer, ArrayList<Journey>>();
	
	public Excursion() {} 
	
	public Excursion(int date, HashMap<Integer, ArrayList<Journey>> journeys) {
		this.date = date;
		this.journeys = journeys;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}
	
	public HashMap<Integer, ArrayList<Journey>> getJourneys() {
		return journeys;
	}

	public void setJourneys(HashMap<Integer, ArrayList<Journey>> journeys) {
		this.journeys = journeys;
	}

	public float getPrice() {
		float total = 0;
		
		for(int i = 0; i<journeys.size(); i++) {
			for(int j = 0; j<journeys.get(i).size(); j++) {
				total = total + journeys.get(i).get(j).getPrice();
			}
		}
		
		return total;
	}
	
}