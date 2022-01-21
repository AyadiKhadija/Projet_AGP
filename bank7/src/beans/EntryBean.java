package beans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import business.offer.EntryOffer;
import business.simulation.OfferManager;
import business.spring.SpringIoC;


/**
 * Simulation bean controller used to collect simulation entry parameters and to start the simulation.
 * 
 * The proxy design pattern is used for avoiding redundant code copy.
 */
@ManagedBean
@SessionScoped
public class EntryBean {

	/**
	 * Proxy encapsulated object. All get/set of parameters work on this proxy object.
	 */
	private EntryOffer entry = new EntryOffer();

	private OfferManager offermanager = new OfferManager();

	public EntryBean() {
	}

	public String startSimulation() {
		System.out.println(entry.getKeyWords().get(0));
		
		offermanager.createMultipleOffers(entry);
		//offermanager.buildBank();
		//offermanager.simulate();
		return "result";
	}

	public EntryOffer getEntry() {
		return entry;
	}

	public void setEntry(EntryOffer entry) {
		this.entry = entry;
	}

	public OfferManager getOfferManager() {
		return offermanager;
	}

	public void setOfferManager(OfferManager offermanager) {
		this.offermanager = offermanager;
	}

	public int getMaxPrice() {
		return entry.getMaxPrice();
	}

	public void setMaxPrice(int maxPrice) {
		entry.setMaxPrice(maxPrice);
	}

	public boolean getIsIntensity() {
		return entry.getIsIntensity();
	}

	public void setIsIntensity(boolean isIntensity) {
		entry.setIsIntensity(isIntensity);
	}

	public boolean getIsCultural() {
		return entry.getIsCultural();
	}

	public void setIsCultural(boolean isCultural) {
		entry.setIsCultural(isCultural);
	}

	public ArrayList<String> getKeyWords() {
		return entry.getKeyWords();
	}

	public void setKeyWords(ArrayList<String> keyWords) {
		entry.setKeyWords(keyWords);
	}


}
