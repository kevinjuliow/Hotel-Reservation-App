package Transylvania.Classes;

import javax.swing.ImageIcon;

/**
 *
 * @author ASUS
 */
public class Hotel {
    String hotelId, hotelName, description, location;
    int totalDeluxe, totalStandard, totalSuite, star, standardPrice, deluxePrice, suitePrice;
    ImageIcon image;
    byte[] imageData;

    public Hotel() {}

    public Hotel(String hotelId, String hotelName, String description, String location, int totalDeluxe, int totalStandard, int totalSuite, int star, ImageIcon image) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.description = description;
        this.location = location;
        this.totalDeluxe = totalDeluxe;
        this.totalStandard = totalStandard;
        this.totalSuite = totalSuite;
        this.star = star;
        this.image = image;
    }
    
    public Hotel(String hotelId, String hotelName, String description, String location, int totalDeluxe, int totalStandard, int totalSuite, int star, byte[] imageData) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.description = description;
        this.location = location;
        this.totalDeluxe = totalDeluxe;
        this.totalStandard = totalStandard;
        this.totalSuite = totalSuite;
        this.star = star;
        this.imageData = imageData;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalDeluxe() {
        return totalDeluxe;
    }

    public void setTotalDeluxe(int totalDeluxe) {
        this.totalDeluxe = totalDeluxe;
    }

    public int getTotalStandard() {
        return totalStandard;
    }

    public void setTotalStandard(int totalStandard) {
        this.totalStandard = totalStandard;
    }

    public int getTotalSuite() {
        return totalSuite;
    }

    public void setTotalSuite(int totalSuite) {
        this.totalSuite = totalSuite;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public int getStandardPrice() {
        return standardPrice;
    }

    public void setStandardPrice(int standardPrice) {
        this.standardPrice = standardPrice;
    }

    public int getDeluxePrice() {
        return deluxePrice;
    }

    public void setDeluxePrice(int deluxePrice) {
        this.deluxePrice = deluxePrice;
    }

    public int getSuitePrice() {
        return suitePrice;
    }

    public void setSuitePrice(int suitePrice) {
        this.suitePrice = suitePrice;
    }
}
