package Transylvania.Classes;

import java.util.Date;

public class Standard extends Room {
    int price; 

    public Standard(String roomId, String hotelId, Date checkin, Date checkOut) {
        super(roomId, "Standard", hotelId, checkin, checkOut);
    }
    
    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }
}
