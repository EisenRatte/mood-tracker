package de.ratdev.mood_tracker.persistance.model;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by EisenRatte on 02.07.2016.
 */
//@Document
public class MoodState {

    //@Id
    //private String id;

    @Max(100)
    @Min(0)
    private int peace;

    @Max(100)
    @Min(0)
    private int pleasure;

    @Max(100)
    @Min(0)
    private int family;

    @Max(100)
    @Min(0)
    private int friends;

    @Max(100)
    @Min(0)
    private int fitness;

    @Max(100)
    @Min(0)
    private int finance;

    @Max(100)
    @Min(0)
    private int education;

    @Max(100)
    @Min(0)
    private int company;

    //FIXME is not filled automatically
    @CreatedDate
    private DateTime createdAt;

    public int getPeace() {
        return peace;
    }

    public void setPeace(int peace) {
        this.peace = peace;
    }

    public int getPleasure() {
        return pleasure;
    }

    public void setPleasure(int pleasure) {
        this.pleasure = pleasure;
    }

    public int getFamily() {
        return family;
    }

    public void setFamily(int family) {
        this.family = family;
    }

    public int getFriends() {
        return friends;
    }

    public void setFriends(int friends) {
        this.friends = friends;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int getFinance() {
        return finance;
    }

    public void setFinance(int finance) {
        this.finance = finance;
    }

    public int getEducation() {
        return education;
    }

    public void setEducation(int education) {
        this.education = education;
    }

    public int getCompany() {
        return company;
    }

    public void setCompany(int company) {
        this.company = company;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "MoodState{" +
                "peace=" + peace +
                ", pleasure=" + pleasure +
                ", family=" + family +
                ", friends=" + friends +
                ", fitness=" + fitness +
                ", finance=" + finance +
                ", education=" + education +
                ", company=" + company +
                ", createdAt=" + createdAt +
                '}';
    }
}
