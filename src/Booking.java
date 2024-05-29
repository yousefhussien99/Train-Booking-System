public class Booking {
    private int bookingId;
    private int userId;
    private int tripId;
    private String bookingDate;
    private int seatId;

    public Booking(int bookingId, int userId, int tripId, String bookingDate,int seatId) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.tripId = tripId;
        this.bookingDate = bookingDate;
        this.seatId=seatId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }
    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

}
