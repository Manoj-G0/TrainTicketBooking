package models;

import java.util.List;

public class Train {
    private int id;
    private String number;
    private String name;
    private int totalSeats;
    private List<Station> stations;

    public Train() {}

    public Train(int id, String number, String name, int totalSeats) {
        this.id = id;
        this.number = number;
        this.name = name;
        this.totalSeats = totalSeats;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }
}