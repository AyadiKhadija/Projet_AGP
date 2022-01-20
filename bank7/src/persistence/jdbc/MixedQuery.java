package persistence.jdbc;

import persistence.jdbc.JdbcPersistence;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import business.offer.Site;
import lucene.LuceneOperator;

public class MixedQuery {
	public static ResultSet executeSQL(String querySQL) {
		return jdbcPersistenceAGP.QuerySite();
	}

	public static HashMap<String, Float> executeLucen(String keywords) {
		return LuceneOperator.operator(keywords);
	}
	
	public static ArrayList<Site> executeMixed(String query) {

		HashMap<String, Float> resultLucen = new HashMap<String, Float>();
		ResultSet resultSQL;
		HashMap <Integer,Float> result = new HashMap<Integer,Float>();

		String[] queries = query.split("with");
		System.out.println(queries[1]);
		resultSQL = executeSQL(queries[0]);
		resultLucen = executeLucen(queries[1]);
		
		try {
			while(resultSQL.next()) {
				
				Integer id = resultSQL.getInt("id_site");
				String name = resultSQL.getString("name_site");
				for (Entry<String, Float> m : resultLucen.entrySet()) {
					String fileId = m.getKey();
					if ((fileId).equals(id.toString())) {
						result.put(id, m.getValue());
			}
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SortResult(result);
	}

		
	
	public static ArrayList<Site> SortResult(HashMap <Integer,Float> hm){
		System.out.println("je suis la");
		HashMap <Integer,Float> sortedList = new HashMap<Integer,Float>();
		ArrayList <Integer> IdSiteSorted = new ArrayList<Integer>();
		while(!hm.isEmpty()) {
			Collection<Float> scored = hm.values();
			Set key = hm.keySet();
            Iterator it = scored.iterator();
            Iterator it2 = key.iterator();

            float scoreMax = 0;
            int keySite = 0;

            while (it.hasNext()) {
                float score = (float) it.next();
                int cle = (int) it2.next();
                if (score > scoreMax) {
                    scoreMax = score;
                    keySite = cle;
                }
            }
            System.out.println("id : "+keySite+" Score : "+scoreMax);
            System.out.println("hello");
            sortedList.put(keySite, scoreMax);
            //System.out.println("ID : " + site.getIdPlace() + "  |  Nom : " + site.getName() + "  |  Score : " + scoreMax);

            hm.remove(keySite);
            IdSiteSorted.add(keySite);
            
        }
		return finalSiteSorted(IdSiteSorted);
	}
	
	public static ArrayList<Site> finalSiteSorted(ArrayList<Integer> idListe){
		ArrayList<Site> result = new ArrayList<Site>();

		for (Integer id : idListe) {
			Site site = persistence.jdbc.jdbcPersistenceAGP.QuerySiteById(id);
			result.add(site);
		}
		return result;
		
	}
		
}
