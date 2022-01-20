package business.simulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import business.offer.*;


public class ExcursionManager {
	
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
		int duration = 0;
		float price = 0;
		
		if(!departure.getIsland().getName().equals(arrival.getIsland().getName())) {
			Boat boat = new Boat();
			duration = getDuration(boat, distance);
			price = getPriceTransport(boat, distance);
			return new Boat(duration, price, distance);
		}
		else {
			if(distance > 2) {
				Bus bus = new Bus();
				duration = getDuration(bus, distance);
				price = getPriceTransport(bus, distance);
				return new Bus(duration, price, distance);
			}
			else {
				Walk walk = new Walk();
				duration = getDuration(walk, distance);
				price = getPriceTransport(walk, distance);
				return new Walk(duration, price, distance);
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
	
	/*public Journey createJourney(Place departure, Place arrival) {
		Transport transport = getTransport(departure, arrival);
		int distance = getDistance(departure, arrival);
		float price = getPriceJourney(departure, arrival, transport, distance);
		int duration = getDuration(transport, distance);
		return new Journey(departure, arrival, distance, transport, price, duration);
	}*/
	
	public ArrayList<Site> initSites() {
		ArrayList<Site> sites = new ArrayList<Site>();
		Island fuerte = new Island("Fuerte");
		Island palma = new Island("Las Palma");
		Island papa = new Island("Papa");
		
		sites.add(new Site("", true, "Museum", new Coordinate(8050,3800), fuerte, 15.0f));
		sites.add(new Site("", false, "Jet", new Coordinate(6500,1800), palma, 175.0f));
		sites.add(new Site("", true, "Grotte", new Coordinate(3650,2840), papa, 5.0f));
		sites.add(new Site("", false, "Parachute", new Coordinate(7800,2780), fuerte, 150.0f));
		sites.add(new Site("", false, "Sous-marin", new Coordinate(6800,2790), palma, 250.0f));
		sites.add(new Site("", false, "Cheval", new Coordinate(3190,1800), papa, 125.0f));
		sites.add(new Site("", true, "Art", new Coordinate(7890,2050), fuerte, 7.5f));
		sites.add(new Site("", true, "Memorial", new Coordinate(6100,1100), palma, 2.0f));
		sites.add(new Site("", true, "Monument", new Coordinate(2905,1920), papa, 7.0f));
		sites.add(new Site("", false, "Surf", new Coordinate(8900,2640), fuerte, 105.0f));
		sites.add(new Site("", false, "Escalade", new Coordinate(6200,1100), palma, 85.0f));
		sites.add(new Site("", false, "Patinoire", new Coordinate(3500,1600), papa, 10.0f));
		
		return sites;
	}
	
	public ArrayList<Hotel> initHotels() {
		ArrayList<Hotel> hotels = new ArrayList<Hotel>();
		Island fuerte = new Island("Fuerte");
		Island palma = new Island("Las Palma");
		Island papa = new Island("Papa");
		
		hotels.add(new Hotel(5, "Hotel-A", new Coordinate(8450,2050), fuerte, 250.0f));
		hotels.add(new Hotel(3, "Hotel-B", new Coordinate(6500,2600), palma, 120.0f));
		hotels.add(new Hotel(2, "Hotel-C", new Coordinate(4000,3200), papa, 75.0f));
		hotels.add(new Hotel(5, "Hotel-D", new Coordinate(8150,3200), fuerte, 350.0f));
		hotels.add(new Hotel(4, "Hotel-E", new Coordinate(6800,2000), palma, 175.0f));
		hotels.add(new Hotel(2, "Hotel-F", new Coordinate(2900,1740), papa, 95.0f));
		hotels.add(new Hotel(3, "Hotel-G", new Coordinate(7909,1900), fuerte, 115.0f));
		hotels.add(new Hotel(5, "Hotel-H", new Coordinate(6000,1000), palma, 325.0f));
		hotels.add(new Hotel(2, "Hotel-G", new Coordinate(2990,1700), papa, 100.0f));
		hotels.add(new Hotel(4, "Hotel-I", new Coordinate(8085,2907), fuerte, 152.0f));
		hotels.add(new Hotel(5, "Hotel-J", new Coordinate(6300,1500), palma, 275.0f));
		hotels.add(new Hotel(4, "Hotel-K", new Coordinate(4040,3290), papa, 140.0f));
		
		return hotels;
	}
	
	public ArrayList<Place> sortHotelbyPrice(EntryOffer entry) {
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
					System.out.println("A");
					randomHotel = (int) (Math.random() * places.size());
					System.out.println("B");
					arrival = places.get(randomHotel);
					System.out.println("C");
					Journey journey = addJourney(departure, arrival);
					System.out.println("D");
					journeys.add(journey);
					System.out.println("E");
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
			System.out.println("Le prix du trajet sans l'hotel est " + price + "€" + "("+ transportPrice + "€ pour transport et " + (price - transportPrice) + "€ pour le site pour " + distance + "km)");
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
		transport.setPrice((int)transportPrice);
		float price = getPriceJourney(departure, arrival, transport, distance);
		System.out.println("Le prix du trajet sans l'hotel est " + price + "€" + "("+ transportPrice + "€ pour transport et " + (price - transportPrice) + "€ pour le site pour " + distance + "km avec Depart = "+ departure.getName() + " et arrivé = " + arrival.getName());
		int duration = getDuration(transport, distance);
		return new Journey(departure, arrival, distance, transport, price, duration);
	}
	
	public Excursion createExcursion(int date, EntryOffer entry, int excursionPerDay, Place departure) {
		HashMap<Integer, ArrayList<Journey>> excursions = new HashMap<Integer, ArrayList<Journey>>();
		
		ArrayList<Journey> journeys = new ArrayList<Journey>();
		ArrayList<Place> hotels = sortHotelbyPrice(entry);
		for(int i = 0; i<excursionPerDay; i++) {
			if(i==0) {
				/*int randomHotel = (int) (Math.random() * hotels.size());
				departure = hotels.get(randomHotel);*/
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
