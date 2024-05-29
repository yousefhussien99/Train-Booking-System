public class Station {
    private int stationId;
    private String stationName;
    private String city;
    private String state;

    public Station(int stationId, String stationName, String city, String state) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.city = city;
        this.state = state;
    }

    public int getStationId() {
        return stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }
}
