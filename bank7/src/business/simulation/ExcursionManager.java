package business.simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import business.offer.*;
import persistence.jdbc.BDe;
import persistence.jdbc.jdbcPersistenceAGP;


public class ExcursionManager {
	
	BDe bd = new BDe();
	
	public int getDistance(Place departure, Place arrival) {
		int absDeparture = departure.getLocation().getX();
		int ordDeparture = departure.getLocation().getY();
		int absArrival = arrival.getLocation().getX();
		int ordArrival = arrival.getLocation().getY();
		
		double geoDistance = Math.sqrt(Math.pow(absDeparture - absArrival, 2.0) + Math.pow(ordDeparture - ordArrival,  2.0));
		
		int realDistance = (int)(geoDistance*380)/2010;
		
		return realDistance;
	}
	
	public Transport getTransport(Place departure, Place arrival) {
		int distance = getDistance(departure, arrival);
		
		if(!departure.getIsland().getName_island().equals(arrival.getIsland().getName_island())) {
			String id_Transport = bd.executeTransportByNameIsland("Boat", departure.getIsland().getId_island());
			return bd.executeTransportById(id_Transport).get(0);
		}
		else {
			if(distance > 2) {
				String id_Transport = bd.executeTransportByNameIsland("Bus", departure.getIsland().getId_island());
				return bd.executeTransportById(id_Transport).get(0);
			}
			else {
				return new Walk("", "Walk", departure.getIsland().getId_island());
			}
		}
	}
	
	public float getPriceTransport(Transport transport, int distance) {
		if(transport instanceof Boat) {
			return distance * 0.3f;
		}
		else if(transport instanceof Bus) {
			return distance * 0.2f;
		}
		else {
			return 0f;
		}
	}
	
	public float getPriceJourney(Place departure, Place arrival, Transport transport, int distance) {
		float price = getPriceTransport(transport, distance);
		
		if(arrival instanceof Site) {
			price += arrival.getPrice();
		}
		
		return price;
	}
	
	public int getDuration(Transport transport, int distance) {
		if(transport instanceof Boat) {
			return (distance*70)/100;
		}
		else if(transport instanceof Bus) {
			return (distance*50)/100;
		}
		else {
			return (distance*20)/2;
		}
	}
	
	public float budgetPerPrice(EntryOffer entry, String type) {
		float budget = entry.getMaxPrice();
		
		if(type.equals("Hotel")) {
			budget /= 5;
		}
		else if (type.equals("Site")) {
			if(entry.getIsIntensity() == true)	budget /= 10;
			else budget /=4;
		}
		return budget;
	}
	
	
	public ArrayList<Place> sortHotelbyPrice(EntryOffer entry) {
		float budget = budgetPerPrice(entry, "Hotel");
		ArrayList<Hotel> hotels = bd.executeListHotel((int)budget);
		ArrayList<Place> places = new ArrayList<Place>();
		for(int i = 0; i<hotels.size();i++) {
			places.add(hotels.get(i));
		}
		
		return places;
	}
	
	public ArrayList<Site> sortSitebyPrice(EntryOffer entry, ArrayList<Site> wantedSites) {
		float budget = budgetPerPrice(entry, "Site");
		ArrayList<Site> sites = new ArrayList<Site>();
		for(int i = 0; i<wantedSites.size(); i++) {
			if(wantedSites.get(i).getPrice()<budget) {
				sites.add(wantedSites.get(i));
			}
		}
		return sites;
	}
	
	public ArrayList<Place> sortSitebyFunction(EntryOffer entry, ArrayList<Site> wantedSites, ArrayList<Site> allSite) {
		ArrayList<Place> sites = new ArrayList<Place>();
		
		
			for(int j = 0; j<wantedSites.size(); j++) {
				sites.add(wantedSites.get(j));
			}
		for(int i = 0; i<allSite.size(); i++) {
			double random = Math.random();
			if(allSite.get(i).isTouristic() == entry.getIsCultural()) {
				if(random<0.3) {
					sites.add(allSite.get(i));
				}
			}
			else {
				if(random<0.1) {
					sites.add(allSite.get(i));
				}
			}
		}
		
		
		return sites;
	}
	

	
	public ArrayList<Place> radius(Place departure, ArrayList<Place> place) {
		ArrayList<Place> compatiblePlaces = new ArrayList<Place>();
		
		for(int i = 0; i<place.size();i++) {
			if(getDistance(departure, place.get(i))<1000) {
				compatiblePlaces.add(place.get(i));
			}
		}
		
		return compatiblePlaces;
	}
	
	
	public ArrayList<Journey> createJourneys(EntryOffer entry, Place departure) {
		ArrayList<Journey> journeys = new ArrayList<Journey>();
		float budget = budgetPerPrice(entry, "Site");
		
		ArrayList<Place> hotels = sortHotelbyPrice(entry);
		ArrayList<Site> wantedSites = bd.executeTheQueryMixed(entry.getKeyWords());
		ArrayList<Site> sites = sortSitebyPrice(entry, wantedSites);
		ArrayList<Site> allSites = bd.executeListSite((int)budget);
		ArrayList<Place> selectedSites = sortSitebyFunction(entry, sites, allSites);
		
		int randomHotel;
		Place arrival;
		 
		ArrayList<Place> places = new ArrayList<Place>();
		
		for(int i = 0; i<3; i++) {
			if(i == 0) {
				places = radius(departure,selectedSites);
				
				while(places.size()==0) {
					randomHotel = (int) (Math.random() * hotels.size());
					departure = hotels.get(randomHotel);
					places = radius(departure,selectedSites);
				}
				
				int randomSite = (int) (Math.random() * places.size());
				arrival = places.get(randomSite);
			}
			else if(i==1) {	
				places = radius(departure,selectedSites);
				if(places.size()==1) {
					places = radius(departure,hotels);
					randomHotel = (int) (Math.random() * places.size());
					arrival = places.get(randomHotel);
					Journey journey = addJourney(departure, arrival);
					journeys.add(journey);
					break;
				}
				else {
					int randomSite = (int) (Math.random() * places.size());
					while(places.get(randomSite).getName().equals(departure.getName())) {
						randomSite = (int) (Math.random() * places.size());
					}
					arrival = places.get(randomSite);
				}
			}
			else {
				places = radius(departure,hotels);
				randomHotel = (int) (Math.random() * places.size());
				arrival = places.get(randomHotel);
			}
			
			Journey journey = addJourney(departure, arrival);
			journeys.add(journey);
			System.out.println(journeys.size());
			departure = arrival;
		}
		return journeys;
	}
	
	public Journey addJourney(Place departure, Place arrival) {
		int distance = getDistance(departure, arrival);
		Transport transport = getTransport(departure, arrival);
		float transportPrice = getPriceTransport(transport, distance);
		float price = getPriceJourney(departure, arrival, transport, distance);
		System.out.println("Le prix du trajet sans l'hotel est " + price + "�" + "("+ transportPrice + "� pour transport et " + (price - transportPrice) + "� pour le site pour " + distance + "km avec Depart = "+ departure.getName() + " et arriv� = " + arrival.getName());
		int duration = getDuration(transport, distance);
		return new Journey(departure, arrival, distance, transport, price, duration);
	}
	
	public Excursion createExcursion(int date, EntryOffer entry, int excursionPerDay, Place departure) {
		HashMap<Integer, ArrayList<Journey>> excursions = new HashMap<Integer, ArrayList<Journey>>();
		
		ArrayList<Journey> journeys = new ArrayList<Journey>();
		ArrayList<Place> hotels = sortHotelbyPrice(entry);
		for(int i = 0; i<excursionPerDay; i++) {
			if(i==0) {
				int randomHotel = (int) (Math.random() * hotels.size());
				departure = hotels.get(randomHotel);
				journeys = createJourneys(entry, departure);
				excursions.put(i, journeys);
			}
			else {				
				departure = null;
				for(int j = 0; j<excursions.get(0).size(); j++) {
					if(j==excursions.get(0).size()-1) {
						departure = excursions.get(0).get(j).getArrival();
					}	
				}
				journeys = createJourneys(entry, departure);
				excursions.put(i, journeys);
			}
		}
		Excursion excursion = new Excursion(date, excursions);
		return excursion;
	}
	
}
