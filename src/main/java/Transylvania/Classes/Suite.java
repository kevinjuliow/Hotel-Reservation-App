package Transylvania.Classes;

import java.util.Date;

public class Suite extends Room {
    int price; 

    public Suite(String roomId, String hotelId, Date checkin, Date checkOut) {
        super(roomId, "Suite", hotelId, checkin, checkOut);
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
