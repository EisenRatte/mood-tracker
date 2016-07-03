package de.ratdev.mood_tracker.persistance.repository;

import de.ratdev.mood_tracker.persistance.model.MoodStateWeek;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by EisenRatte on 03.07.2016.
 */
public interface MoodStateWeekRepository extends MongoRepository<MoodStateWeek, String>{
}
