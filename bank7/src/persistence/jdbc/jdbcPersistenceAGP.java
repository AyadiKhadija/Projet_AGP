package persistence.jdbc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import business.offer.Coordinate;
import business.offer.Island;
import business.offer.Site;
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
			    		  result.getBoolean("is_historique"),
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
	

	


	

	
}
