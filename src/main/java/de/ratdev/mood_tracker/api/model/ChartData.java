package de.ratdev.mood_tracker.api.model;

/**
 * Created by EisenRatte on 03.07.2016.
 */
public class ChartData {

    private String[] categories;

    private Series[] series;

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public Series[] getSeries() {
        return series;
    }

    public void setSeries(Series[] series) {
        this.series = series;
    }
}
