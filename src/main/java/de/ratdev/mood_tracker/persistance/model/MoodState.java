package de.ratdev.mood_tracker.persistance.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by EisenRatte on 02.07.2016.
 */
public class MoodState {


    @Max(100)
    @Min(0)
    private double peace;

    @Max(100)
    @Min(0)
    private double pleasure;

    @Max(100)
    @Min(0)
    private double family;

    @Max(100)
    @Min(0)
    private double friends;

    @Max(100)
    @Min(0)
    private double fitness;

    @Max(100)
    @Min(0)
    private double finance;

    @Max(100)
    @Min(0)
    private double education;

    @Max(100)
    @Min(0)
    private double company;


    public double getPeace() {
        return peace;
    }

    public void setPeace(double peace) {
        this.peace = peace;
    }

    public double getPleasure() {
        return pleasure;
    }

    public void setPleasure(double pleasure) {
        this.pleasure = pleasure;
    }

    public double getFamily() {
        return family;
    }

    public void setFamily(double family) {
        this.family = family;
    }

    public double getFriends() {
        return friends;
    }

    public void setFriends(double friends) {
        this.friends = friends;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFinance() {
        return finance;
    }

    public void setFinance(double finance) {
        this.finance = finance;
    }

    public double getEducation() {
        return education;
    }

    public void setEducation(double education) {
        this.education = education;
    }

    public double getCompany() {
        return company;
    }

    public void setCompany(double company) {
        this.company = company;
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
                '}';
    }
}
