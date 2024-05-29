public class Seat {
    private int seatId;
    private int trainId;
    private int tripId;

    private String availability;

    public Seat(int seatId, int trainId, int tripId, String availability) {
        this.seatId = seatId;
        this.trainId = trainId;
        this.tripId = tripId;

        this.availability = availability;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }



    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
