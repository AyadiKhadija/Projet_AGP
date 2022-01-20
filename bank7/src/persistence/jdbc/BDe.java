package persistence.jdbc;

import java.util.ArrayList;

import business.offer.Hotel;
import business.offer.Site;
import business.offer.Transport;

public class BDe {
	
	//renvoie la liste des sites inferieurs a un prix donnée
	public ArrayList <Site> executeListSite(int price) {
		jdbcPersistenceAGP jd = new jdbcPersistenceAGP();
		return jd.QuerySiteOperand("SELECT * FROM Site s, Island i WHERE s.id_island=i.id_island where s.price <"+price);
	}
	
	//renvoie la liste des hotel inferieurs a un prix donnée
	public ArrayList<Hotel> executeListHotel(int price){
		jdbcPersistenceAGP jd = new jdbcPersistenceAGP();
		return jd.QueryHotelOperand("Select * from hotel h, island i where h.id_island=i.id_island and h.price_day <"+price);
	}
	
	public String executeTransportByNameIsland(String nom, String id){
		jdbcPersistenceAGP jd = new jdbcPersistenceAGP();
		return jd.QueryTransportByNameIsland("SELECT id_Transport FROM Transport t WHERE t.id_island=" +id+" AND t.name_Transport = '"+nom+"'");
	}
	
	public ArrayList<Transport> executeTransportById(String id_transport) {
		jdbcPersistenceAGP jd = new jdbcPersistenceAGP();
		return jd.QueryTransportOperand("select * from transport where id_Transport='"+id_transport+"'");
	}
	
	public ArrayList<Site> executeTheQueryMixed(ArrayList<String> keywords){
		MixedQuery test =new MixedQuery();
		String myWords="";
		for(String s : keywords) {
			myWords = myWords+" "+s;
		}
		return test.executeMixed("Select * from Site, island where site.id_island=island.id_island with "+myWords);

	}

}
