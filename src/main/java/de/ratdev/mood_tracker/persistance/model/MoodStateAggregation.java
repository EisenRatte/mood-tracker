package de.ratdev.mood_tracker.persistance.model;

/**
 * Created by EisenRatte on 03.07.2016.
 */
public class MoodStateAggregation extends MoodState{

    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
