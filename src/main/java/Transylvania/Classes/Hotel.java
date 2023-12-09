package ClassClass;

/**
 *
 * @author ASUS
 */
public class Hotel {
    String hotelId, hotelName;
    int totalDeluxe, totalStandard, totalSuite, star;

    public Hotel(String hotelId, String hotelName, int totalDeluxe, int totalStandard, int totalSuite, int star) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.totalDeluxe = totalDeluxe;
        this.totalStandard = totalStandard;
        this.totalSuite = totalSuite;
        this.star = star;
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
}
