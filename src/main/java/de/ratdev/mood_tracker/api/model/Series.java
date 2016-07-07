package de.ratdev.mood_tracker.api.model;

/**
 * Created by EisenRatte on 03.07.2016.
 */
public class Series {

    private String name;
    private Double[] data;

    public Series() {
    }

    public Series(String name, Double[] data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double[] getData() {
        return data;
    }

    public void setData(Double[] data) {
        this.data = data;
    }
}
