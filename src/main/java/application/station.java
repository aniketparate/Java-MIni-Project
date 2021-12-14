package application;

public class station {
    String station_code, station_name;

    public String getStation_code() {
        return station_code;
    }

    public String getStation_name() {
        return station_name;
    }

    public station(String station_code, String station_name) {
        this.station_code = station_code;
        this.station_name = station_name;
    }
}
