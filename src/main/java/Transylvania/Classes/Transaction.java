package Transylvania.Classes;

public class Transaction {
    String transactionId, userId, roomId, paymentMethod;
    int transactionPrice, taxFee;

    public Transaction(String transactionId, String userId, String roomId, String paymentMethod, int transactionPrice, int taxFee) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.roomId = roomId;
        this.paymentMethod = paymentMethod;
        this.transactionPrice = transactionPrice;
        this.taxFee = taxFee;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(int transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    public int getTaxFee() {
        return taxFee;
    }

    public void setTaxFee(int taxFee) {
        this.taxFee = taxFee;
    }
}
