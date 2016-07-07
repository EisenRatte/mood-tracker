package de.ratdev.mood_tracker.persistance.model;

/**
 * Created by EisenRatte on 03.07.2016.
 */
public class MoodStateAggregation extends MoodState{

    private long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
