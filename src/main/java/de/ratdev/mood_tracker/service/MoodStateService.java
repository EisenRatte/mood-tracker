package de.ratdev.mood_tracker.service;

import de.ratdev.mood_tracker.persistance.model.MoodState;
import de.ratdev.mood_tracker.persistance.model.MoodStateAggregation;
import de.ratdev.mood_tracker.persistance.model.MoodStateWeek;
import de.ratdev.mood_tracker.persistance.repository.MoodStateWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

/**
 * Created by EisenRatte on 03.07.2016.
 */
@Service
public class MoodStateService {



    @Autowired
    private MoodStateWeekRepository moodStateWeekRepository;

    public void addMoodState(MoodState moodState){

        String weekId = getCurrentWeekMoodId();
        Optional<MoodStateWeek> weekOpt = Optional.ofNullable(moodStateWeekRepository.findOne(weekId));
        MoodStateWeek week = weekOpt.orElse(new MoodStateWeek(weekId));
        addMoodState(moodState, week);
        recalculateWeekMood(week);
        moodStateWeekRepository.save(week);
    }

    private void addMoodState(MoodState moodState, MoodStateWeek week) {
        week.getSingleStates().add(moodState);
    }

    private void recalculateWeekMood(MoodStateWeek week){

        MoodStateAggregation aggregation = new MoodStateAggregation();

        aggregation.setPeace(getSingleAverage(week, MoodState::getPeace));
        aggregation.setPleasure(getSingleAverage(week, MoodState::getPleasure));
        aggregation.setFamily(getSingleAverage(week, MoodState::getFamily));
        aggregation.setFriends(getSingleAverage(week, MoodState::getFriends));
        aggregation.setFitness(getSingleAverage(week, MoodState::getFitness));
        aggregation.setFinance(getSingleAverage(week, MoodState::getFinance));
        aggregation.setEducation(getSingleAverage(week, MoodState::getEducation));
        aggregation.setCompany(getSingleAverage(week, MoodState::getCompany));

        aggregation.setCount(getCount(week));

        week.setAggregation(aggregation);
    }

    private long getCount(MoodStateWeek week) {
        return week.getSingleStates().stream().count();
    }

    private double getSingleAverage(MoodStateWeek week, ToDoubleFunction<MoodState> valueExtractor){
        return week.getSingleStates().stream()
                .collect(Collectors.averagingDouble(valueExtractor));
    }

    private String getCurrentWeekMoodId(){
        Calendar calendar = Calendar.getInstance();
        return String.format("%d-%d", calendar.get(Calendar.YEAR), calendar.get(Calendar.WEEK_OF_YEAR));
    }
}
