package persistence.jdbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import business.offer.Site;

public class TestMixed {

	public static void main(String[] args) {
		MixedQuery test =new MixedQuery();
		ArrayList <Site> array = test.executeMixed("Select * from Site, island where site.id_island=island.id_island with jet montagne");
		for(Site i : array) {
			System.out.println(i.getId_site()+" "+i.getName());
		}
		}

		
	}


