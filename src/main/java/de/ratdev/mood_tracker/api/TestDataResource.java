package de.ratdev.mood_tracker.api;

import de.ratdev.mood_tracker.persistance.model.MoodState;
import de.ratdev.mood_tracker.persistance.model.MoodStateAggregation;
import de.ratdev.mood_tracker.persistance.model.MoodStateWeek;
import de.ratdev.mood_tracker.persistance.repository.MoodStateWeekRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by EisenRatte on 03.07.2016.
 */
@RestController
@RequestMapping(path = "testData")
public class TestDataResource {


    @Autowired
    private MoodStateWeekRepository moodStateWeekRepository;

    //@RequestMapping(path = "/clear", method = RequestMethod.GET)
    public void clearData(){
        moodStateWeekRepository.deleteAll();
    }

    //@RequestMapping(path = "/create", method = RequestMethod.GET)
    public void generateTestData(@RequestParam("amountWeek") long amountWeek,
                                 @RequestParam("amountStates") long amountStates){
        clearData();
        ArrayList<MoodStateWeek> weeks = new ArrayList<>();
        for (int i = 0; i < amountWeek; i++) {
            MoodStateWeek week = new MoodStateWeek();
            week.setId("2016-"+i);

            week.setSingleStates(new ArrayList<>());
            for (int j = 0; j < amountStates; j++) {
                MoodState moodState = new MoodState();
                fillMoodState(moodState);
                week.getSingleStates().add(moodState);
            }

            MoodStateAggregation aggregation = new MoodStateAggregation();
            fillMoodState(aggregation);
            aggregation.setCount(amountStates);

            week.setAggregation(aggregation);

            weeks.add(week);
            //moodStateWeekRepository.insert(week);
        }
        moodStateWeekRepository.insert(weeks);
    }

    private void fillMoodState(MoodState aggregation) {
        aggregation.setPeace(randomBetween(80, 85));
        aggregation.setPleasure(randomBetween(70, 75));
        aggregation.setFamily(randomBetween(78, 80));
        aggregation.setFriends(randomBetween(60, 70));
        aggregation.setFitness(randomBetween(70, 72));
        aggregation.setFinance(randomBetween(90, 92));
        aggregation.setEducation(randomBetween(88, 90));
        aggregation.setCompany(randomBetween(95, 98));

    }

    private int randomBetween(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
