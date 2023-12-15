package Transylvania.Classes;

public class Transaction {
    String paymentMethod, check_in, check_out, type;
    int userId, roomId, transactionId, transactionPrice, countRoom, hotelId;
    double taxFee;

    public Transaction(){};

    public Transaction(int userId, int roomId, String paymentMethod, String check_in, String check_out, String type, int transactionPrice, double taxFee) {
        this.userId = userId;
        this.roomId = roomId;
        this.paymentMethod = paymentMethod;
        this.check_in = check_in;
        this.check_out = check_out;
        this.type = type;
        this.transactionPrice = transactionPrice;
        this.taxFee = taxFee;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getCountRoom() {
        return countRoom;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public void setCountRoom(int countRoom) {
        this.countRoom = countRoom;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCheck_in() {
        return check_in;
    }

    public void setCheck_in(String check_in) {
        this.check_in = check_in;
    }

    public String getCheck_out() {
        return check_out;
    }

    public void setCheck_out(String check_out) {
        this.check_out = check_out;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(int transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public double getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(double taxFee) {
        this.taxFee = taxFee;
    }
}
