package persistence.jdbc;

import java.util.ArrayList;

import business.offer.Site;

public class testJDBC {

	public static void main(String[] args) {
		jdbcPersistenceAGP test = new jdbcPersistenceAGP();
		ArrayList<Site> testQ = test.QuerySite();
		for(Site s : testQ) {
			System.out.println("Nom de l'activité : "+s.getName());
			System.out.println();
		}
		

	}

}

