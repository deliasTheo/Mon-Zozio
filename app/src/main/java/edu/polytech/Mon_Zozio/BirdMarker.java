package edu.polytech.Mon_Zozio;

import org.osmdroid.util.GeoPoint;

public class BirdMarker {
    private String birdName;
    private GeoPoint coordinates;

    public BirdMarker(String birdName, GeoPoint coordinates) {
        this.birdName = birdName;
        this.coordinates = coordinates;
    }

    public String getBirdName() {
        return birdName;
    }

    public GeoPoint getCoordinates() {
        return coordinates;
    }
}
