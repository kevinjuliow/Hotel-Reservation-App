package Transylvania.Classes;

public class Room {

    String roomType;
    String checkIn, checkOut;
    int roomCount, price, hotelId;

    public Room(String roomType, int roomCount, int hotelId, String checkIn, String checkOut, int price) {
        this.setRoomType(roomType);
        this.setRoomCount(roomCount);
        this.setHotelId(hotelId);
        this.setCheckIn(checkIn);
        this.setCheckOut(checkOut);
        this.setPrice(price);
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
