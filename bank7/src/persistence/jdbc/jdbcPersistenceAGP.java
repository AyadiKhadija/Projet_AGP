package persistence.jdbc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import business.offer.*;
import dao.PersistenceAGP;
import lucene.LuceneTester;

public class jdbcPersistenceAGP implements PersistenceAGP{
	

	public static ResultSet QuerySite() {
		
		ResultSet result = null;
		String selectAddressQuery = "SELECT * FROM Site s, Island i WHERE s.id_island=i.id_island";
		ArrayList <Site> sites = new ArrayList<Site>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = JdbcConnection.getConnection().prepareStatement(selectAddressQuery);
			result = preparedStatement.executeQuery();
			
			 
			/*
			while (result.next()) {
			      sites.add(new Site(result.getInt("id_site"),
			    		  result.getBoolean("is_historique"),
			    		  result.getString("name_site"),
			    		  new Coordinate( result.getInt("longitude_site"),result.getInt("latitude_site")),
			    		  new Island(result.getString("name_island")),
			    		  result.getInt("price")));
			    }
			
			 */
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
			
	}
	
	public static ArrayList <Site> QuerySiteOperand(String query) {
		
		String selectAddressQuery = query;
		ArrayList <Site> sites = new ArrayList<Site>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = JdbcConnection.getConnection().prepareStatement(selectAddressQuery);
			ResultSet result = preparedStatement.executeQuery();
			
			 
			
			while (result.next()) {
			      sites.add(new Site(result.getInt("id_site"),
			    		  result.getBoolean("is_historic"),
			    		  result.getString("name_site"),
			    		  new Coordinate( result.getInt("longitude_site"),result.getInt("latitude_site")),
			    		  new Island(result.getString("name_island")),
			    		  result.getInt("price")));
			    }
			
			 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return sites;
			
	}

	public static Site QuerySiteById(int id) {
		Site readSite = new Site();
		PreparedStatement preparedStatement;
		
		try {
			String selectSiteQuery = "SELECT * FROM site s, Island i WHERE s.id_island=i.id_island AND id_site = ? ";
			preparedStatement = JdbcConnection.getConnection().prepareStatement(selectSiteQuery);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			
			
	
			while (result.next()) {
				readSite.setId_site(result.getInt("id_site"));
				readSite.setName(result.getString("name_site"));
				readSite.setTouristic(result.getBoolean("is_historic"));
				Island island = new Island(result.getString("name_island"));
				readSite.setIsland(island);
				Coordinate coordinate = new Coordinate( result.getInt("longitude_site"),result.getInt("latitude_site"));
				readSite.setLocation(coordinate);
			}
	
			preparedStatement.close();
	
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return readSite;
	}
	
	
	public static ArrayList<Hotel> QueryHotelOperand(String query) {
		
		String selectAddressQuery = query;
		ArrayList <Hotel> hotels = new ArrayList<Hotel>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = JdbcConnection.getConnection().prepareStatement(selectAddressQuery);
			ResultSet result = preparedStatement.executeQuery();
			
			 
			
			while (result.next()) {
			      hotels.add(new Hotel(result.getString("id_hotel"),
			    		  result.getString("name_hotel"),
			    		  result.getFloat("price_day"),
			    		  new Coordinate( result.getInt("longitude_hotel"),result.getInt("latitude_hotel")),
			    		  result.getInt("stars"),
			    		  new Island(result.getString("id_island"))));
			    }
			
			 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return hotels;
	}
	
	
	public static Hotel QueryHotelById(int id) {
		Hotel readHotel = new Hotel();
		PreparedStatement preparedStatement;
		
		try {
			String selectSiteQuery = "SELECT * FROM hotel h, Island i WHERE h.id_island=i.id_island AND id_hotel = ? ";
			preparedStatement = JdbcConnection.getConnection().prepareStatement(selectSiteQuery);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			
			
	
			while (result.next()) {
				readHotel.setId_hotel(result.getString("id_site"));
				readHotel.setName("name_hote");
				Coordinate coordinate = new Coordinate( result.getInt("longitude_site"),result.getInt("latitude_site"));
				readHotel.setLocation(coordinate);
				readHotel.setStars(result.getInt("stars"));
				Island island = new Island(result.getString("name_island"));
				readHotel.setIsland(island);
			}
	
			preparedStatement.close();
	
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return readHotel;
	}
	
	
	
	public static ArrayList<Transport> QueryTransportOperand(String query) {
		
		String selectAddressQuery = query;
		ArrayList <Transport> transports = new ArrayList<Transport>();
		PreparedStatement preparedStatement;
		try {
			preparedStatement = JdbcConnection.getConnection().prepareStatement(selectAddressQuery);
			ResultSet result = preparedStatement.executeQuery();
			
			 
			
			while (result.next()) {
				if(result.getString("name_Transport").equals("Bus")) {
					transports.add(new Bus(result.getString("id_Transport"),
			    		  result.getString("name_Transport"),
			    		  result.getString("id_Island")));
				}
				else if(result.getString("name_Transport").equals("Boat")) {
					transports.add(new Boat(result.getString("id_Transport"),
				    		  result.getString("name_Transport"),
				    		  result.getString("id_Island")));
				}
				else {
					transports.add(new Walk(result.getString("id_Transport"),
				    		  result.getString("name_Transport"),
				    		  result.getString("id_Island")));
				}
			}
			 
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return transports;
	}
	
	
	public static String QueryTransportByNameIsland(String name, String id) {
		String id_Transp = "";
		PreparedStatement preparedStatement;
		
		try {
			String selectSiteQuery = "SELECT id_Transport FROM Transport t WHERE t.id_island=? AND t.name_Transport = ? ";
			preparedStatement = JdbcConnection.getConnection().prepareStatement(selectSiteQuery);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, name);
			ResultSet result = preparedStatement.executeQuery();
			
			
	
			while (result.next()) {
				id_Transp = result.getString("id_Transport");
			}
	
			preparedStatement.close();
	
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
		return id_Transp;
	}
}
