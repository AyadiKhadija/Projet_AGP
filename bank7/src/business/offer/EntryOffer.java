package business.offer;

import java.util.ArrayList;

/**
 * This class encapsulates simulation entry parameters.
 */
public class EntryOffer {
    private int maxPrice;
    private boolean isIntensity;
    private boolean isCultural;
    private ArrayList<String> keyWords;

    public EntryOffer() {
    }

    public EntryOffer(int maxPrice,boolean isIntensity, boolean isCultural, ArrayList<String> keyWords) {
        this.maxPrice = maxPrice;
        this.isIntensity = isIntensity;
        this.isCultural = isCultural;
        this.keyWords = keyWords;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setSimulationDuration(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public boolean getIsIntensity() {
        return isIntensity;
    }

    
    public void setIsIntensity(boolean isIntensity) {
        this.isIntensity = isIntensity;
    }

    public boolean getIsCultural() {
        return isCultural;
    }

    public void setIsCultural(boolean isCultural) {
        this.isCultural = isCultural;
    }

    public ArrayList<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(ArrayList<String> keyWords) {
        this.keyWords = keyWords;
    }



}
