package business.simulation;

import java.util.ArrayList;
import java.util.HashMap;

import business.offer.Boat;
import business.offer.EntryOffer;
import business.offer.Hotel;
import business.offer.Journey;
import business.offer.Offer;

public class TestExcursion {
	public static void main(String[] args) {
		EntryOffer entry = new EntryOffer(3600, true, false, null);
		ExcursionManager em = new ExcursionManager();
		
		ArrayList<Hotel> hotels = em.initHotels();
		
		//TEST 1
		/*ArrayList<Journey> journeys = em.createJourneys(entry, hotels.get(1));
		
		for(int i = 0; i<journeys.size(); i++) {
			System.out.println("Departure = " + journeys.get(i).getDeparture().getName() + " Arrival = " + journeys.get(i).getArrival().getName());
		}*/
		
		//TEST2
		/*Excursion excursion = em.createExcursion(1, entry, 2);
		HashMap<Integer, ArrayList<Journey>> excursions = excursion.getJourneys();
		String transportName = "";
		for(int i = 0; i<excursions.size(); i++) {
			for(int j = 0; j<excursions.get(i).size(); j++) {
				
				if( excursions.get(i).get(j).getTransport() instanceof Boat) {
					transportName = "Boat";
				}
				else {
					transportName = "Bus";
				}
				
				System.out.println("Departure = " + excursions.get(i).get(j).getDeparture().getName() + excursions.get(i).get(j).getDeparture().getIsland().getName() +"\t" + transportName + "\t Arrival = " + excursions.get(i).get(j).getArrival().getName() + excursions.get(i).get(j).getArrival().getIsland().getName());
			}
			System.out.println("---");
		}*/
		
		
		//TEST3
		/*OfferManager om= new OfferManager();
		Offer offer = om.createOffer(entry);
		ArrayList<Excursion> exc = offer.getExcursions();
		for(int k = 0; k<exc.size(); k++) {
			Excursion exc1=exc.get(k);
			HashMap<Integer, ArrayList<Journey>> excursions = exc1.getJourneys();
			String transportName = "";
			for(int i = 0; i<excursions.size(); i++) {
				for(int j = 0; j<excursions.get(i).size(); j++) {
					
					if( excursions.get(i).get(j).getTransport() instanceof Boat) {
						transportName = "Boat";
					}
					else {
						transportName = "Bus";
					}
					
					System.out.println("Departure = " + excursions.get(i).get(j).getDeparture().getName() + excursions.get(i).get(j).getDeparture().getIsland().getName() +"\t" + transportName + "\t Arrival = " + excursions.get(i).get(j).getArrival().getName() + excursions.get(i).get(j).getArrival().getIsland().getName() + " Jour = " + exc1.getDate());
				}
				System.out.println("---");
			}
			
		}
		System.out.println("Le prix total de l'offre est " + offer.getPrice() + "€");*/
		
		
		//TEST 4
		OfferManager om= new OfferManager();
		ArrayList<Offer> offers = om.createMultipleOffers(entry);
		
		for(int i = 0; i<offers.size(); i++) {
			System.out.println(offers.get(i).getPrice());
		}
	}
}
