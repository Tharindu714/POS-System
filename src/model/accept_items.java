package model;

import java.util.Date;

public class accept_items {

    private String equipmentID;
    private String brandName;
    private String type;
    private double price;
    private Date given_date;
    private String reason;
    private String est_price;

    /**
     * @return the equipmentID
     */
    public String getEquipmentID() {
        return equipmentID;
    }

    /**
     * @param equipmentID the equipmentID to set
     */
    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    /**
     * @return the brandName
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * @param brandName the brandName to set
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getGiven_date() {
        return given_date;
    }
    
    public void setGiven_date(Date given_date) {
        this.given_date = given_date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the est_price
     */
    public String getEst_price() {
        return est_price;
    }

    /**
     * @param est_price the est_price to set
     */
    public void setEst_price(String est_price) {
        this.est_price = est_price;
    }
}
