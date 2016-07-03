package de.ratdev.mood_tracker.service;

import de.ratdev.mood_tracker.api.model.ChartData;
import de.ratdev.mood_tracker.api.model.Series;
import de.ratdev.mood_tracker.persistance.model.MoodStateAggregation;
import de.ratdev.mood_tracker.persistance.model.MoodStateWeek;
import de.ratdev.mood_tracker.persistance.repository.MoodStateWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * Created by EisenRatte on 03.07.2016.
 */
@Service
public class ChartDataService {

    @Autowired
    private MoodStateWeekRepository moodStateWeekRepository;

    public ChartData getChartData(){
        Date fetchStart = new Date();
        List<MoodStateWeek> moodStateWeeks = loadWeeks();
        System.out.println(String.format("fetch time:%d", new Date().getTime()-fetchStart.getTime()));
        Date aggregationStart = new Date();
        ChartData chartData = new ChartData();
        chartData.setCategories(aggregateCategories(moodStateWeeks));
        chartData.setSeries(aggregateSeriesData(moodStateWeeks));
        System.out.println(String.format("aggregation time:%d", new Date().getTime()-aggregationStart.getTime()));
        return chartData;
    }

    private List<MoodStateWeek> loadWeeks(){
        //TODO: maybe limit size to last x weeks
        return moodStateWeekRepository.findAll();
    }

    private String[] aggregateCategories(List<MoodStateWeek> moodStateWeeks) {
        return moodStateWeeks.stream()
                .map(MoodStateWeek::getId)
                .toArray(String[]::new);
    }

    private Series[] aggregateSeriesData(List<MoodStateWeek> moodStateWeeks) {
        return new Series[]{
                aggregateSingleMoodSeriesData(moodStateWeeks, MoodStateAggregation::getPeace, "Peace"),
                aggregateSingleMoodSeriesData(moodStateWeeks, MoodStateAggregation::getPleasure, "Pleasure"),
                aggregateSingleMoodSeriesData(moodStateWeeks, MoodStateAggregation::getFamily, "Family"),
                aggregateSingleMoodSeriesData(moodStateWeeks, MoodStateAggregation::getFriends, "Friends"),
                aggregateSingleMoodSeriesData(moodStateWeeks, MoodStateAggregation::getFitness, "Fitness"),
                aggregateSingleMoodSeriesData(moodStateWeeks, MoodStateAggregation::getFinance, "Finance"),
                aggregateSingleMoodSeriesData(moodStateWeeks, MoodStateAggregation::getEducation, "Education"),
                aggregateSingleMoodSeriesData(moodStateWeeks, MoodStateAggregation::getCompany, "Company"),
                aggregateSingleMoodSeriesData(moodStateWeeks, MoodStateAggregation::getCount, "Count")
        };
    }

    private Series aggregateSingleMoodSeriesData(List<MoodStateWeek> moodStateWeeks,
                                                 Function<MoodStateAggregation, Integer> mood,
                                                 String seriesName) {
        int[] moodData = moodStateWeeks.stream()
                .map(MoodStateWeek::getAggregation)
                .map(mood)
                .mapToInt(Integer::intValue)
                .toArray();
        return new Series(seriesName, moodData);
    }

}
