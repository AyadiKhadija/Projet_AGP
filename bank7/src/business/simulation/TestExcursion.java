package business.simulation;

import java.util.ArrayList;
import java.util.HashMap;

import business.offer.Boat;
import business.offer.EntryOffer;
import business.offer.Hotel;
import business.offer.Journey;
import business.offer.Offer;
import business.offer.Site;
import business.offer.Transport;
import persistence.jdbc.BDe;
import persistence.jdbc.jdbcPersistenceAGP;

public class TestExcursion {
	public static void main(String[] args) {
		
		/*BDe bd = new BDe();
		String id_Transport = bd.executeTransportByNameIsland("Boat", "1116");
		System.out.println(id_Transport);*/
		ArrayList<String> mots = new ArrayList<String>();
		mots.add("jet");
		mots.add("montagne");
		//mots.add("parachute");
		mots.add("pont");
		EntryOffer entry = new EntryOffer(2500, true, false, mots);
		ExcursionManager em = new ExcursionManager();
			
		
		//ArrayList<Hotel> hotels = em.initHotels();
		
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
		OfferManager om= new OfferManager();
		Offer offer = om.createOffer(entry);
		ArrayList<Excursion> exc = offer.getExcursions();
		for(int k = 0; k<exc.size(); k++) {
			Excursion exc1=exc.get(k);
			HashMap<Integer, ArrayList<Journey>> excursions = exc1.getJourneys();
			String transportName = "";
			for(int i = 0; i<excursions.size(); i++) {
				for(int j = 0; j<excursions.get(i).size(); j++) {
					
					if( excursions.get(i).get(j).getTransport() instanceof Boat) {
						transportName = "Bateau";
					}
					else {
						transportName = "Bus";
					}
					
					System.out.println("Departure = " + excursions.get(i).get(j).getDeparture().getName() + excursions.get(i).get(j).getDeparture().getIsland().getName_island() +"\t" + transportName + "\t Arrival = " + excursions.get(i).get(j).getArrival().getName() + excursions.get(i).get(j).getArrival().getIsland().getName_island() + " Jour = " + exc1.getDate());
				}
				System.out.println("---");
			}
			
		}
		System.out.println("Le prix total de l'offre est " + offer.getPrice() + "�");
		
		
		

		
		
		//TEST 4
		/*OfferManager om= new OfferManager();
		ArrayList<Offer> offers = om.createMultipleOffers(entry);
		
		for(int i = 0; i<offers.size(); i++) {
			System.out.println(offers.get(i).getPrice());
		}*/
		
		
		//TEST 5 
		/*ArrayList<Hotel> myhotels = jdbcPersistenceAGP.QueryHotelOperand("SELECT * FROM Hotel h WHERE h.price_day<250");
		for(int i = 0; i<myhotels.size(); i ++) {
			System.out.println("Name : " + myhotels.get(i).getName() + " \tPrix : " + myhotels.get(i).getPrice());
		}*/
		
		//TEST 6
		/*ArrayList<Transport> transports = jdbcPersistenceAGP.QueryTransportOperand("SELECT * FROM Transport t WHERE t.id_island = 1111");
		for(int i = 0; i<transports.size(); i ++) {
			System.out.println("Name : " + transports.get(i).getName_Transport());
		}*/
		
		//TEST7
		/*String id_Transport = jdbcPersistenceAGP.QueryTransportByNameIsland("Bateau", "1111");
		System.out.println(id_Transport);*/
	}
}
