public class Trip {
    private int tripId;
    private int trainId;
    private String tripDate;
    private int adminId;

    public Trip(int tripId, int trainId, String tripDate, int adminId) {
        this.tripId = tripId;
        this.trainId = trainId;
        this.tripDate = tripDate;
        this.adminId = adminId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTripDate() {
        return tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
