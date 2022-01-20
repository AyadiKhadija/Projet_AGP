package beans;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.chart.PieChartModel;

import business.offer.Journey;
import business.offer.Offer;
import business.simulation.Excursion;
import business.simulation.OfferManager;
import business.simulation.Simulation;
import dao.StatisticPersistence;

/**
 * 
 * Proxy design pattern is used for getting simulation results.
 *
 */
@ManagedBean
@RequestScoped
public class ResultBean {


	@ManagedProperty(value = "#{entryBean}")
	private EntryBean entryBean;
	
	public ResultBean() {

	}
	
	private ArrayList<Offer> offers;
	
	private Offer offer;
	
	
	
	private OfferManager offermanager = new OfferManager();

	@PostConstruct



	public EntryBean getEntryBean() {
		return entryBean;
	}
	

	public void setEntryBean(EntryBean entryBean) {
		this.entryBean = entryBean;
	}
/*
	public int getMoffers() {
		return entryBean.getOfferManager().test();
	}*/
	
	public Offer getOneOffer() {
		return entryBean.getOfferManager().createOffer(entryBean.getEntry());
	}
	
	public ArrayList<Offer> getMoffers() {
		return entryBean.getOfferManager().createMultipleOffers(entryBean.getEntry());
	}
	
	public Journey getMJouney() {
		Offer o=entryBean.getOfferManager().createOffer(entryBean.getEntry());
		Excursion e=o.getExcursions().get(0);
		Journey j=e.getJourneys().get(0).get(1);
		return j;
	}
	
	
	
	public Excursion getMExcursion() {
		Offer o=entryBean.getOfferManager().createOffer(entryBean.getEntry());
		Excursion e=o.getExcursions().get(0);
		return e;
	}
	
	
	
	   public void init() {
	        offers = offermanager.createMultipleOffers(entryBean.getEntry());
	    }
	    
	    public String printInfos() {
	    	return "entry";
	    }

		public ArrayList<Offer> getOffers() {
			return entryBean.getOfferManager().createMultipleOffers(entryBean.getEntry());
		}


		public void setOffers(ArrayList<Offer> offers) {
			this.offers = offers;
		}


	
	

	
/*	public ArrayList<Offer> getMOffers() {
		Simulation simulation = entryBean.getSimulation();
		return simulation.getStatisticManager().calculateClientSatisfactionRate();
	}*/

}
