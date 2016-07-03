package de.ratdev.mood_tracker.persistance.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by EisenRatte on 02.07.2016.
 */
@Document(collection = "mood_state_week")
public class MoodStateWeek {

    public MoodStateWeek() {
    }

    public MoodStateWeek(String id) {
        this.id = id;
    }

    @Id
    private String id;

    private Collection<MoodState> singleStates = new ArrayList<>();

    private MoodStateAggregation aggregation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Collection<MoodState> getSingleStates() {
        return singleStates;
    }

    public void setSingleStates(Collection<MoodState> singleStates) {
        this.singleStates = singleStates;
    }

    public MoodStateAggregation getAggregation() {
        return aggregation;
    }

    public void setAggregation(MoodStateAggregation aggregation) {
        this.aggregation = aggregation;
    }
}
