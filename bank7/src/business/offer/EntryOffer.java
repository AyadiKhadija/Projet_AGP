package business.offer;

import java.util.ArrayList;

/**
 * This class encapsulates simulation entry parameters.
 */
public class EntryOffer {
    private int maxPrice;
    private int isIntensity;
    private int isCultural;
    private ArrayList<String> keyWords;

    public EntryOffer() {
    }

    public EntryOffer(int maxPrice,int isIntensity, int isCultural, ArrayList<String> keyWords) {
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

    public int getIsIntensity() {
        return isIntensity;
    }

    
    public void setIsIntensity(int isIntensity) {
        this.isIntensity = isIntensity;
    }

    public int getIsCultural() {
        return isCultural;
    }

    public void setIsCultural(int isCultural) {
        this.isCultural = isCultural;
    }

    public ArrayList<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(ArrayList<String> keyWords) {
        this.keyWords = keyWords;
    }



}
