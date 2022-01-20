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
	
	
	/*public ArrayList<Place> sortHotelbyPrice(EntryOffer entry) {
		float hotelBudget = entry.getMaxPrice()*0.6f;
		float budgetPerNight = hotelBudget/5;
		ArrayList<Hotel> hotels = initHotels();
		ArrayList<Place> myHotels = new ArrayList<Place>();
		
		for(int i = 0; i<hotels.size(); i++) {
			//System.out.println(budgetPerNight + "--" +hotels.get(i).getPrice()+"\n\n" );
			if(hotels.get(i).getPrice()<=budgetPerNight) {
				myHotels.add(hotels.get(i));
			}
		}
		
		return myHotels;
	}
	
	public ArrayList<Site> sortSitebyPrice(EntryOffer entry) {
		float siteBudget = entry.getMaxPrice() * 0.4f;
		ArrayList<Site> sites = initSites();
		ArrayList<Site> mySites = new ArrayList<Site>();
		
		int budgetForSite;
		if(entry.getIsIntensity() == true) {
			budgetForSite = (int)siteBudget/10;
		}
		else {
			budgetForSite = (int)siteBudget/4;
		}
		
		for(int i = 0; i<sites.size(); i++) {
			if(sites.get(i).getPrice()<budgetForSite) {
				mySites.add(sites.get(i));
			}
		}
		System.out.println("Tailles de l'AL des sites possible pour moi " + mySites.size());
		return mySites;
	}*/
	
	public ArrayList<Place> sortHotelbyPrice(EntryOffer entry) {
		float budget = budgetPerPrice(entry, "Hotel");
		ArrayList<Hotel> hotels = bd.executeListHotel((int)budget);
		ArrayList<Place> places = new ArrayList<Place>();
		for(int i = 0; i<hotels.size();i++) {
			places.add(hotels.get(i));
		}
		
		return places;
	}
	
	public ArrayList<Site> sortSitebyPrice(EntryOffer entry) {
		float budget = budgetPerPrice(entry, "Site");
		ArrayList<Site> sites = bd.executeListSite((int)budget);
		return sites;
	}
	
	public ArrayList<Place> sortSitebyFunction(ArrayList<Site> sites, EntryOffer entry) {
		ArrayList<Place> siteByFunction = new ArrayList<Place>();
		for(int i = 0; i<sites.size(); i++) {
			double random = Math.random();
			if(sites.get(i).isTouristic() == entry.getIsCultural()) {
				siteByFunction.add((Place)sites.get(i));
			}
			else {
				if(entry.getMaxPrice()>3500) {
					if(random<0.2) siteByFunction.add((Place)sites.get(i));
				}
				else {
					if(random<0.5) siteByFunction.add((Place)sites.get(i));
				}
			}
		}
		return siteByFunction;
	}
	
	
	public ArrayList<Place> radius(Place departure, ArrayList<Place> place) {
		ArrayList<Place> compatiblePlaces = new ArrayList<Place>();
		
		for(int i = 0; i<place.size();i++) {
			if(getDistance(departure, place.get(i))<250) {
				compatiblePlaces.add(place.get(i));
			}
		}
		System.out.println("Places compatible avec moi " + compatiblePlaces.size());
		return compatiblePlaces;
	}
	
	
	public ArrayList<Journey> createJourneys(EntryOffer entry, Place departure) {
		ArrayList<Journey> journeys = new ArrayList<Journey>();
		
		ArrayList<Place> hotels = sortHotelbyPrice(entry);
		ArrayList<Site> sites = sortSitebyPrice(entry);
		ArrayList<Place> selectedSites = sortSitebyFunction(sites, entry);
		
		//int randomHotel = (int) (Math.random() * hotels.size());
		//Place departure = hotels.get(randomHotel);
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
					//places = radius(departure,selectedSites);
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
			/*int distance = getDistance(departure, arrival);
			Transport transport = getTransport(departure, arrival);
			float transportPrice = getPriceTransport(transport, distance);
			transport.setPrice((int)transportPrice);
			float price = getPriceJourney(departure, arrival, transport, distance);
			System.out.println("Le prix du trajet sans l'hotel est " + price + "�" + "("+ transportPrice + "� pour transport et " + (price - transportPrice) + "� pour le site pour " + distance + "km)");
			int duration = getDuration(transport, distance);*/
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
