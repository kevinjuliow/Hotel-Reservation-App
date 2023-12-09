package Transylvania.Classes;

import java.util.Date;

public class Deluxe extends Room {
    int price; 

    public Deluxe(String roomId, String hotelId, Date checkin, Date checkOut) {
        super(roomId, "Deluxe", hotelId, checkin, checkOut);
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
