package Transylvania.Classes;

import java.util.Date;

public abstract class Room {
    String roomId, type, hotelId;
    Date checkIn, checkOut;
    int price;

    public Room(String roomId, String type, String hotelId, Date checkIn, Date checkOut) {
        this.setRoomId(roomId);
        this.setType(type);
        this.setHotelId(hotelId);
        this.setCheckin(checkIn);
        this.setCheckOut(checkOut);
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public Date getCheckin() {
        return checkIn;
    }

    public void setCheckin(Date checkin) {
        this.checkIn = checkin;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    abstract int getPrice();
    abstract void setPrice(int price);
}
