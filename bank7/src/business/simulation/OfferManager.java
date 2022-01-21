package business.simulation;

import java.util.ArrayList;
import java.util.HashMap;

import business.offer.*;

public class OfferManager {
	
	private ArrayList<Offer> offers = new ArrayList<Offer>();
	

	
	public Offer createOffer(EntryOffer entry) {	
		
		ArrayList<Excursion> listExcursion = new ArrayList<Excursion>();
		
		ExcursionManager em = new ExcursionManager();
		ArrayList<Place> hotels = em.sortHotelbyPrice(entry);
		int randomHotel = (int) (Math.random() * hotels.size());
		Place departure = hotels.get(randomHotel);
		int nbExcursion;
		if(entry.getIsIntensity() == true) nbExcursion = 5;
		else nbExcursion=2;
		
		int counter = 0;
		
		for(int i = 0; i<5; i++) {
			if(counter<nbExcursion) {
				
				int random = 1 + (int)(Math.random() * 2);
				if(counter == 4 && nbExcursion == 5) random = 1;
				if(counter == 1 && nbExcursion == 2) random = 1;
				
				if(i==0) {
					listExcursion.add(em.createExcursion((i+1), entry, random, departure)); 
				}
				else {		
					Excursion e1= listExcursion.get(listExcursion.size()-1);
					HashMap<Integer, ArrayList<Journey>> j1= e1.getJourneys();
					int lastElement = j1.size()-1;
					ArrayList<Journey> journeys = j1.get(lastElement);
					int lastJourney = journeys.size()-1;
					departure = journeys.get(lastJourney).getArrival();
					
					listExcursion.add(em.createExcursion((i+1), entry, random, departure));
				}
				counter += random-1;
			}
			counter++;
		}
		
		Offer offer = new Offer(0f, listExcursion);
		float price = ComputeOfferPrice(offer);
		offer.setPrice(price);
		return offer;
	}
	
	
	public float ComputeJourneyPrice(Journey journey) {
		return journey.getPrice();
	}
	
	
	public float ComputeExcursionPrice(Excursion exc) {
		HashMap<Integer, ArrayList<Journey>> journeys = exc.getJourneys();
		float price = journeys.get(0).get(0).getDeparture().getPrice();
		
		for(int i = 0; i<journeys.size(); i++) {
			for(int j=0; j<journeys.get(i).size(); j++) {
				price += journeys.get(i).get(j).getPrice();
			}
		}
		return price;
	}
	
	
	public float ComputeOfferPrice(Offer offer) {
		ArrayList<Excursion> excursions = offer.getExcursions();
		float price = 0;
		
		for(int i =0; i<excursions.size(); i++) {
			price += ComputeExcursionPrice(excursions.get(i));
		}
		return price;
	}

	public ArrayList<Offer> createMultipleOffers(EntryOffer entry) {
		ArrayList<Offer> listOffers = new ArrayList<Offer>();
		
		for(int i = 0; i<3; i++) {
			listOffers.add(createOffer(entry));
		}
		
		return listOffers;
	}
	
	/*
	
	public HashMap<Offer, Integer> getComfort() {
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
