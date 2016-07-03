package de.ratdev.mood_tracker.service;

import de.ratdev.mood_tracker.persistance.model.MoodState;
import de.ratdev.mood_tracker.persistance.model.MoodStateAggregation;
import de.ratdev.mood_tracker.persistance.model.MoodStateWeek;
import de.ratdev.mood_tracker.persistance.repository.MoodStateWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

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
        //TODO translate to streams or with mongo aggregation
        MoodStateAggregation aggregation = new MoodStateAggregation();
        for (MoodState moodState : week.getSingleStates()) {
            aggregation.setPeace(aggregation.getPeace()+moodState.getPeace());
            aggregation.setPleasure(aggregation.getPleasure()+moodState.getPleasure());
            aggregation.setFamily(aggregation.getFamily()+moodState.getFamily());
            aggregation.setFriends(aggregation.getFriends()+moodState.getFriends());
            aggregation.setFitness(aggregation.getFitness()+moodState.getFitness());
            aggregation.setFinance(aggregation.getFinance()+moodState.getFinance());
            aggregation.setEducation(aggregation.getEducation()+moodState.getEducation());
            aggregation.setCompany(aggregation.getCompany()+moodState.getCompany());
            aggregation.setCount(aggregation.getCount()+1);
        }

        aggregation.setPeace(aggregation.getPeace()/aggregation.getCount());
        aggregation.setPleasure(aggregation.getPleasure()/aggregation.getCount());
        aggregation.setFamily(aggregation.getFamily()/aggregation.getCount());
        aggregation.setFriends(aggregation.getFriends()/aggregation.getCount());
        aggregation.setFitness(aggregation.getFitness()/aggregation.getCount());
        aggregation.setFinance(aggregation.getFinance()/aggregation.getCount());
        aggregation.setEducation(aggregation.getEducation()/aggregation.getCount());
        aggregation.setCompany(aggregation.getCompany()/aggregation.getCount());

        week.setAggregation(aggregation);
    }

    private String getCurrentWeekMoodId(){
        Calendar calendar = Calendar.getInstance();
        return String.format("%d-%d", calendar.get(Calendar.YEAR), calendar.get(Calendar.WEEK_OF_YEAR));
    }
}
