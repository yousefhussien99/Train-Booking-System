public class Train {
    private int trainId;
    private String trainName;
    private int adminId;
    private String departureStation;
    private String arrivalStation;

    public Train(int trainId, String trainName, int adminId, String departureStation, String arrivalStation) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.adminId = adminId;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }
}
